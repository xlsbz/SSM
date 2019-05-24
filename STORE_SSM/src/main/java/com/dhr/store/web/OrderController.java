package com.dhr.store.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dhr.store.domain.Orderitem;
import com.dhr.store.domain.Orders;
import com.dhr.store.domain.User;
import com.dhr.store.service.OrderService;
import com.dhr.store.util.CartItem;
import com.dhr.store.util.CookieUtils;
import com.dhr.store.util.JsonUtils;
import com.dhr.store.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Mr DU 订单处理
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	// 注入订单服务
	@Autowired
	private OrderService orderService;

	/**
	 * 生成订单
	 * 
	 * @return
	 */
	@RequestMapping("/saveOrder")
	public String saveOrder(Orders orders, Double total, HttpServletRequest request, HttpServletResponse response) {
		// 1.判断用户是否登录---->拦截器
		/*
		 * User user = (User) request.getSession().getAttribute("user"); if (user ==
		 * null) { request.setAttribute("msg", "请先登录再下单!"); return "jsp/login"; }
		 */
		// 拦截后把user实体对象给过来
		User user = (User) request.getAttribute("user");
		System.out.println(user.getUsername());
		// 2.补全订单属性
		orders = new Orders();
		orders.setAddress(null);
		orders.setName(null);
		orders.setOid(UUIDUtils.getId());
		orders.setOrdertime(new Date());
		// 0:未付款 1:已付款 ,待发货 2:已发货,待收货 3:交易完成
		orders.setState(0);
		orders.setTelephone(null);
		orders.setTotal(total);
		orders.setUser(user);
		// 3.遍历cookie的订单项,补全订单项
		// 获取cookie
		String cookieValue = CookieUtils.getCookieValue(request, "CART_COOKIE", true);
		if ((cookieValue != null) && (!cookieValue.equals(""))) {
			List<CartItem> jsonToList = JsonUtils.jsonToList(cookieValue, CartItem.class);
			// 遍历购物车
			Orderitem orderitem = null;
			List<Orderitem> orderitems = new ArrayList<>();
			for (CartItem cartItem : jsonToList) {
				orderitem = new Orderitem();
				orderitem.setItemid(UUIDUtils.getId());
				orderitem.setProduct(cartItem.getProduct());
				orderitem.setQuantity(cartItem.getQuantity());
				orderitem.setTotal(cartItem.getSubTotal());
				orderitem.setOrders(orders);
				// 添加到订集合
				orderitems.add(orderitem);
			}
			orders.setOrderitems(orderitems);
		}
		// 4.调用订单服务实现新增订单
		orderService.saveOrder(orders);
		// 5.设置订单到session
		request.getSession().setAttribute("orders", orders);
		// 6.清除cookie购物车
		CookieUtils.deleteCookie(request, response, "CART_COOKIE");
		return "redirect:/order/showOrder";
	}

	@RequestMapping("/showOrder")
	public String showOrder() {
		return "jsp/order_info";
	}

	/**
	 * 确认订单 模拟
	 * 
	 * @param orders
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/sureOrder", method = RequestMethod.POST)
	public String sureOrder(Orders orders, HttpServletRequest request, Model model) {
		// 1.获取用户数据
		// 2.补全属性
		orders.setState(1);
		orders.setOrdertime(new Date());
		// 3.调用服务改变订单
		orderService.updateOrder(orders);
		// 4.返回
		model.addAttribute("msg", "您的订单编号:" + orders.getOid() + "已经付款成功,预计三天后送达!");
		return "jsp/msg";
	}

	/**
	 * 分页查询订单
	 * 
	 * @param uid
	 * @param model
	 * @return
	 */
	@RequestMapping("/findOrderByUid/{uid}")
	public String findOrderByUid(@PathVariable String uid, Integer pageNumber, Model model) {
		// 1.获取查询数据
		// 设置pageHelper
		PageHelper.startPage(pageNumber, 4);
		// 2.调用查询服务
		List<Orders> orders = orderService.findOrderByUid(uid);
		// 3.设置到PageInfo
		PageInfo<Orders> pageInfoOrders = new PageInfo<>(orders);
		// 4.设置到域
		model.addAttribute("pageInfoOrder", pageInfoOrders);
		// 5.返回
		return "jsp/order_list";
	}

	/**
	 * 根据oid查询订单
	 * 
	 * @param oid
	 * @param model
	 * @return
	 */
	@RequestMapping("/findOrderByOid/{oid}")
	public String findOrderByOid(@PathVariable String oid, HttpServletRequest request, Model model) {
		// 调用服务
		Orders orders = orderService.findOrderByOid(oid);
		// 设置到域
		model.addAttribute("orders", orders);
		// 返回
		return "jsp/order_info";
	}

	/**
	 * 确认收货--改变订单状态
	 * 
	 * @param oid
	 * @param model
	 * @return
	 */
	@RequestMapping("/sureOrder/{oid}")
	public String sureOrder(@PathVariable String oid, HttpServletRequest request, Model model) {
		// 1.调用服务
		User user = (User) request.getSession().getAttribute("user");
		Orders orders = orderService.findOrderByOid(oid);
		// 2.改变状态
		// 交易完成
		orders.setState(3);
		orders.setUid(user.getUid());
		orderService.updateOrder(orders);
		// 3.返回
		model.addAttribute("msg", "订单" + oid + "完成交易,快去评价吧!");
		return "jsp/msg";
	}

}

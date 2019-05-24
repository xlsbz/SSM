package com.dhr.web;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.domain.User;
import com.dhr.service.OrderService;
import com.dhr.util.Cart;
import com.dhr.util.CartItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 订单处理
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	// 注入服务
	@Autowired
	private OrderService orderService;

	/**
	 * 生成订单
	 * 
	 * @return
	 */
	@RequestMapping("/createOrder")
	public String showOrder(HttpServletRequest request) {
		// 判断用户是否登录----拦截器
		User user = (User) request.getSession().getAttribute("user");
		// 1.获取购物车的订单数据
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 2.注入订单服务
		// 遍历购物车封装订单项数据
		// 3.补全订单属性
		// 封装订单属性
		Order order = new Order();
		order.setName(null);
		order.setAddr(null);
		order.setPhone(null);
		order.setOrdertime(new Date());
		order.setState(1);// 1:未付款 2:已付款未发货3:已发货待收货4完成
		order.setTotal(cart.getTotal());
		order.setUser(user);
		if (cart != null) {
			OrderItem orderItem = null;
			Collection<CartItem> cartAllItems = cart.getCartAllItems();
			for (CartItem item : cartAllItems) {
				orderItem = new OrderItem();
				orderItem.setCount(item.getNum());
				orderItem.setProduct(item.getProduct());
				orderItem.setSubtotal(item.getSubTotal());
				orderItem.setOrder(order);
				// 把订单项添加到订单中
				order.getOrderItems().add(orderItem);
			}
		}

		// 4.调用订单生成服务
		orderService.saveOrder(order);
		// 把订单设置到session
		request.getSession().setAttribute("orderBean", order);
		// 清除购物车
		request.getSession().removeAttribute("cart");
		// 5.返回
		return "redirect:/order/showOrder";
	}

	/**
	 * 显示订单
	 * 
	 * @return
	 */
	@RequestMapping("/showOrder")
	public String showOrder() {
		return "jsp/order";
	}

	/**
	 * 分页查看我的订单
	 * 
	 * @return
	 */
	@RequestMapping("/findOrderById/{uid}")
	public String findAllOrderById(@PathVariable Integer uid, @RequestParam Integer pageNumber, Model model,
			HttpServletRequest request) {
		// 清除订单session
		request.getSession().removeAttribute("orderBean");
		// 1.或取相关参数
		// 2.设置pagehelp
		PageHelper.startPage(pageNumber, 6);
		// 3.注入服务
		// 4.调用查询方法
		List<Order> orders = orderService.findOrderById(uid);
		// 5.设置到pageInfo
		PageInfo<Order> pageInfo = new PageInfo<>(orders);
		// 6.设置到域
		model.addAttribute("orderPageInfo", pageInfo);
		// 7.返回
		return "jsp/orderList";
	}

	/**
	 * 根据订单Id查询订单
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("/payMendOrder/{oid}")
	public String payMendOrder(@PathVariable Integer oid, Model model) {
		// 1.获取订单Id
		// 2.调用服务查询订单
		Order order = orderService.findOrderByOrderId(oid);
		// 3.设置到域
		model.addAttribute("orderBean", order);
		// 4.返回逻辑视图
		return "jsp/order";
	}

	/**
	 * 完成付款(模拟)
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("/activeOrder")
	public String avtiveOrder(Order order, Model model) {
		// 1.获取订单数据 收货人,电话,姓名
		// 2.调用服务改变订单状态完善信息
		order.setState(2);
		orderService.updateOrderByUid(order);
		// 3.订单Id到域
		model.addAttribute("msg", "订单:" + order.getOid() + "付款成功,预计三天后送到!");
		// 4.返回
		return "jsp/msg";
	}

	/**
	 * 确认收货
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("/sureOrder/{oid}")
	public String suerOrder(@PathVariable Integer oid, Model model) {
		// 修改订单状态
		Order order = new Order();
		order.setState(4);
		order.setOid(oid);
		orderService.updateOrderByOid(order);
		model.addAttribute("msg", "收货成功!快去评价吧");
		return "jsp/msg";
	}
}

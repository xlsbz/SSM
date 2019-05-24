package com.dhr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.service.AdminOrderService;
import com.dhr.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 订单处理
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	// 注入订单服务
	@Autowired
	private AdminOrderService adminOrderService;

	// 注入order服务
	@Autowired
	private OrderService orderService;

	/**
	 * 查询所有订单 type:0所有订单 :1:未付款订单 2:已付款(待发货)订单 3:已发货(待收货)订单 4:已完成订单
	 * 
	 * @return
	 */
	@RequestMapping("/orderfindAllByPage")
	public String findAllOrder(@RequestParam(required = false) String type,
			@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		// 1.获取查询类型和页码
		// 2.创建pagehelper
		PageHelper.startPage(pageNumber, 6);
		// 3.调用服务
		// 4.查询结果
		List<Order> orders = adminOrderService.findAllOrderType(type);
		// 5.把结果集放到pageInfo
		PageInfo<Order> pageInfoOrders = new PageInfo<>(orders);
		// 6.设置到域
		model.addAttribute("pageInfoOrders", pageInfoOrders);
		// 7.返回
		return "admin/order/list";
	}

	/**
	 * 查询单个订单
	 */
	@RequestMapping("/findOrderItems/{oid}")
	public String findOrderItems(@PathVariable Integer oid, Model model) {
		// 1.获取订单Id
		// 2.调用服务查询订单
		Order order = orderService.findOrderByOrderId(oid);
		List<OrderItem> orderItems = order.getOrderItems();
		model.addAttribute("orderItems", orderItems);
		return "admin/order/orderItem";
	}

	/**
	 * 去发货
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("/orderGoGoods/{oid}")
	public String orderGoods(@PathVariable Integer oid) {
		// 修改订单状态
		Order order = new Order();
		order.setOid(oid);
		order.setState(3);
		orderService.updateOrderByOid(order);
		return "redirect:/admin/orderfindAllByPage?type=all";
	}
}

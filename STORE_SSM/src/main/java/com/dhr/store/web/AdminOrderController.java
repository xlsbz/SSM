package com.dhr.store.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhr.store.domain.Orders;
import com.dhr.store.service.AdminOrderService;
import com.dhr.store.service.OrderService;
import com.dhr.store.util.StoreResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Mr DU 订单管理
 */
@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	@Autowired
	private AdminOrderService adminService;

	@Autowired
	private OrderService orderService;

	/**
	 * 分页查询 不同状态的订单
	 * 
	 * @param state
	 * @param pageNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/showOrder/{state}")
	public String showOrder(@PathVariable(required = false) Integer state,
			@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		// 1.注入服务
		// 2.设置pageHelper
		PageHelper.startPage(pageNumber, 5);
		// 3.查询结果集
		List<Orders> orders = adminService.findOrderByState(state);
		// 4.封装到pageInfo
		PageInfo<Orders> pageInfoOrder = new PageInfo<>(orders);
		// 5.返回
		model.addAttribute("pageInfoOrders", pageInfoOrder);
		model.addAttribute("state", state);
		return "admin/order/list";
	}

	/**
	 * 异步查询订单详情
	 * 
	 * @param oid
	 * @return
	 */
	@RequestMapping("/findOrderByOid/{oid}")
	@ResponseBody
	public StoreResult findOrderByOid(@PathVariable String oid, Model model) {
		// 注入orderService
		Orders orders = orderService.findOrderByOid(oid);
		model.addAttribute("orders", orders);
		return StoreResult.ok(orders);
	}

}

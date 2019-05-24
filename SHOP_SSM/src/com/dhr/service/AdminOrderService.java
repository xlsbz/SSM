package com.dhr.service;

import java.util.List;

import com.dhr.domain.Order;

public interface AdminOrderService {

	/**
	 * 按类型查询所有订单
	 * 
	 * @param type
	 * @return
	 */
	List<Order> findAllOrderType(String type);

}

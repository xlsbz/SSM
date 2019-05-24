package com.dhr.service;

import java.util.List;

import com.dhr.domain.Order;

/**
 * 订单接口
 * 
 * @author Mr DU
 *
 */
public interface OrderService {

	/**
	 * 保存订单
	 * 
	 * @param order
	 */
	void saveOrder(Order order);

	/**
	 * 根据用户ID查询订单
	 * 
	 * @param uid
	 * @return
	 */
	List<Order> findOrderById(Integer uid);

	/**
	 * 根据订单ID查询订单
	 * 
	 * @param oid
	 * @return
	 */
	Order findOrderByOrderId(Integer oid);

	/**
	 * 修改订单状态
	 * 
	 * @param order
	 */
	void updateOrderByUid(Order order);

	// *************************************************************************//
	/**
	 * 修改订单状态
	 * 
	 * @param order
	 */
	void updateOrderByOid(Order order);

}

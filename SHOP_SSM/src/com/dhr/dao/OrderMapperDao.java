package com.dhr.dao;

import java.util.List;

import com.dhr.domain.Order;

public interface OrderMapperDao {

	/**
	 * 保存订单
	 * 
	 * @param order
	 * @return
	 */
	Integer saveOrder(Order order);

	/**
	 * 根据用户Id查询订单
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
	void updateOrder(Order order);

	// ========================管理员操作=====================================================\\
	/**
	 * 
	 * @param type
	 * @return
	 */
	List<Order> findOrderType(Integer type);

	List<Order> findOrder();

	/**
	 * 修改订单状态
	 * 
	 * @param order
	 */
	void updateOrderCid(Order order);

}

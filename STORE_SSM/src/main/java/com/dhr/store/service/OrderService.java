package com.dhr.store.service;

import java.util.List;

import com.dhr.store.domain.Orders;

/**
 * @author Mr DU
 *
 */
public interface OrderService {

	/**
	 * 生成订单
	 * 
	 * @param orders
	 */
	void saveOrder(Orders orders);

	/**
	 * 更新订单
	 * 
	 * @param orders
	 */
	void updateOrder(Orders orders);

	/**
	 * 查询订单
	 * 
	 * @param uid
	 * @return
	 */
	List<Orders> findOrderByUid(String uid);

	/**
	 * 查询单个订单
	 * 
	 * @param oid
	 * @return
	 */
	Orders findOrderByOid(String oid);

}

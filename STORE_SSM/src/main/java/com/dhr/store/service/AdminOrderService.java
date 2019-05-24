package com.dhr.store.service;

import java.util.List;

import com.dhr.store.domain.Orders;

/**
 * @author Mr DU
 *
 */
public interface AdminOrderService {

	/**
	 * 查询订单
	 * 
	 * @param state
	 * @return
	 */
	List<Orders> findOrderByState(Integer state);

}

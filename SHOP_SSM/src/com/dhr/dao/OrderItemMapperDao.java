package com.dhr.dao;

import com.dhr.domain.OrderItem;

public interface OrderItemMapperDao {

	/**
	 * 保存订单项
	 * 
	 * @param item
	 */
	void saveOrderItem(OrderItem item);

}

package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Orderitem;
import com.dhr.store.domain.Orders;
import com.dhr.store.mapper.OrdersMapper;
import com.dhr.store.service.OrderService;

/**
 * @author Mr DU 订单业务
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapper ordersMapper;

	/**
	 * @param orders
	 * 
	 */
	@Override
	public void saveOrder(Orders orders) {
		// 1.注入mapper
		ordersMapper.saveOrders(orders);
		// 添加订单项
		List<Orderitem> orderitems = orders.getOrderitems();
		for (Orderitem orderitem : orderitems) {
			ordersMapper.saveOrderItem(orderitem);
		}
	}

	/**
	 * @param orders
	 */
	@Override
	public void updateOrder(Orders orders) {

		ordersMapper.updateByPrimaryKey(orders);
	}

	/**
	 * @param uid
	 * @return
	 */
	@Override
	public List<Orders> findOrderByUid(String uid) {
		List<Orders> list = ordersMapper.findOrderAndOrderItem(uid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;

	}

	/**
	 * @param oid
	 * @return
	 */
	@Override
	public Orders findOrderByOid(String oid) {
		return ordersMapper.findOrderByOid(oid);
	}

}

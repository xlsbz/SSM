package com.dhr.service.impl;
/**
 * 订单服务
 * @author Mr DU
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.OrderItemMapperDao;
import com.dhr.dao.OrderMapperDao;
import com.dhr.domain.Order;
import com.dhr.domain.OrderItem;
import com.dhr.service.OrderService;

/**
 * 订单业务
 * 
 * @author Mr DU
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapperDao orderMapper;
	@Autowired
	private OrderItemMapperDao orderItemMapper;

	/**
	 * 保存订单
	 */
	@Override
	public void saveOrder(Order order) {
		// 1.注入订单mapper和订单项mapper
		// 2.调用订单mapper保存订单
		Integer oid = orderMapper.saveOrder(order);
		// 返回主键Id
		order.setOid(order.getOid());
		// 3.调用订单项mapper保存订单项
		for (OrderItem item : order.getOrderItems()) {
			item.setOrder(order);
			orderItemMapper.saveOrderItem(item);
		}
	}

	/**
	 * 根据用户ID查询订单
	 */
	@Override
	public List<Order> findOrderById(Integer uid) {
		List<Order> list = orderMapper.findOrderById(uid);
		return list;
	}

	@Override
	public Order findOrderByOrderId(Integer oid) {
		return orderMapper.findOrderByOrderId(oid);
	}

	@Override
	public void updateOrderByUid(Order order) {
		orderMapper.updateOrder(order);
	}

	@Override
	public void updateOrderByOid(Order order) {
		orderMapper.updateOrderCid(order);
	}

}

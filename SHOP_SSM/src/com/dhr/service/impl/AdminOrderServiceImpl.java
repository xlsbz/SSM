package com.dhr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.OrderMapperDao;
import com.dhr.domain.Order;
import com.dhr.service.AdminOrderService;

/**
 * 订单服务
 * 
 * @author Mr DU
 *
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	@Autowired
	private OrderMapperDao orderMapper;

	@Override
	public List<Order> findAllOrderType(String type) {
		// 注入mapper查询订单
		if (type.equals("all")) {
			return orderMapper.findOrder();
		} else {
			return orderMapper.findOrderType(Integer.parseInt(type));
		}
	}

}

package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Orders;
import com.dhr.store.mapper.OrdersMapper;
import com.dhr.store.service.AdminOrderService;

/**
 * @author Mr DU 订单业务
 */
@Service
public class AdminOrderServiceImpl implements AdminOrderService {

	// 注入mapper
	@Autowired
	private OrdersMapper ordersMapper;

	/**
	 * @param state
	 * @return
	 */
	@Override
	public List<Orders> findOrderByState(Integer state) {
		if (state == 4) {
			// 查询所有订单
			List<Orders> list = ordersMapper.findAllOrderAndOrderItem();
			return list;
		}
		// 按状态查询
		List<Orders> selectByExample = ordersMapper.findOrderByState(state);
		if (selectByExample != null) {
			return selectByExample;
		}
		return null;
	}

}

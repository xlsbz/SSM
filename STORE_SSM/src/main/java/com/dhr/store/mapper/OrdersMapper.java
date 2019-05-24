package com.dhr.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dhr.store.domain.Orderitem;
import com.dhr.store.domain.Orders;
import com.dhr.store.domain.OrdersExample;

public interface OrdersMapper {
	int countByExample(OrdersExample example);

	int deleteByExample(OrdersExample example);

	int deleteByPrimaryKey(String oid);

	int insert(Orders record);

	int insertSelective(Orders record);

	List<Orders> selectByExample(OrdersExample example);

	Orders selectByPrimaryKey(String oid);

	int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

	int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

	int updateByPrimaryKeySelective(Orders record);

	int updateByPrimaryKey(Orders record);

	// ***************************自定义**************************//
	/**
	 * 级联保存
	 * 
	 * @param orders
	 */
	void saveOrders(Orders orders);

	/**
	 * 级联保存
	 * 
	 * @param orderitem
	 */
	void saveOrderItem(Orderitem orderitem);

	/**
	 * 关联查询
	 * 
	 * @param uid
	 * @return
	 */
	List<Orders> findOrderAndOrderItem(String uid);

	/**
	 * 查询单个订单
	 * 
	 * @param oid
	 * @return
	 */
	Orders findOrderByOid(String oid);

	/**
	 * 查询所有订单
	 * 
	 * @return
	 */
	List<Orders> findAllOrderAndOrderItem();

	/**
	 * 按状态查询
	 * 
	 * @param state
	 * @return
	 */
	List<Orders> findOrderByState(Integer state);
}
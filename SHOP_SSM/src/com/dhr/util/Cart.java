package com.dhr.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.dhr.domain.Product;

/**
 * 购物车= 购物项+总价
 * 
 * @author Mr DU
 *
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<String, CartItem> mapCart = new HashMap<>();
	private Double total = 0.0;

	/**
	 * 获取所有购物项
	 * 
	 * @return
	 */
	public Collection<CartItem> getCartAllItems() {
		return mapCart.values();
	}

	public Map<String, CartItem> getCartItems() {
		return mapCart;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.mapCart = cartItems;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	/**
	 * 添加到购物车
	 * 
	 * @param cartItem
	 */
	public void add2Cart(CartItem cartItem) {
		Product newProduct = cartItem.getProduct();
		// 1.查看购物车是否有该商品
		if (mapCart.containsKey(newProduct.getPid().toString())) {
			// 2.如果有,修改数量 现在的数量=原来的数量+新的数量
			CartItem oitem = mapCart.get(newProduct.getPid().toString());
			oitem.setNum(oitem.getNum() + cartItem.getNum());
		} else {
			// 3.如果没有,直接put
			mapCart.put(cartItem.getProduct().getPid().toString(), cartItem);
		}
		// 4.改变价格
		total += cartItem.getSubTotal();
	}

	/**
	 * 移除商品
	 * 
	 * @param pid
	 */
	public void remove2Cart(Integer pid) {
		// 1.根据ID移除购物项并返回被移除的项
		CartItem cartItem = mapCart.remove(pid.toString());
		// 2.总价改变
		total -= cartItem.getSubTotal();
	}

	/**
	 * 清空购物车
	 */
	public void clearCart() {
		mapCart.clear();
		total = 0.0;
	}
}

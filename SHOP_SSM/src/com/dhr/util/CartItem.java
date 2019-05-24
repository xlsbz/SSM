package com.dhr.util;

import java.io.Serializable;

import com.dhr.domain.Product;

/**
 * 购物项=商品+小计+数量
 * 
 * @author Mr DU
 *
 */
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Product product;
	private Integer num;
	private Double subTotal;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	// 小计
	public Double getSubTotal() {
		return this.product.getShop_price() * num;
	}

	// public void setSubTotal(Double subTotal) {
	// this.subTotal = subTotal;
	// }

}

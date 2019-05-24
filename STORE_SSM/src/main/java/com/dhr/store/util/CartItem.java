package com.dhr.store.util;

import com.dhr.store.domain.Product;

/**
 * @author Mr DU 购物项
 */
public class CartItem {
	private String pid;
	private Product product;// 购买商品
	private Integer quantity;// 购买数量
	private Double subTotal;// 小计

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSubTotal() {
		return product.getShopPrice() * quantity;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

}

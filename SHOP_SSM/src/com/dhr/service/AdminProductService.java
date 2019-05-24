package com.dhr.service;

import java.util.List;

import com.dhr.domain.Product;

public interface AdminProductService {

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	List<Product> findAllProduct();

	/**
	 * 删除商品
	 * 
	 * @param pid
	 */
	Integer delProductById(Integer pid) throws Exception;

	/**
	 * 新增商品
	 * 
	 * @param product
	 */
	void addProduct(Product product);

	/**
	 * 查询单个商品
	 * 
	 * @param pid
	 * @return
	 */
	Product findProductById(Integer pid);

	/**
	 * 修改商品
	 * 
	 * @param product
	 */
	void updateProduct(Product product);

}

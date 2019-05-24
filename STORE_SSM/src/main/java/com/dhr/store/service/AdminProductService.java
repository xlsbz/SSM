package com.dhr.store.service;

import java.util.List;

import com.dhr.store.domain.Product;

/**
 * @author Mr DU
 *
 */
public interface AdminProductService {

	/**
	 * 查询所有商品
	 * 
	 * @return
	 */
	List<Product> findAllProduct();

	/**
	 * 查询已下架商品
	 * 
	 * @return
	 */
	List<Product> findPushDownProduct();

	/**
	 * 下架商品
	 * 
	 * @param pid
	 */
	void shutDownProduct(String pid);

	/**
	 * 上架商品
	 * 
	 * @param product
	 */
	void saveProduct(Product product);

	/**
	 * 修改商品
	 * 
	 * @param product
	 */
	void updateProduct(Product product);

}

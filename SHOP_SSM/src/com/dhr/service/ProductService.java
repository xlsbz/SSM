package com.dhr.service;

import java.util.List;

import com.dhr.domain.Product;

public interface ProductService {
	/**
	 * 热门商品
	 * 
	 * @return
	 */
	List<Product> findHotProduct();

	/**
	 * 最新商品
	 * 
	 * @return
	 */
	List<Product> findNewProduct();

	/**
	 * 推选
	 * 
	 * @return
	 */
	List<Product> findLikeProduct(int start);

	/**
	 * 查询某个一级分类的商品
	 * 
	 * @param cid
	 * @return
	 */
	List<Product> findProductByCid(Integer cid);

	/**
	 * 查询某个二级分类的商品
	 * 
	 * @param csid
	 * @return
	 */
	List<Product> findProductByCsid(Integer csid);

	/**
	 * 根据商品ID查询商品
	 * 
	 * @param pid
	 * @return
	 */
	Product findProductById(Integer pid);
}

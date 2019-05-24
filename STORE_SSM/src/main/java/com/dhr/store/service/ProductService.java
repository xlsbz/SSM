package com.dhr.store.service;

import java.util.List;

import com.dhr.store.domain.Product;

/**
 * @author Mr DU
 *
 */
public interface ProductService {

	/**
	 * @return
	 */
	List<Product> findHotProduct();

	/**
	 * @return
	 */
	List<Product> findNewProduct();

	/**
	 * @return
	 */
	List<Product> findLikeProduct();

	/**
	 * @param pid
	 * @return
	 */
	Product findProductById(String pid);

	/**
	 * @param cid
	 * @return
	 */
	List<Product> findProductByCid(String cid);

	/**
	 * @param jsonToList
	 * @return
	 */
	List<Product> findProductLike(List<String> jsonToList);

}

package com.dhr.dao;

import java.util.List;

import com.dhr.domain.Product;

public interface ProductMapperDao {
	/**
	 * 查询热门
	 * 
	 * @return
	 */
	List<Product> getHotProduct();

	/**
	 * 查询最新
	 * 
	 * @return
	 */
	List<Product> getNewProduct();

	/**
	 * 查询喜欢
	 * 
	 * @return
	 */
	List<Product> getLikeProduct(int start);

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
	 * @param cid
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

	// ***************************管理员操作**********************************************\\
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
	void delProductById(Integer pid) throws Exception;

	/**
	 * 新增商品
	 * 
	 * @param product
	 */
	void addProduct(Product product);

	/**
	 * 修改商品
	 * 
	 * @param product
	 */
	void updateProductByPid(Product product);

}

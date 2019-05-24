package com.dhr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.ProductMapperDao;
import com.dhr.domain.Product;
import com.dhr.service.ProductService;

/**
 * 商品业务
 * 
 * @author Mr DU
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	// 注入mapper
	@Autowired
	private ProductMapperDao productMapper;

	/**
	 * 查询热门
	 */
	@Override
	public List<Product> findHotProduct() {
		return productMapper.getHotProduct();
	}

	/**
	 * 最新
	 */
	@Override
	public List<Product> findNewProduct() {
		return productMapper.getNewProduct();
	}

	/**
	 * 推选
	 */
	@Override
	public List<Product> findLikeProduct(int start) {
		return productMapper.getLikeProduct(start);
	}

	/**
	 * 查询某个一级分类的商品
	 */
	@Override
	public List<Product> findProductByCid(Integer cid) {
		return productMapper.findProductByCid(cid);
	}

	/**
	 * 查询某个二级分类的商品
	 */
	@Override
	public List<Product> findProductByCsid(Integer csid) {
		return productMapper.findProductByCsid(csid);
	}

	/**
	 * 根据商品ID查询商品
	 */
	@Override
	public Product findProductById(Integer pid) {
		return productMapper.findProductById(pid);
	}
}

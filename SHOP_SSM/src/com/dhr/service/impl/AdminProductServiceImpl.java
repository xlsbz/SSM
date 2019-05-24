package com.dhr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.ProductMapperDao;
import com.dhr.domain.Product;
import com.dhr.service.AdminProductService;

/**
 * 商品服务
 * 
 * @author Mr DU
 *
 */
@Service
public class AdminProductServiceImpl implements AdminProductService {

	@Autowired
	private ProductMapperDao productMapper;

	/**
	 * 查询所有商品
	 */
	@Override
	public List<Product> findAllProduct() {
		return productMapper.findAllProduct();
	}

	@Override
	public Integer delProductById(Integer pid) throws Exception {
		productMapper.delProductById(pid);
		return 1;
	}

	@Override
	public void addProduct(Product product) {
		productMapper.addProduct(product);
	}

	@Override
	public Product findProductById(Integer pid) {
		return productMapper.findProductById(pid);
	}

	@Override
	public void updateProduct(Product product) {
		productMapper.updateProductByPid(product);
	}

}

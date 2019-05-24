package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Product;
import com.dhr.store.domain.ProductExample;
import com.dhr.store.domain.ProductExample.Criteria;
import com.dhr.store.mapper.ProductMapper;
import com.dhr.store.service.AdminProductService;

/**
 * @author Mr DU 商品管理服务
 */
@Service
public class AdminProductServiceImpl implements AdminProductService {

	// 注入mapper
	@Autowired
	private ProductMapper productMapper;

	/**
	 * @return
	 */
	@Override
	public List<Product> findAllProduct() {
		ProductExample example = new ProductExample();
		example.setOrderByClause("pdate desc");
		List<Product> list = productMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @return
	 */
	@Override
	public List<Product> findPushDownProduct() {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andPflagEqualTo(1);

		List<Product> list = productMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @param pid
	 */
	@Override
	public void shutDownProduct(String pid) {
		// 查询商品设置状态
		Product product = productMapper.selectByPrimaryKey(pid);
		product.setPflag(1);
		// 修改商品
		productMapper.updateByPrimaryKey(product);
	}

	/**
	 * @param product
	 */
	@Override
	public void saveProduct(Product product) {
		productMapper.insertSelective(product);
	}

	/**
	 * @param product
	 */
	@Override
	public void updateProduct(Product product) {
		productMapper.updateByPrimaryKeySelective(product);
	}

}

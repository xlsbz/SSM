package com.dhr.store.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Product;
import com.dhr.store.domain.ProductExample;
import com.dhr.store.domain.ProductExample.Criteria;
import com.dhr.store.mapper.ProductMapper;
import com.dhr.store.service.ProductService;

/**
 * @author Mr DU 商品业务
 */
@Service
public class ProductServiceImpl implements ProductService {

	// 注入mapper
	@Autowired
	private ProductMapper productMapper;

	/**
	 * @return
	 */
	@Override
	public List<Product> findHotProduct() {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsHotEqualTo(1);
		List<Product> list = productMapper.selectByExample(example);
		List<Product> products = new ArrayList<>();
		int flag = 9;
		while (flag > 0) {
			products.add(list.get(flag));
			flag--;
		}
		return products;
	}

	/**
	 * @return
	 */
	@Override
	public List<Product> findNewProduct() {
		ProductExample example = new ProductExample();
		example.setOrderByClause("pdate desc");
		List<Product> list = productMapper.selectByExample(example);
		List<Product> products = new ArrayList<>();
		int flag = 9;
		while (flag > 0) {
			products.add(list.get(flag));
			flag--;
		}
		return products;
	}

	/**
	 * @return
	 */
	@Override
	public List<Product> findLikeProduct() {
		List<Product> list = productMapper.findLikeProduct(new Random().nextInt(30));
		return list;
	}

	/**
	 * @param pid
	 * @return
	 */
	@Override
	public Product findProductById(String pid) {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(pid);
		List<Product> list = productMapper.selectByExample(example);
		return list.get(0);
	}

	/**
	 * @param cid
	 * @return
	 */
	@Override
	public List<Product> findProductByCid(String cid) {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid.toString());
		List<Product> list = productMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	/**
	 * @param jsonToList
	 * @return
	 */
	@Override
	public List<Product> findProductLike(List<String> jsonToList) {
		ProductExample example = new ProductExample();
		Criteria criteria = example.createCriteria();
		// 查询集合 where In()
		criteria.andPidIn(jsonToList);

		List<Product> list = productMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}

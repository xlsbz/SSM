package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Category;
import com.dhr.store.domain.CategoryExample;
import com.dhr.store.domain.CategoryExample.Criteria;
import com.dhr.store.mapper.CategoryMapper;
import com.dhr.store.service.AdminCategoryService;

/**
 * @author Mr DU
 *
 */
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

	// 注入mapper
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * @param cid
	 * @return
	 */
	@Override
	public Category findCategoryByCid(String cid) {
		CategoryExample example = new CategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);

		List<Category> list = categoryMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * @param category
	 */
	@Override
	public void saveCategory(Category category) {
		categoryMapper.insertSelective(category);
	}

	/**
	 * @param category
	 */
	@Override
	public void updateCategory(Category category) {
		categoryMapper.updateByPrimaryKey(category);
	}

	/**
	 * @param cid
	 */
	@Override
	public void delCategory(String cid) {
		categoryMapper.deleteByPrimaryKey(cid);
	}

}

package com.dhr.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.store.domain.Category;
import com.dhr.store.domain.CategoryExample;
import com.dhr.store.mapper.CategoryMapper;
import com.dhr.store.service.CategoryService;

/**
 * @author Mr DU 分类业务
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	// 注入mapper
	@Autowired
	private CategoryMapper categoryMapper;

	/**
	 * @return
	 */
	@Override
	public List<Category> findAllCategory() {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

}

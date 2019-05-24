package com.dhr.store.service;

import java.util.List;

import com.dhr.store.domain.Category;

/**
 * @author Mr DU
 *
 */
public interface CategoryService {

	/**
	 * 查询所有分类
	 * 
	 * @return
	 */
	List<Category> findAllCategory();

}

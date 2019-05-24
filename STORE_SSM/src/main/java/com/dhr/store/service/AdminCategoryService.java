package com.dhr.store.service;

import com.dhr.store.domain.Category;

/**
 * @author Mr DU
 *
 */
public interface AdminCategoryService {

	/**
	 * 查询分类
	 * 
	 * @param cid
	 * @return
	 */
	Category findCategoryByCid(String cid);

	/**
	 * 添加分类
	 * 
	 * @param category
	 */
	void saveCategory(Category category);

	/**
	 * 修改分类
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 删除分类
	 * 
	 * @param cid
	 */
	void delCategory(String cid);

}

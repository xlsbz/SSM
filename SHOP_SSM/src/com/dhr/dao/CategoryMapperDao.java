package com.dhr.dao;

import java.util.List;

import com.dhr.domain.Category;
import com.dhr.domain.CategorySecond;

public interface CategoryMapperDao {

	/**
	 * 查询所有一级分类
	 * 
	 * @return
	 */
	List<Category> findAllCategory();

	/**
	 * 添加一级分类
	 * 
	 * @param cname
	 */
	void saveCategory(String cname);

	/**
	 * 查找一级分类
	 * 
	 * @param cid
	 * @return
	 */
	Category findCategoryByCid(Integer cid);

	/**
	 * 修改一级分类
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 根据Id查询所有这个id的二级分类
	 * 
	 * @param cid
	 * @return
	 */
	List<CategorySecond> findAllCategorySecondCid(Integer cid);

	/**
	 * 删除一级分类下的所有二级分类
	 * 
	 * @param ids
	 */
	void delCategorySecondCid(List<Integer> ids);

	/**
	 * 删除一级分类
	 * 
	 * @param cid
	 */
	void delCategoryCid(Integer cid);

}

package com.dhr.service;

import java.util.List;

import com.dhr.domain.Category;
import com.dhr.domain.CategorySecond;

/**
 * 一级分类服务接口
 * 
 * @author Mr DU
 *
 */
public interface CategoryService {

	/**
	 * 查询所有一级分类
	 * 
	 * @return
	 */
	List<Category> findAllCategory();

	/**
	 * 查询所有二级分类
	 * 
	 * @return
	 */
	List<CategorySecond> findAllCategorySecond();

	/**
	 * 添加一级分类
	 * 
	 * @param cname
	 */
	void saveCategory(String cname);

	/**
	 * 添加二级分类
	 * 
	 * @param cname
	 * @param csname
	 */
	void saveCategorySecond(String cname, Integer cid);

	/**
	 * 查询一级分类
	 * 
	 * @param cid
	 * @return
	 */
	Category findAllCategoryById(Integer cid);

	/**
	 * 查询二级分类
	 * 
	 * @param cid
	 * @return
	 */
	CategorySecond findAllCategorySecondById(Integer csid);

	/**
	 * 修好一级分类
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 修改二级分类
	 * 
	 * @param categorySecond
	 */
	void updateCategorySecond(CategorySecond categorySecond);

	/**
	 * 删除一级分类
	 * 
	 * @param cid
	 */
	void delCategoryByCid(Integer cid);

	/**
	 * 删除二级分类
	 * 
	 * @param csid
	 */
	void delCategorySecondByCsid(Integer csid);

}

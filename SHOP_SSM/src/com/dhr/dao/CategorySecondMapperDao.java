package com.dhr.dao;

import java.util.List;

import com.dhr.domain.CategorySecond;

public interface CategorySecondMapperDao {
	/**
	 * 查询所有二级分类
	 * 
	 * @return
	 */
	List<CategorySecond> findAllCategorySecond();

	/**
	 * 添加二级分类
	 * 
	 * @param categorySecond
	 */
	void saveCategorySecond(String csname, Integer cid);

	/**
	 * 查找二级分类
	 * 
	 * @param csid
	 * @return
	 */
	CategorySecond findCategorySecondByCsid(Integer csid);

	/**
	 * 修改二级分类
	 * 
	 * @param categorySecond
	 */
	void updateCategorySecond(CategorySecond categorySecond);

	/**
	 * 删除二级分类
	 * 
	 * @param csid
	 */
	void delCategorySecond(Integer csid);
}

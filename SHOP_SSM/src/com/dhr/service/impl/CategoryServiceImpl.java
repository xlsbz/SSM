package com.dhr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhr.dao.CategoryMapperDao;
import com.dhr.dao.CategorySecondMapperDao;
import com.dhr.domain.Category;
import com.dhr.domain.CategorySecond;
import com.dhr.jedis.JedisClient;
import com.dhr.service.CategoryService;
import com.dhr.util.JsonUtils;

/**
 * 一级分类业务
 * 
 * @author Mr DU
 *
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapperDao categoryMapper;

	@Autowired
	private CategorySecondMapperDao categorySecondMapper;

	@Autowired
	private JedisClient jedisClient;

	/**
	 * 使用redis存到缓存 查询所有一级分类
	 */
	@Override
	public List<Category> findAllCategory() {
		// 注入mapper接口
		// 先从redis当中获取缓存数据,如果存在,就直接会返回,不存在从数据库查,并把结果设置到rdedis中
		List<Category> categories = new ArrayList<>();
		// 1.注入redis接口
		String nav = jedisClient.get("SHOP_CATEGORY");
		if (StringUtils.isNotBlank(nav)) {
			// 存在redis则直接转型返回
			categories = JsonUtils.jsonToList(nav, Category.class);
			return categories;
		}
		// 不存在直接从数据库查
		// 调用查询方法
		categories = categoryMapper.findAllCategory();
		if (categories != null) {
			// 设置到redis
			jedisClient.set("SHOP_CATEGORY", JsonUtils.objectToJson(categories));
		}
		// 返回
		return categories;
	}

	/**
	 * 查询二级分类
	 */
	@Override
	public List<CategorySecond> findAllCategorySecond() {
		// 注入mapper接口
		// 调用查询方法
		List<CategorySecond> categorySeconds = categorySecondMapper.findAllCategorySecond();
		// 返回
		return categorySeconds;
	}

	/**
	 * 添加一级分类
	 */
	@Override
	public void saveCategory(String cname) {
		// 删除redis的缓存数据
		jedisClient.del("SHOP_CATEGORY");
		categoryMapper.saveCategory(cname);
	}

	/**
	 * 添加二级分类
	 */
	@Override
	public void saveCategorySecond(String csname, Integer cid) {
		categorySecondMapper.saveCategorySecond(csname, cid);
	}

	/**
	 * 查询ID
	 */
	@Override
	public Category findAllCategoryById(Integer cid) {
		return categoryMapper.findCategoryByCid(cid);
	}

	/**
	 * 查询二级分类ID
	 */
	@Override
	public CategorySecond findAllCategorySecondById(Integer csid) {
		return categorySecondMapper.findCategorySecondByCsid(csid);
	}

	/**
	 * 更新一级分类
	 */
	@Override
	public void updateCategory(Category category) {
		// 删除缓存
		jedisClient.del("SHOP_CATEGORY");
		categoryMapper.updateCategory(category);
	}

	/**
	 * 跟新二级分类
	 */
	@Override
	public void updateCategorySecond(CategorySecond categorySecond) {
		categorySecondMapper.updateCategorySecond(categorySecond);
	}

	/**
	 * 删除一级分类 级联二级分类
	 */
	@Override
	public void delCategoryByCid(Integer cid) {
		// 查出所有改分类下的二级分类
		List<CategorySecond> categorySeconds = categoryMapper.findAllCategorySecondCid(cid);
		// 取出所有二级分类ID
		List<Integer> ids = new ArrayList<>();
		for (CategorySecond cate : categorySeconds) {
			ids.add(cate.getCsid());
		}
		// 执行删除
		if (ids != null && ids.size() > 0) {
			categoryMapper.delCategorySecondCid(ids);
		}
		// 删除一级分类
		// 删除缓存
		jedisClient.del("SHOP_CATEGORY");
		categoryMapper.delCategoryCid(cid);
	}

	/**
	 * 删除二级分类
	 *
	 */
	@Override
	public void delCategorySecondByCsid(Integer csid) {
		categorySecondMapper.delCategorySecond(csid);
	}

}

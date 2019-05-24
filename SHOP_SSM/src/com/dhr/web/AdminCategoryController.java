package com.dhr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhr.domain.Category;
import com.dhr.domain.CategorySecond;
import com.dhr.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 分类
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;

	/**
	 * 查询所有一级分类
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/findCategory")
	public String findCategory(Model model, HttpServletRequest request) {
		// 1.注入服务
		// 2.调用服务 级联查询了二级分类 存到session
		List<Category> category = categoryService.findAllCategory();
		// 3.保存到域
		request.getSession().setAttribute("category", category);
		// 4.返回
		return "admin/category/list";
	}

	/**
	 * 分页查询所有二级分类
	 * 
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("/findCategorySecond")
	public String findCategorySecond(@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		// 1.注入服务
		// 1.设置pageHelper
		PageHelper.startPage(pageNumber, 10);
		// 2.查询分类
		List<CategorySecond> categorySeconds = categoryService.findAllCategorySecond();
		// 3.设置到pageInfo
		PageInfo<CategorySecond> pageInfoCategorySecond = new PageInfo<>(categorySeconds);
		// 4.放在域对象
		model.addAttribute("pageInfoCategorySecond", pageInfoCategorySecond);
		// 5.返回
		return "admin/categorySecond/list";
	}

	/**
	 * 添加一级分类
	 * 
	 * @return
	 */
	@RequestMapping("/categorySave")
	public String saveCategory(String cname) {
		// 1.接收参数
		// 2.调用服务
		categoryService.saveCategory(cname);
		// 3.返回
		return "redirect:/admin/findCategory";
	}

	/**
	 * 添加二级分类
	 * 
	 * @return
	 */
	@RequestMapping("/categorySecondSave")
	public String saveCategorySecond(String csname, Integer cid) {
		// 1.接收参数
		// 2.调用服务
		categoryService.saveCategorySecond(csname, cid);
		// 3.返回
		return "redirect:/admin/findCategorySecond";
	}

	/**
	 * 去编辑页面回显 一级分类数据
	 * 
	 * @return
	 */
	@RequestMapping("/categoryEdit/{cid}")
	public String updateCategory(@PathVariable Integer cid, Model model) {
		// 查询该分类
		Category category = categoryService.findAllCategoryById(cid);
		model.addAttribute("categoryBean", category);
		return "admin/category/edit";
	}

	/**
	 * 去编辑页面回显 二级分类数据
	 * 
	 * @return
	 */
	@RequestMapping("/categorySecondEdit/{csid}")
	public String updateCategorySecond(@PathVariable Integer csid, Model model) {
		// 查询该分类
		CategorySecond categorySecond = categoryService.findAllCategorySecondById(csid);
		model.addAttribute("categorySecondBean", categorySecond);
		return "admin/categorySecond/edit";
	}

	/**
	 * 更新一级分类
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping("/updateCategory")
	public String updateCategory(Category category) {
		categoryService.updateCategory(category);
		return "redirect:/admin/findCategory";
	}

	/**
	 * 更新二级分类
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping("/updateCategorySecond")
	public String updateCategorySecond(CategorySecond categorySecond, Integer cid) {
		// 封装参数
		Category category = new Category();
		category.setCid(cid);
		categorySecond.setCategory(category);
		categoryService.updateCategorySecond(categorySecond);
		return "redirect:/admin/findCategorySecond";
	}

	/**
	 * 删除一级分类 (级联删除所有二级分类)
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping("/categoryDelete/{cid}")
	public String delCategory(@PathVariable Integer cid) {
		categoryService.delCategoryByCid(cid);
		return "redirect:/admin/findCategory";
	}

	/**
	 * 删除二级分类
	 */
	@RequestMapping("/categorySecondDelete/{csid}")
	public String delCategorySecond(@PathVariable Integer csid) {
		categoryService.delCategorySecondByCsid(csid);
		return "redirect:/admin/findCategorySecond";
	}
}

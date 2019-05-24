package com.dhr.store.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dhr.store.domain.Category;
import com.dhr.store.service.AdminCategoryService;
import com.dhr.store.service.CategoryService;
import com.dhr.store.util.UUIDUtils;

/**
 * @author Mr DU 管理员管理分类
 */
@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AdminCategoryService adminService;

	/**
	 * 显示所有分类
	 * 
	 * @return
	 */
	@RequestMapping("/showCategory")
	public String showCategory(Model model) {
		// 注入服务
		List<Category> category = categoryService.findAllCategory();
		model.addAttribute("category", category);
		return "admin/category/list";
	}

	/**
	 * 去修改页面
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping("/toEdit/{cid}")
	public String toEdit(@PathVariable String cid, Model model) {
		// 查询该分类回显数据
		System.out.println(cid);
		Category category = adminService.findCategoryByCid(cid);
		model.addAttribute("category", category);
		return "admin/category/edit";
	}

	/**
	 * 修改分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateCategory")
	public String updateCategory(Category category) {
		// 调用方法修改分类
		adminService.updateCategory(category);
		return "redirect:/admin/showCategory";
	}

	/**
	 * 去添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toEdit() {
		return "admin/category/add";
	}

	/**
	 * 添加分类
	 * 
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public String addCategory(Category category) {
		// 注入服务
		// 调用服务
		// 补全属性
		category.setCid(UUIDUtils.getId());
		adminService.saveCategory(category);
		return "redirect:/admin/showCategory";
	}

	/**
	 * 删除分类
	 * 
	 * @param cid
	 * @return
	 */
	@RequestMapping("/delCategory/{cid}")
	public String delCategory(@PathVariable String cid) {
		adminService.delCategory(cid);
		return "redirect:/admin/showCategory";
	}
}

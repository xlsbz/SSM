package com.dhr.store.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhr.store.domain.Category;
import com.dhr.store.domain.Product;
import com.dhr.store.service.CategoryService;
import com.dhr.store.service.ProductService;

/**
 * @author Mr DU 门户处理
 */
@Controller
public class IndexController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@RequestMapping("/index")
	public String indexPage(HttpServletRequest request) {
		// 同步加载导航栏,首页商品信息
		// 1.注入分类服务
		// 调用查询分类方法
		List<Category> categories = categoryService.findAllCategory();
		if (categories != null && categories.size() > 0) {
			request.getSession().setAttribute("category", categories);
		}
		// 2.注入商品服务
		// 查询商品
		List<Product> productHots = productService.findHotProduct();
		List<Product> productNews = productService.findNewProduct();
		List<Product> productLikes = productService.findLikeProduct();
		request.setAttribute("productHots", productHots);
		request.setAttribute("productNews", productNews);
		request.setAttribute("productLikes", productLikes);
		return "jsp/index";
	}
}

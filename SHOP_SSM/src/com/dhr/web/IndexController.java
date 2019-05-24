package com.dhr.web;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhr.domain.Category;
import com.dhr.domain.Product;
import com.dhr.service.CategoryService;
import com.dhr.service.ProductService;

/**
 * 首页加载
 * 
 * @author Mr DU
 *
 */
@Controller
public class IndexController {

	// 注入分类服务
	@Autowired
	private CategoryService cateService;

	// 注入商品服务
	@Autowired
	private ProductService productService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		try {
			// 默认加载一级分类
			// 注入分类服务
			List<Category> category = cateService.findAllCategory();
			request.getSession().setAttribute("categoryList", category);
			// 查询首页的几大分类商品
			// 查询热门商品
			List<Product> productHots = productService.findHotProduct();
			request.setAttribute("productHots", productHots);
			// 查询最新商品
			List<Product> productNews = productService.findNewProduct();
			request.setAttribute("productNews", productNews);
			// 查询为你推选的商品
			Random random = new Random();
			List<Product> productLikes = productService.findLikeProduct(random.nextInt(45));
			request.setAttribute("productLikes", productLikes);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/index";
	}
}

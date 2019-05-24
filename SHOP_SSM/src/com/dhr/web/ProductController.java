package com.dhr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhr.domain.Product;
import com.dhr.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 商品处理
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	// 注入商品服务
	@Autowired
	private ProductService productService;

	/**
	 * 查询一级分类下的所有商品
	 * 
	 * @param cid
	 * @param pageNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/findProductByCid/{cid}")
	public String findProductByCid(@PathVariable Integer cid, @RequestParam(defaultValue = "1") Integer pageNumber,
			Model model) {
		// 获取到分类ID
		// 注入服务

		// 调用查询方法
		// 设置分页相关属性 第几页/每页大小
		PageHelper.startPage(pageNumber, 12);
		List<Product> products = productService.findProductByCid(cid);
		PageInfo<Product> pageInfo = new PageInfo<>(products);
		// 设置cid到域
		model.addAttribute("cid", cid);
		// 设置到域
		model.addAttribute("pageInfoCid", pageInfo);
		// 返回
		return "jsp/categoryProduct";
	}

	/**
	 * 查询二级分类下的所有商品
	 * 
	 * @param csid
	 * @param pageNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/findProductByCsid/{csid}")
	public String findProductByCsid(@PathVariable Integer csid, @RequestParam(defaultValue = "1") Integer pageNumber,
			Model model) {
		// 注入服务

		// 设置pageHelper
		PageHelper.startPage(pageNumber, 8);
		// 调用查询方法
		List<Product> products = productService.findProductByCsid(csid);
		// 封装到pageInfo
		PageInfo<Product> pageInfo = new PageInfo<>(products);
		// 设置csid回显
		model.addAttribute("csid", csid);
		// 设置到域
		model.addAttribute("pageInfoCsid", pageInfo);
		// 返回
		return "jsp/categoryProduct";

	}

	/**
	 * 根据商品ID查询商品详情
	 * 
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/productById/{pid}")
	public String findProductInfoById(@PathVariable Integer pid, Model model) {
		// 获取商品ID
		// 注入服务

		// 调用查询服务的方法
		Product product = productService.findProductById(pid);
		// 设置到域
		model.addAttribute("productBean", product);
		// 返回
		return "jsp/productInfo";
	}

}

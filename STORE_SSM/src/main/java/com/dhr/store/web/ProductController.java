package com.dhr.store.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dhr.store.domain.Product;
import com.dhr.store.service.ProductService;
import com.dhr.store.util.CookieUtils;
import com.dhr.store.util.JsonUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Mr DU 商品处理
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 查询商品
	 * 
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findProductById/{pid}", method = RequestMethod.GET)
	public String findProductById(@PathVariable String pid, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 注入商品服务
		// 查询
		Product product = productService.findProductById(pid);
		// 把商品id存到cookie

		// 已经存在cookie的话
		try {
			String cookieValue = CookieUtils.getCookieValue(request, "COOKIE_PRODUCT", true);
			if ((cookieValue != null) && (!cookieValue.equals(""))) {
				// cookie有集合,判断集合中有没有这个商品id
				List<String> jsonToList = JsonUtils.jsonToList(cookieValue, String.class);
				boolean flag = true;
				for (String cookiePid : jsonToList) {
					if (!cookiePid.equals(pid)) {
						flag = true;
					}
				}
				if (flag) {
					// 没有添加到cookie
					jsonToList.add(pid);
				}
				if (jsonToList.size() > 12) {
					jsonToList.remove(0);
				}
				// 设置回cookie
				CookieUtils.setCookie(request, response, "COOKIE_PRODUCT", JsonUtils.objectToJson(jsonToList), true);
			} else {
				// 首次添加
				List<String> ids = new ArrayList<>();
				System.out.println("首次添加");
				ids.add(pid);
				CookieUtils.setCookie(request, response, "COOKIE_PRODUCT", JsonUtils.objectToJson(ids), true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 设置到域
		model.addAttribute("productBean", product);
		// 返回
		return "jsp/product_info";
	}

	/**
	 * 分页查询分类商品
	 * 
	 * @param cid
	 * @param pageNumber
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findProductByCid/{cid}")
	public String findProductByCid(@PathVariable String cid,
			@RequestParam(required = false, defaultValue = "1") Integer pageNumber, HttpServletRequest request,
			Model model) {
		// 1.注入服务
		// 2.创建pageHelper
		PageHelper.startPage(pageNumber, 12);
		// 3.查询商品
		List<Product> products = productService.findProductByCid(cid);
		// 4.封装到pageInfo
		PageInfo<Product> pageInfoProducts = new PageInfo<>(products);
		// 5.设置到域
		model.addAttribute("pageInfoProducts", pageInfoProducts);
		// 设置分类回显
		model.addAttribute("cid", cid);
		// 显示十条最近浏览的商品--->点击商品就把商品id存到cookie
		// 从cookie去取出id
		String cookieValue = CookieUtils.getCookieValue(request, "COOKIE_PRODUCT", true);
		if ((cookieValue != null) && (!cookieValue.equals(""))) {
			// 取出集合
			List<String> jsonToList = JsonUtils.jsonToList(cookieValue, String.class);
			// 查询商品
			List<Product> cookieList = productService.findProductLike(jsonToList);
			if (cookieList != null) {
				model.addAttribute("cookieList", cookieList);
			}
		} else {
			model.addAttribute("msg", "暂时没有浏览的商品!");
		}
		// 6.返回
		return "jsp/product_list";
	}
}

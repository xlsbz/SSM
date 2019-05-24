package com.dhr.store.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dhr.store.domain.Product;
import com.dhr.store.service.ProductService;
import com.dhr.store.util.CartItem;
import com.dhr.store.util.CookieUtils;
import com.dhr.store.util.JsonUtils;
import com.dhr.store.util.StoreResult;

/**
 * @author Mr DU 购物车----cookie处理
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	// 注入商品服务
	@Autowired
	private ProductService productService;

	/**
	 * 添加到购物车
	 * 
	 * @param cartItem
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addCart(String pid, Integer quantity, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 1.获取要添加商品的数据
		// 2.查询商品封装到商品项
		// 获取商品cookie
		List<CartItem> cookieCart = getCookieCart(request);
		// 2.判断商品是否存在购物车中
		if (cookieCart != null && cookieCart.size() > 0) {
			boolean flag = false;
			for (CartItem item : cookieCart) {
				if (item.getPid().equals(pid)) {
					// 修改数量
					item.setQuantity(item.getQuantity() + quantity);
					flag = true;
					break;
				}
			}
			// 3.存在就修改数量
			if (flag) {
				CookieUtils.setCookie(request, response, "CART_COOKIE", JsonUtils.objectToJson(cookieCart), true);
			} else {
				// 4 .不存在就直接将商品添加到cookie
				// 根据商品id调用服务查询商品信息
				Product product = productService.findProductById(pid);
				CartItem cartItem = new CartItem();
				cartItem.setPid(product.getPid());
				cartItem.setProduct(product);
				// 设置数量
				cartItem.setQuantity(quantity);
				// 添加到商品集合
				cookieCart.add(cartItem);
				// 设置到cookie
				CookieUtils.setCookie(request, response, "CART_COOKIE", JsonUtils.objectToJson(cookieCart), true);
			}
		} else {
			Product product = productService.findProductById(pid);
			CartItem cartItem = new CartItem();
			cartItem.setPid(product.getPid());
			cartItem.setProduct(product);
			// 设置数量
			cartItem.setQuantity(quantity);
			List<CartItem> cartItems = new ArrayList<>();
			cartItems.add(cartItem);
			// 设置到cookie
			CookieUtils.setCookie(request, response, "CART_COOKIE", JsonUtils.objectToJson(cartItems), true);
		}

		return "redirect:/cart/showCart";
	}

	/**
	 * 移除商品
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/removeCart/{pid}")
	public String removeCart(@PathVariable String pid, HttpServletRequest request, HttpServletResponse response) {
		// 获取cookie
		List<CartItem> cookieCart = getCookieCart(request);
		if (cookieCart != null && cookieCart.size() > 0) {
			// 遍历购物车
			boolean flag = false;
			for (CartItem cartItem : cookieCart) {
				if (cartItem.getPid().equals(pid)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				Iterator<CartItem> iterator = cookieCart.iterator();
				while (iterator.hasNext()) {
					CartItem item = iterator.next();
					if (item.getPid().equals(pid)) {
						// 移除这个商品
						iterator.remove();
						break;
					}
				}
				// 重新设置cookie
				CookieUtils.setCookie(request, response, "CART_COOKIE", JsonUtils.objectToJson(cookieCart), true);
			}
		}
		return "redirect:/cart/showCart";
	}

	/**
	 * 更新购物车
	 * 
	 * @param pid
	 * @param quantity
	 * @return
	 */
	@RequestMapping("/updateCart/{pid}/{quantity}")
	@ResponseBody
	public StoreResult updateCart(@PathVariable String pid, @PathVariable Integer quantity, HttpServletRequest request,
			HttpServletResponse response) {
		// 获取购物车
		List<CartItem> cookieCart = getCookieCart(request);
		if (cookieCart != null && cookieCart.size() > 0) {
			// 根据商品ID查询出购物项
			Iterator<CartItem> iterator = cookieCart.iterator();
			while (iterator.hasNext()) {
				CartItem cartItem = iterator.next();
				if (cartItem.getPid().equals(pid)) {
					// 修改数量
					cartItem.setQuantity(quantity);
					break;
				}
			}
			// 回写cookie
			CookieUtils.setCookie(request, response, "CART_COOKIE", JsonUtils.objectToJson(cookieCart), true);
		}

		return StoreResult.ok();
	}

	/**
	 * 清除购物车
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		List<CartItem> cookieCart = getCookieCart(request);
		if (cookieCart != null && cookieCart.size() > 0) {
			cookieCart.clear();
		}
		CookieUtils.setCookie(request, response, "CART_COOKIE", JsonUtils.objectToJson(cookieCart), true);
		return "redirect:/cart/showCart";
	}

	/**
	 * 获取cookie
	 * 
	 * @return
	 */
	private List<CartItem> getCookieCart(HttpServletRequest request) {
		// 1.获得页面的cookie，从cookie中读取商品字符串
		String cookieValue = CookieUtils.getCookieValue(request, "CART_COOKIE", true);
		// 2.将字符串转换为对象
		List<CartItem> items = null;
		if ((cookieValue != null) && (!cookieValue.equals(""))) {
			items = new ArrayList<>();
			if ((cookieValue != null) && (!cookieValue.equals(""))) {
				items = JsonUtils.jsonToList(cookieValue, CartItem.class);
			}
		}
		// 3.返回
		return items;
	}

	/**
	 * 显示购物车
	 * 
	 * @return
	 */
	@RequestMapping("/showCart")
	public String showCart(HttpServletRequest request, Model model) {
		// 获取cookie购物车数据
		String cookieValue = CookieUtils.getCookieValue(request, "CART_COOKIE", true);
		if ((cookieValue != null) && (!cookieValue.equals(""))) {
			List<CartItem> jsonToList = JsonUtils.jsonToList(cookieValue, CartItem.class);
			if (jsonToList != null && jsonToList.size() > 0) {
				model.addAttribute("cartBean", jsonToList);
			} else {
				model.addAttribute("msg", "购物车还没有商品,快去购物吧!");
			}
		}
		return "jsp/cart";

	}
}

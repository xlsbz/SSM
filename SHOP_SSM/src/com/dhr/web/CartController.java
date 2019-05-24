package com.dhr.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dhr.domain.Product;
import com.dhr.service.ProductService;
import com.dhr.util.Cart;
import com.dhr.util.CartItem;

/**
 * 购物车处理
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController {
	// 注入product服务
	@Autowired
	private ProductService productService;

	/**
	 * 显示购物车
	 * 
	 * @return
	 */
	@RequestMapping("/showCart")
	public String showCart(HttpServletRequest request) {
		getCart(request);
		return "jsp/cart";
	}

	/**
	 * 添加到购物车
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addCart", method = RequestMethod.POST)
	public String addCart(Integer quantity, Integer pid, HttpServletResponse response, HttpServletRequest request) {
		// 获取数量,pid
		// 调用添加服务
		// 获取一个购物车
		Cart sessionCart = getCart(request);
		// 调用productService查询商品
		Product product = productService.findProductById(pid);
		// 创建购物项对象
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setNum(quantity);
		// 执行添加方法
		sessionCart.add2Cart(cartItem);
		request.getSession().setAttribute("cart", sessionCart);
		// 返回
		return "redirect:/cart/showCart";
	}

	/**
	 * 从购物车移除
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/removeCart/{pid}")
	public String removeCart(@PathVariable Integer pid, HttpServletRequest request) {
		// 调用服务
		// 获取购物车
		Cart cart = getCart(request);
		cart.remove2Cart(pid);
		return "redirect:/cart/showCart";
	}

	/**
	 * 清空购物车
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/clearCart")
	public String clearCart(HttpServletRequest request) {
		// 调用服务
		// 获取购物车
		Cart cart = getCart(request);
		cart.clearCart();
		return "redirect:/cart/showCart";
	}

	/**
	 * 获取购物车
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 1.判断session有没有购物车
		if (cart != null) {
			// 2.有就直接返回
			return cart;
		} else {
			// 3.没有创建购物车返回
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
			return cart;
		}
	}
}

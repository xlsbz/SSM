package com.dhr.store.web;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dhr.store.domain.Category;
import com.dhr.store.domain.Product;
import com.dhr.store.service.AdminCategoryService;
import com.dhr.store.service.AdminProductService;
import com.dhr.store.service.CategoryService;
import com.dhr.store.service.ProductService;
import com.dhr.store.util.StoreResult;
import com.dhr.store.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author Mr DU 商品管理
 */
@Controller
@RequestMapping("/admin")
public class AdminProductController {

	// 注入商品服务
	@Autowired
	private ProductService productService;

	@Autowired
	private AdminProductService adminService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AdminCategoryService adminCategoryService;

	/**
	 * 分页查询所有商品
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showProduct")
	public String showProduct(@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		// 注入服务
		// 设置pagehelper
		PageHelper.startPage(pageNumber, 10);
		// 查询商品
		List<Product> products = adminService.findAllProduct();
		// 设置到pageInfo
		PageInfo<Product> pageInfoProduct = new PageInfo<>(products);
		// 设置到域对象
		model.addAttribute("pageInfoProducts", pageInfoProduct);
		return "admin/product/list";
	}

	/**
	 * 查看下架商品
	 * 
	 * @param pageNumber
	 * @param model
	 * @return
	 */
	@RequestMapping("/showPushDownProduct")
	public String showPushDownProduct(@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		// 注入服务
		// 设置pagehelper
		PageHelper.startPage(pageNumber, 10);
		// 查询商品
		List<Product> products = adminService.findPushDownProduct();
		// 设置到pageInfo
		PageInfo<Product> pageInfoPushDownProducts = new PageInfo<>(products);
		// 设置到域对象
		model.addAttribute("pageInfoPushDownProducts", pageInfoPushDownProducts);
		return "admin/product/pushDown_list";
	}

	/**
	 * 下架商品
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping("/shutDownProduct/{pid}")
	@ResponseBody
	public StoreResult shutDownProduct(@PathVariable String pid) {

		adminService.shutDownProduct(pid);

		return StoreResult.ok("下架成功!");
	}

	/**
	 * 去添加商品页面
	 * 
	 * @return
	 */
	@RequestMapping("/toAddProduct")
	public String toAddProduct(Model model) {
		// 查询一级分类
		List<Category> findAllCategory = categoryService.findAllCategory();
		model.addAttribute("category", findAllCategory);
		return "admin/product/add";
	}

	/**
	 * 上架商品
	 * 
	 * @param file
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/addProduct")
	public String addProduct(MultipartFile file, Product product, HttpServletRequest req, Model model)
			throws IllegalStateException, IOException {
		// 1.提取request获取商品信息
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
		// 2.补全商品属性
		product.setPid(UUIDUtils.getId().substring(0, 6));
		product.setPdate(new Date());
		product.setPflag(0);
		// 3.上传图片
		String ranName = null;
		if (file != null) {
			// 获取商品名
			String fileName = file.getOriginalFilename();
			// 设置随机名
			ranName = UUIDUtils.getCode() + fileName;
			// 获取上传路径
			String realPath = request.getServletContext().getRealPath("/products/upload");
			System.out.println(realPath);
			File uploadFile = new File(realPath, ranName);
			file.transferTo(uploadFile);
		}
		// 4.设置图片路径
		product.setPimage("products/upload/" + ranName);

		adminService.saveProduct(product);

		// 5.返回
		return "redirect:/admin/showProduct";
	}

	/**
	 * 去编辑页面
	 */
	@RequestMapping("/toEditProduct/{pid}")
	public String toEditProduct(@PathVariable String pid, Model model) {
		// 查询该商品和一级分类
		Product product = productService.findProductById(pid);
		List<Category> category = categoryService.findAllCategory();
		model.addAttribute("category", category);
		model.addAttribute("productBean", product);
		return "admin/product/edit";
	}

	/**
	 * 修改商品
	 * 
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/updateProduct")
	public String updateProduct(MultipartFile file, Product product, HttpServletRequest req, Model model)
			throws IllegalStateException, IOException {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
		// 1.封装商品属性
		product.setPdate(new Date());
		// 3.上传商品图片
		if (file != null) {
			// 2.获取以前图片路径 删除商品图片
			String oldPimage = product.getPimage();
			String realPath = request.getServletContext().getRealPath("/");
			// 3.设置上传路径
			File newFile = new File(realPath, oldPimage);
			// 4.上传
			file.transferTo(newFile);
		}
		// 5.修改商品

		adminService.updateProduct(product);

		// 6.返回
		return "redirect:/admin/showProduct";
	}
}

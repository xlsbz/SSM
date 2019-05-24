package com.dhr.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dhr.domain.CategorySecond;
import com.dhr.domain.Product;
import com.dhr.service.AdminProductService;
import com.dhr.service.CategoryService;
import com.dhr.util.ShopResult;
import com.dhr.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 商品管理
 * 
 * @author Mr DU
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminProductController {

	@Autowired
	private AdminProductService adminProductService;

	@Autowired
	private CategoryService categoryService;

	/**
	 * 分页查询所有商品
	 * 
	 * @param pageNumer
	 * @return
	 */
	@RequestMapping("/findAllProduct")
	public String findAllProduct(@RequestParam(defaultValue = "1") Integer pageNumber, Model model) {
		// 1.注入服务
		// 2.设置pageHelper
		PageHelper.startPage(pageNumber, 10);
		// 3.调用查询方法
		List<Product> products = adminProductService.findAllProduct();
		// 4.封装到pageInfo
		PageInfo<Product> pageInfo = new PageInfo<>(products);
		// 5.设置到域对象
		model.addAttribute("pageInfoProduct", pageInfo);
		// 6.返回
		return "admin/product/list";
	}

	/**
	 * 删除商品
	 * 
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "delProductById/{pid}", produces = "application/json;charset=utf-8")
	@ResponseBody
	public ShopResult delProductById(@PathVariable Integer pid) {
		// 1.调用服务
		try {
			adminProductService.delProductById(pid);
			return new ShopResult("编号:" + pid + "删除成功!");
		} catch (Exception e) {
			return new ShopResult("删除失败,该商品是用户订单商品!");
		}
	}

	/**
	 * 去添加商品页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddProduct")
	public String toAddProductPage(Model model) {
		// 1.查询出所有二级分类
		List<CategorySecond> categorySecond = categoryService.findAllCategorySecond();
		// 2.设置到域对象
		model.addAttribute("categorySecond", categorySecond);
		// 3.返回
		return "admin/product/add";
	}

	/**
	 * 添加商品
	 * 
	 * @param uploadFile
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@RequestMapping("/addProduct")
	public String addProduct(@RequestParam(value = "uploadFile") MultipartFile uploadFile, HttpServletRequest request)
			throws FileNotFoundException, IOException {
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		// 获取页面参数
		String pname = req.getParameter("pname");
		Double market_price = Double.valueOf(req.getParameter("market_price"));
		Double shop_price = Double.valueOf(req.getParameter("shop_price"));
		String pdesc = req.getParameter("pdesc");
		int is_hot = Integer.parseInt(req.getParameter("is_hot"));
		int csid = Integer.parseInt(req.getParameter("csid"));
		// 处理上传图片
		String newName = null;
		if (uploadFile != null) {
			// 获取文件名
			String suffix = uploadFile.getOriginalFilename();
			// 生成随机名
			newName = UUIDUtils.randomNumber() + suffix;
			// 获取文件上传真实途径
			String realPath = request.getServletContext().getRealPath("image");
			FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(realPath + "/products/" + newName));
			// 备份一份
			String newFile = "D:\\workspaceEclips2018\\SHOP_SSM\\WebContent\\image";
			FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(newFile + "/products/" + newName));
		}
		Product product = new Product();
		product.setPname(pname);
		product.setMarket_price(market_price);
		product.setShop_price(shop_price);
		product.setPdate(new Date());
		product.setIs_hot(is_hot);
		product.setPdesc(pdesc);
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		// 设置图片路径
		product.setImage("products/" + newName);

		// 调用service 添加文件
		adminProductService.addProduct(product);
		return "redirect:/admin/findAllProduct";
	}

	/**
	 * 去编辑页面
	 * 
	 * @param pid
	 * @param model
	 * @return
	 */
	@RequestMapping("/toEditProduct/{pid}")
	public String toEditProduct(@PathVariable Integer pid, Model model) {
		// 获取商品id
		// 调用方法查询商品
		Product product = adminProductService.findProductById(pid);
		// 查询所有二级分类
		List<CategorySecond> categorySecond = categoryService.findAllCategorySecond();
		// 设置到域
		model.addAttribute("productBean", product);
		model.addAttribute("categorySecond", categorySecond);
		// 返回
		return "admin/product/edit";
	}

	/**
	 * 修改商品
	 * 
	 * @param pid
	 * @param uploadFile
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updateProduct/{pid}")
	public String updateProduct(@PathVariable Integer pid, @RequestParam(value = "uploadFile") MultipartFile uploadFile,
			HttpServletRequest request) throws IOException {
		// 获取商品属性
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		String pname = req.getParameter("pname");
		Double market_price = Double.valueOf(req.getParameter("market_price"));
		Double shop_price = Double.valueOf(req.getParameter("shop_price"));
		String pdesc = req.getParameter("pdesc");
		int is_hot = Integer.parseInt(req.getParameter("is_hot"));
		int csid = Integer.parseInt(req.getParameter("csid"));
		String image = req.getParameter("image");
		// 处理图片上传
		// 删除原来的商品图片
		String realPath = req.getServletContext().getRealPath("image");
		File file = new File(realPath + "/" + image);
		file.delete();
		// 上传图片
		String newFileName = null;
		if (uploadFile != null) {
			// 获取文件名
			String suffix = uploadFile.getOriginalFilename();
			// 随机名
			newFileName = UUIDUtils.randomNumber() + suffix;
			// 上传
			// InputStream inputStream = uploadFile.getInputStream();
			// OutputStream outputStream = new FileOutputStream(realPath + "/products" +
			// newFileName);
			// int i = 0;
			// while ((i = inputStream.read()) != -1) {
			// outputStream.write(i);
			// }
			// outputStream.flush();
			// outputStream.close();
			// inputStream.close();
			uploadFile.transferTo(new File(realPath + "/products/" + newFileName));
			// 备份一份
			String newFile = "D:\\workspaceEclips2018\\SHOP_SSM\\WebContent\\image\\products\\";
			uploadFile.transferTo(new File(newFile + newFileName));
		}
		// 封装product
		Product product = new Product();
		product.setPid(pid);
		product.setPname(pname);
		product.setMarket_price(market_price);
		product.setShop_price(shop_price);
		product.setPdate(new Date());
		product.setIs_hot(is_hot);
		product.setPdesc(pdesc);
		CategorySecond categorySecond = new CategorySecond();
		categorySecond.setCsid(csid);
		product.setCategorySecond(categorySecond);
		// 设置图片路径
		product.setImage("products/" + newFileName);

		// 调用服务修改
		adminProductService.updateProduct(product);
		return "redirect:/admin/findAllProduct";
	}
}

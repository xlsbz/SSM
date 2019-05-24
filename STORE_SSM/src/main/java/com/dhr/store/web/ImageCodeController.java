package com.dhr.store.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证码
 * 
 * @author Mr DU
 *
 */
@Controller
public class ImageCodeController {

	@RequestMapping("/imageCode")
	@ResponseBody
	public String genVerifiationCode(HttpServletRequest req, HttpServletResponse res) {
		int width = 120;
		int height = 70;
		// 1.创建图片流对象
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 2.获得画笔
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		// 3.设置图片边框，颜色
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, width, height);

		// 4.设置验证字符
		// 生成随机字符四个
		graphics.setFont(new Font(null, Font.BOLD, 22));
		// 保存验证码设置到session
		StringBuffer code = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			Random random = new Random();
			graphics.setColor(Color.WHITE);
			int y = random.nextInt(10);
			int x = random.nextInt(10);
			String c = genRannum(4);
			code.append(c);
			graphics.drawString(c, x + i * 20, 30 + y);
		}
		System.out.println(code);
		req.getSession().setAttribute("code", code);
		// 5.设置干扰线
		for (int i = 0; i < 8; i++) {
			Random r = new Random();
			int x1 = r.nextInt(width);
			int x2 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int y2 = r.nextInt(height);
			graphics.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			graphics.drawLine(x1, y1, x2, y2);
		}
		// 关闭画笔
		graphics.dispose();
		// 设置浏览器不缓存
		res.setDateHeader("expries", -1);
		res.setHeader("Cache-Control", "no-cache");
		res.setHeader("Pragma", "no-cache");
		// 6.设置图片类型
		res.setContentType("image/jpeg");
		try {
			ImageIO.write(image, "jpg", res.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成随机字符
	 * 
	 * @return
	 */
	private static String genRannum(int digit) {
		String num = "1234567890qazwsxedcrfvtgbyhnujmikolp";
		char code = 0;
		Random random = new Random();
		for (int i = 0; i < digit; i++) {
			int dom = random.nextInt(num.length() - 1);
			code = num.charAt(dom);
		}
		return String.valueOf(code);
	}
}

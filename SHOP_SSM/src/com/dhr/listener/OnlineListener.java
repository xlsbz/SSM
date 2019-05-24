package com.dhr.listener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.dhr.domain.User;

/**
 * 监听在线人数
 */
@WebListener
public class OnlineListener implements ServletContextListener, HttpSessionAttributeListener {

	ServletContext servletContext = null;

	private static Integer peopleCount = 0;

	/**
	 * 初始化容器存放在线人数
	 */
	public void contextInitialized(ServletContextEvent context) {
		servletContext = context.getServletContext();
		List<String> online = new ArrayList<>();
		servletContext.setAttribute("online", online);
		// 本站累计访问
		try {
			Reader r = new FileReader(new File(
					"D:\\apache-tomcat-8.5.39\\webapps\\SHOP_SSM\\WEB-INF\\classes\\properties\\count.properties"));
			BufferedReader reader = new BufferedReader(r);
			int count = 0;
			String line = null;
			while ((line = reader.readLine()) != null) {
				count = Integer.parseInt(line);
			}
			peopleCount += count;
			servletContext.setAttribute("countWebSite", peopleCount);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		try {
			Writer w = new FileWriter(new File(
					"D:\\apache-tomcat-8.5.39\\webapps\\SHOP_SSM\\WEB-INF\\classes\\properties\\count.properties"));
			BufferedWriter writer = new BufferedWriter(w);
			writer.write(String.valueOf(servletContext.getAttribute("countWebSite")));
			System.out.println("写入成功!");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户登录触发此事件
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		// 获取online在线人数
		List<String> online = (List<String>) servletContext.getAttribute("online");
		if (event.getSession().getAttribute("user") != null) {
			User user = (User) event.getSession().getAttribute("user");
			online.add(user.getUsername());
			// 重新设置online
			servletContext.setAttribute("online", online);
			// 访问人数
			peopleCount += 1;
			servletContext.setAttribute("countWebSite", peopleCount);
		}
	}

	/**
	 * 退出登录触发
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		try {
			// 获得被移除的对象
			User user = (User) event.getValue();
			String username = user.getUsername();
			// 获取online在线人数
			List<String> online = (List<String>) servletContext.getAttribute("online");
			if (online != null && online.size() > 0) {
				for (String u : online) {
					if (username.equals(u)) {
						online.remove(username);
					}
				}
				// 重新设置online
				servletContext.setAttribute("online", online);
			}
		} catch (Exception e) {
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
	}

}

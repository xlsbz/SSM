package com.dhr.jedis;
/**
 * redis操作类
 * @author Mr DU
 *
 */

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 实现基本方法
 * 
 * @author Mr DU
 *
 */
public class RedisClient extends Jedis {

	/**
	 * jedsis链接
	 */
	@Test
	public void jedisTest() {
		Jedis jedis = new Jedis("192.168.25.128", 6379);
		jedis.select(3);
		jedis.set("demo", "SHOP_SSM!");
		System.out.println(jedis.get("demo"));
		jedis.close();
	}

	/**
	 * jedis连接池
	 */
	@Test
	public void jedisPoolTest() {
		JedisPool jedisPool = new JedisPool("192.168.25.128", 6379);
		Jedis jedis = jedisPool.getResource();
		// Jedis jedis = new Jedis();
		jedis.select(3);
		jedis.set("demo", "SHOP_SSM!!");
		System.out.println(jedis.get("demo"));
		jedis.close();
	}

	@Autowired
	private JedisClient jedisClient;

	/**
	 * 整合spring
	 */
	@Test
	public void jedisSpringTest() {
		// 注入接口
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring/applicationContext-redis.xml");
		JedisClientImpl bean = context.getBean(JedisClientImpl.class);
		System.out.println(bean.set("aaa", "aaaa"));
		System.out.println(bean.get("aaa"));
		// jedisClient.set("spring", "jedis");
		// System.out.println(jedisClient.get("spring"));

	}
}

package com.dhr.jedis;

import javax.annotation.Resource;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 实现类
 * 
 * @author Mr DU
 *
 */
public class JedisClientImpl implements JedisClient {
	// 注入jedis服务
	@Resource
	private JedisPool jedisPool;

	@Override
	public String set(String key, String value) {
		System.out.println(jedisPool);
		Jedis jedis = jedisPool.getResource();
		String set = jedis.set(key, value);
		jedis.close();
		return set;
	}

	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.exists(key);
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.expire(key, seconds);
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.ttl(key);
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.incr(key);
	}

	@Override
	public Long hset(String key, String filed, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.hset(key, filed, value);
	}

	@Override
	public String hget(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.hget(key, value);
	}

	@Override
	public Long del(String key, String... filed) {
		Jedis jedis = jedisPool.getResource();
		jedis.close();
		return jedis.del(key);
	}

}

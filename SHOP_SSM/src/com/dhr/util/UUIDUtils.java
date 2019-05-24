package com.dhr.util;

import java.util.Random;
import java.util.UUID;

/**
 * 随机字符
 * 
 * @author Mr DU
 *
 */
public class UUIDUtils {

	// 随机码
	public static String randomNumber() {
		String number = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return number;
	}

	// 当前时间戳
	public static String timeNumber() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			i++;
		}
		long end = System.currentTimeMillis();
		return String.valueOf(start + new Random().nextInt(1000) + end);
	}

	public static void main(String[] args) {
		System.out.println(UUIDUtils.timeNumber());
	}
}

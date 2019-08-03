package com.auzmor.backendserver.cache;

import redis.clients.jedis.Jedis;

public class RedisConfiguration {

	private static Jedis conn;
	
	public static Jedis redisConn() {

		if (conn == null) {
			conn = new Jedis();
		}
		return conn;
	}

	public static void addData(String key, String value) {
		Jedis jedis = redisConn();
		jedis.set(key, value);
	}
	
	public static void addData(String key, String value, int expiryValue) {
		Jedis jedis = redisConn();
		jedis.set(key, value);
		jedis.expire(key, expiryValue);
	}
	
	

	public static String getValue(String key) {
		Jedis jedis = redisConn();
		String value = jedis.get(key);
		return value;
	}
	
	public static long getTTL(String key) {
		Jedis jedis = redisConn();
		long value = jedis.ttl(key);
		return value;
	}

}

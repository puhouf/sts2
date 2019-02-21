package com.atguigu.jedis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

class TestJedis {

	@Test
	public void testConnect() {
		Jedis jedis = new Jedis("192.168.6.12",6381);
		
		String pong = jedis.ping();
		
		System.out.println(pong);
		
	}
	
	@Test
	public void testKeys() {
		Jedis jedis = new Jedis("192.168.6.12",6380);
		
		Set<String> keys = jedis.keys("*");
		
		for(String key : keys ) {
			System.out.println(key);
		}
		
		System.out.println("是否存在k2: " + jedis.exists("k1"));
		
		System.out.println("k1的存活时间: " + jedis.ttl("k1"));
		
		jedis.close();
		
	}
	
	@Test
	public void testString() {
		
		Jedis jedis = new Jedis("192.168.6.12",6380);
		
		System.out.println("获取k1的值: " + jedis.get("k1"));
		
		jedis.msetnx("k2","v22","k3","v3","k4","v4");
		
		System.out.println(jedis.mget("k2","k3","k4"));
		
		jedis.close();
		
		
	}
	
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.6.12",6380);
		
		Long lpush = jedis.lpush("list2", "A","B","C","D");
		
		System.out.println("lpush = " + lpush);
		
		List<String> list = jedis.lrange("list2", 0, -1);
		
		for(String element : list) {
			System.out.println(element);
			
		}
		
		jedis.close();
		
	}
	
	
	@Test
	public void testSet() {
		
		Jedis jedis = new Jedis("192.168.6.12",6380);
		
		jedis.sadd("myset", "tony","marry","kiki","mimi");
		
		jedis.srem("myset", "kiki");
		
		Set<String> myset = jedis.smembers("myset");
		
		for(String member : myset) {
			
			System.out.println(member);
			
		}
		
		jedis.close();
		
	}
	
	@Test
	public void testHash() {
		
		Jedis jedis = new Jedis("192.168.6.12",6380);
		
		jedis.hset("myhash","username","Jack");
		
		jedis.hset("myhash", "password", "123123");
		
		jedis.hset("myhash", "age", "11");
		
		HashMap<String, String> map = new HashMap<String,String>();
		
		map.put("gender", "18");
		
		map.put("department","研发部");
		
		jedis.hmset("myhash", map);
		
		List<String> values = jedis.hmget("myhash", "username","department");
		
		for(String val : values) {
			System.out.println(val);
		}
		
		jedis.close();
		
		
	}
	
	@Test
	public void testZset() {
		
		Jedis jedis = new Jedis("192.168.6.12",6380);
		
		jedis.zadd("myZset", 100, "math");
		
		Map<String, Double> subject = new HashMap<String,Double>();
		
		subject.put("English", 79d);
		
		subject.put("Chinese", 99d);
		
		jedis.zadd("myZset", subject);
		
		Set<String> myZset = jedis.zrange("myZset", 0, -1);
		
		for(String val : myZset	) {
			System.out.println(val);
		}
		
		
		jedis.close();
		
	}
	
	@Test
	public void testPool() {
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		JedisPool jedisPool = new JedisPool(poolConfig, "192.168.6.12",6380);
		
		Jedis jedis = jedisPool.getResource();
		
		String pong = jedis.ping();
		
		System.out.println(pong);
		
		jedis.close();

		jedisPool.close();
		
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

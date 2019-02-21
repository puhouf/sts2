package com.atguigu.jedis.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

class TestSentinel {

	@Test
	public void testSentinel() throws Exception {
		Set<String> set = new HashSet<>();
		// set中放的是哨兵的Ip和端口
		set.add("192.168.6.12:26379");
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", set, poolConfig, 60000);
		Jedis jedis = jedisSentinelPool.getResource();
		String value = jedis.get("k7");
		jedis.set("Jedis2", "Jedis");
		System.out.println(value);
	}


}

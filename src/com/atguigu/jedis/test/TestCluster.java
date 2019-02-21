package com.atguigu.jedis.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

class TestCluster {

	@Test
	public void testCluster(){
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		//Jedis Cluster will attempt to discover cluster nodes automatically
		jedisClusterNodes.add(new HostAndPort("192.168.6.12", 6379));
		JedisCluster jc = new JedisCluster(jedisClusterNodes);
		jc.set("foo22", "bar");
		String value = jc.get("foo");
		System.out.println(value);
		System.out.println("update by remote git");
		System.out.println("update by local");
	}


}

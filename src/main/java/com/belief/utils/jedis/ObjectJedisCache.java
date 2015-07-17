package com.belief.utils.jedis;

import redis.clients.jedis.ShardedJedisPool;

public class ObjectJedisCache extends AbstractJedisCache {

	public ObjectJedisCache(String appName, ShardedJedisPool shardedJedisPool, String nameSpace) {
		super(appName, shardedJedisPool, nameSpace);
	}

	public ObjectJedisCache(String appName, ShardedJedisPool shardedJedisPool, int period, String nameSpace) {
		super(appName, shardedJedisPool, period, nameSpace);
	}

	@Override
	public void clearKey(String key) {
	}

}

package com.belief.utils.jedis;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 字符串缓存
 * 
 * @author yudongwei
 *
 */
public class StringJedisCache extends AbstractJedisCache {

	public StringJedisCache(String appName, ShardedJedisPool shardedJedisPool, String nameSpace) {
		super(appName, shardedJedisPool, nameSpace);
	}

	public StringJedisCache(String appName, ShardedJedisPool shardedJedisPool, int period, String nameSpace) {
		super(appName, shardedJedisPool, period, nameSpace);
	}

	public boolean setData(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			if (period > 0) {
				jedis.setex(appName + "_" + nameSpace + "_" + key, period, value);
			} else {
				jedis.set(appName + "_" + nameSpace + "_" + key, value);
			}
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			super.returnResource(jedis);
		}
		return false;
	}

	public String getData(String key) {
		String value = null;
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			value = jedis.get(appName + "_" + nameSpace + "_" + key);
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			super.returnResource(jedis);
		}
		return value;
	}

	/**
	 * 对于字符串来说，简单的清理即可
	 */
	@Override
	public void clearKey(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			jedis.del(appName + "_" + nameSpace + "_" + key);
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			super.returnResource(jedis);
		}
	}

}

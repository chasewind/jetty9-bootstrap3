package com.belief.utils.jedis;

import java.util.Set;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Set集合缓存
 * 
 * @author yudongwei
 *
 */
public class SetJedisCache extends AbstractJedisCache {

	public SetJedisCache(String appName, ShardedJedisPool shardedJedisPool, String nameSpace) {
		super(appName, shardedJedisPool, nameSpace);
	}

	public SetJedisCache(String appName, ShardedJedisPool shardedJedisPool, int period, String nameSpace) {
		super(appName, shardedJedisPool, period, nameSpace);
	}

	/**
	 * 获取集合里所有元素
	 * 
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key) {
		ShardedJedis jedis = null;
		Set<String> set = null;
		try {
			jedis = super.getShardedJedis();
			set = jedis.smembers(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			super.returnResource(jedis);
		}
		return set;
	}

	/**
	 * 向指定key对应的集合中添加数据
	 * 
	 * @param key
	 * @param value
	 */
	public void sadd(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			jedis.sadd(key, value);
			if (period > 0) {
				jedis.expire(key, period);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			super.returnResource(jedis);
		}
	}

	/**
	 * 指定key对应的集合size
	 * 
	 * @param key
	 * @return
	 */
	public Long scard(String key) {
		long value = 0;
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			return jedis.scard(key);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			super.returnResource(jedis);
		}
		return value;
	}

	/**
	 * 指定的集合内是否包含对应的元素
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean contains(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			return jedis.sismember(key, value);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			super.returnResource(jedis);
		}
		return false;
	}

	/**
	 * 对于集合来说，整个集合里的数据都会被清理
	 */
	@Override
	public void clearKey(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = super.getShardedJedis();
			Set<String> set = this.smembers(key);
			if (set != null && set.size() > 0) {
				for (String value : set) {
					jedis.srem(key, value);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			super.returnResource(jedis);
		}
	}

}

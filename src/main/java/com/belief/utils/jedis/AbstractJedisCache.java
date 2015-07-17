package com.belief.utils.jedis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * Jedis优化方案，针对不同的类型对数据进行封装,使得调用结构对称
 * 
 * @author yudongwei
 *
 */
public abstract class AbstractJedisCache {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/** 对应项目名称 */
	protected String appName;
	/** jedis实例池，子类不可访问 */
	private ShardedJedisPool shardedJedisPool;
	/** 有效期，单位：秒 */
	protected int period;
	/** 自定义的命名空间，请确保唯一性 */
	protected String nameSpace;

	/**
	 * 构造函数约束，根据缓存策略自动被清理
	 * 
	 * @param appName
	 * @param shardedJedisPool
	 * @param nameSpace
	 */
	public AbstractJedisCache(String appName, ShardedJedisPool shardedJedisPool, String nameSpace) {
		this.appName = appName;
		this.shardedJedisPool = shardedJedisPool;
		this.nameSpace = nameSpace;
	}

	/**
	 * 构造函数约束，根据有效期清理
	 * 
	 * @param appName
	 * @param shardedJedisPool
	 * @param period
	 * @param nameSpace
	 */
	public AbstractJedisCache(String appName, ShardedJedisPool shardedJedisPool, int period, String nameSpace) {
		this(appName, shardedJedisPool, nameSpace);
		this.period = period;
	}

	/**
	 * 获取jedis实例
	 * 
	 * @return
	 */
	public final ShardedJedis getShardedJedis() {
		return shardedJedisPool.getResource();
	}

	/**
	 * 归还jedis实例
	 * 
	 * @param shardedJedis
	 */
	public final void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	/** 清理指定的key对应的数据 */
	public abstract void clearKey(String key);

	/** 清理指定的一系列key对应的数据 */
	public final void clearKeys(List<String> keys) {
		if (keys != null && keys.size() > 0) {
			for (String key : keys) {
				this.clearKey(key);
			}
		}
	}
}

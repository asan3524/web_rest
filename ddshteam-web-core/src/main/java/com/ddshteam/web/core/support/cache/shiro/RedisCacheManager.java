/**
 * 
 */
package com.ddshteam.web.core.support.cache.shiro;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddshteam.web.core.Constants;



@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisCacheManager implements CacheManager {
	private final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

	// fast lookup by name map
	private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
	/**
	 * The Redis key prefix for caches
	 */
	private String keyPrefix = Constants.CACHE_NAMESPACE + "shiro_redis_cache:";

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix
	 *            The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.cache.CacheManager#getCache(java.lang.String)
	 */
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.debug("获取名称为: " + name + " 的RedisCache实例");

		Cache c = caches.get(name);

		if (c == null) {
			// create a new cache instance
			c = new RedisCache<K, V>(keyPrefix);
			// add it to the cache collection
			caches.put(name, c);
		}
		return c;
	}

}

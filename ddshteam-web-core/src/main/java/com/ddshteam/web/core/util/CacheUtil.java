package com.ddshteam.web.core.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.ddshteam.web.core.Constants;
import com.ddshteam.web.core.support.cache.CacheManager;
import com.ddshteam.web.core.support.cache.RedissonHelper;


@Configuration
public class CacheUtil {
	private static Logger logger = LoggerFactory.getLogger(CacheUtil.class);
	
	//private static org.apache.log4j.Logger logger2 = LogManager.getLogger(CacheManager.class);
	
	private static CacheManager cacheManager;

	public static void setCacheManager(CacheManager cacheManager) {
		CacheUtil.cacheManager = cacheManager;
	}

	public static CacheManager getCache() {
		if (cacheManager == null) {
			synchronized (CacheUtil.class) {
				if (cacheManager == null) {
					cacheManager = new RedissonHelper();
				}
			}
		}
		return cacheManager;
	}

	/** 获取锁 */
	public static boolean getLock(String key) {
		try {
			if (!getCache().exists(key)) {
				synchronized (CacheUtil.class) {
					if (!getCache().exists(key)) {
						if (getCache().setnx(key, System.currentTimeMillis())) {
							return true;
						}
					}
				}
			}
			int expires = 1000 * 60 * 3;
			String currentValue = String.valueOf(getCache().get(key));
			if (currentValue != null && Long.parseLong(currentValue) < System.currentTimeMillis() - expires) {
				unlock(key);
				return getLock(key);
			}
			return false;
		} catch (Exception e) {
			logger.error(Constants.Exception_Head, e);
			return false;
		}
	}

	public static void unlock(String key) {
		getCache().unlock(key);
	}
}

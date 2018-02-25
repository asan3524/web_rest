package com.ddshteam.web.core.util;

import com.ddshteam.web.core.support.id.IdWorker;

/**
 * Id生成器
 *
 */
public class IdUtil {
	private static IdWorker idWorker = new IdWorker(1);

    private IdUtil() {}

    public static Long generateId() {
        return idWorker.nextId();
    }
}	

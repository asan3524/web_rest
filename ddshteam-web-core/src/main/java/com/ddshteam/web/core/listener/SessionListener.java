package com.ddshteam.web.core.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ddshteam.web.core.Constants;
import com.ddshteam.web.core.util.CacheUtil;



/**
 * 会话监听器
 * 
 */
public class SessionListener implements HttpSessionListener {
	
	private Logger logger = LoggerFactory.getLogger(SessionListener.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		session.setAttribute(Constants.WEBTHEME, "default");
		logger.info("创建了一个Session连接:[" + session.getId() + "]");
		CacheUtil.getCache().sadd(Constants.ALLUSER_NUMBER, session.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
	 * .http.HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		if (getAllUserNumber() > 0) {
			logger.info("销毁了一个Session连接:[" + session.getId() + "]");
		}
		session.removeAttribute(Constants.CURRENT_USER);
		CacheUtil.getCache().sdel(Constants.ALLUSER_NUMBER, session.getId());
	}

	/** 获取在线用户数量 */
	public Integer getAllUserNumber() {
		return CacheUtil.getCache().sall(Constants.ALLUSER_NUMBER).size();
	}
}

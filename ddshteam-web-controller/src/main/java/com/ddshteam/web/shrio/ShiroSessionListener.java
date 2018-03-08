package com.ddshteam.web.shrio;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroSessionListener implements SessionListener {

	private final static Logger logger = LoggerFactory.getLogger(SessionListener.class);

	@Override
	public void onStart(Session session) {
		logger.info("会话创建: " + session.getHost());
	}

	@Override
	public void onStop(Session session) {
		logger.info("会话停止: " + session.getHost());
	}

	@Override
	public void onExpiration(Session session) {
		logger.info("会话过期: " + session.getHost());
	}

}

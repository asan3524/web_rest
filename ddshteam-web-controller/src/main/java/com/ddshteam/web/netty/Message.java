package com.ddshteam.web.netty;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String account;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Message [account=" + account + "]";
	}
}

package com.ddshteam.web.netty;

import java.io.Serializable;

/**
 * 通知的消息对象
 * @ClassName: Message
 * @author lishibang
 * @date 2018年8月6日 下午3:44:40
 * @version v1.0.0
 * 
 */
public class Message implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public Message(String account) {
		// TODO Auto-generated constructor stub
		this.account = account;
	}

	public static enum MSGTYPE {
		/** 新增消息 */
		NEW,
		/** 老旧消息 */
		OLD,
		/** 删除需要清理的消息 */
		DELETE
	}

	/**
	 * 当前业务场景下，此消息需要发送的用户账号，此处需要结合token使用
	 * @Fields account
	 */
	protected String account;
	protected MSGTYPE msgType = MSGTYPE.NEW;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public MSGTYPE getMsgType() {
		return msgType;
	}

	public void setMsgType(MSGTYPE msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "Message [account=" + account + ", msgType=" + msgType + "]";
	}
}

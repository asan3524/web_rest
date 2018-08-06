package com.ddshteam.web.netty;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 用一句话描述该文件做什么
 * @ClassName: NettyConfig
 * @author lishibang
 * @date 2018年8月4日 下午9:27:05
 * @version v1.0.0
 * 
 */
public class NettyConfig {
	public static ConcurrentHashMap<String, LinkedList<ChannelId>> accountKey = new ConcurrentHashMap<String, LinkedList<ChannelId>>();

	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	/**
	* 无界线程安全队列，后续考虑优化成有界队列
	* @Fields msgLink
	*/
	public static ConcurrentLinkedQueue<Message> msgLink = new ConcurrentLinkedQueue<Message>();
}

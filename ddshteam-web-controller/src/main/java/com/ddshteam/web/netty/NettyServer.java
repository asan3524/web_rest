package com.ddshteam.web.netty;

import java.util.Iterator;
import java.util.LinkedList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

@Configuration
@Component
public class NettyServer {

	private final static Logger logger = LoggerFactory.getLogger(NettyServer.class);

	@Value("${netty.server.port}")
	private Integer port = 8888;

	@Value("${netty.server.url}")
	private String url = "ws://localhost/ws";

	@Value("${netty.send.delay}")
	private Integer delay = 5000;

	@Value("${netty.server.enable}")
	private Integer enable = 0;

	private Channel channel;
	private ChannelFuture future;
	// 优化处理，bossGroup仅负责接收客户端连接，不做逻辑，取值越小越好
	private final EventLoopGroup boss = new NioEventLoopGroup(1);
	// 优化处理，默认值为CPU线程数*2
	// 并发较少时1<=N<=CPU线程数；并发大时需要根据并发连接数动态调节
	// 如果是客户端的workGroup，评估客户端连接数，创建一个大的池所有客户端连接大量服务提供者共用
	// 或者创建workGroup数组，不同客户端通过hash散列值打散使用
	private final EventLoopGroup work = new NioEventLoopGroup();

	public void start() {
		ServerBootstrap bootstrap = new ServerBootstrap();
		// 在linux系统环境下，应该考虑是使用epoll模式
		// EpollEventLoopGroup+EpollServerSocketChannel
		bootstrap.group(boss, work).channel(NioServerSocketChannel.class)
				.childHandler(new WebSocketChannelInitializer(url)).option(ChannelOption.SO_BACKLOG, 1024)
				.childOption(ChannelOption.SO_KEEPALIVE, true);
		future = bootstrap.bind(port);
		future.syncUninterruptibly();
		channel = future.channel();
		logger.info("Netty服务器启动成功!端口:" + port);
	}

	@PreDestroy
	public void destory() {

		future.channel().closeFuture().syncUninterruptibly();
		if (null != channel) {
			channel.close();
		}
		work.shutdownGracefully();
		boss.shutdownGracefully();
	}

	@PostConstruct
	public void init() {

		if (enable != 1) {
			return;
		}
		new Thread(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				while (true) {
					try {
						if (!NettyConfig.group.isEmpty() && !NettyConfig.msgLink.isEmpty()) {
							Message msg = NettyConfig.msgLink.poll();

							if (NettyConfig.accountKey.containsKey(msg.getAccount())) {
								TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(msg.toString());
								LinkedList<ChannelId> ids = NettyConfig.accountKey.get(msg.getAccount());
								if (null != ids && !ids.isEmpty()) {
									Iterator<ChannelId> iterator = ids.iterator();
									while (iterator.hasNext()) {
										Channel channel = NettyConfig.group.find(iterator.next());
										if (null != channel && channel.isWritable()) {
											channel.writeAndFlush(textWebSocketFrame.copy());
										}
									}
								}
							}
						}
						Thread.currentThread().sleep(delay);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("Netty服务器发送进程异常" + e.getMessage());
					}
				}
			}
		}).start();

		start();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				destory();
			}
		});
	}
}

package com.ddshteam.web.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

	private String url;

	public WebSocketChannelInitializer(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}

	@Override
	protected void initChannel(SocketChannel e) throws Exception {
		ChannelPipeline pipeline = e.pipeline();
		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new ChunkedWriteHandler());
		pipeline.addLast(new HttpObjectAggregator(64 * 1024));
		pipeline.addLast(new MessageChannelHandler(url));
		pipeline.addLast(new MessageSendHandler());
	}
}

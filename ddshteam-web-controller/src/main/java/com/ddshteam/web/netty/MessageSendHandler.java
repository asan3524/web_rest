package com.ddshteam.web.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class MessageSendHandler extends ChannelOutboundHandlerAdapter {

	private final static Logger logger = LoggerFactory.getLogger(MessageSendHandler.class);

	@Override
	public void write(ChannelHandlerContext context, Object msg, ChannelPromise promise) throws Exception {

		// if (msg instanceof byte[]) {
		// byte[] bytesWrite = (byte[]) msg;
		// ByteBuf buf = context.alloc().buffer(bytesWrite.length);
		//
		// buf.writeBytes(bytesWrite);

		context.writeAndFlush(msg).addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				logger.info(context.channel().id() + "下发成功" + msg.toString());
			}
		});
		// }
	}
}

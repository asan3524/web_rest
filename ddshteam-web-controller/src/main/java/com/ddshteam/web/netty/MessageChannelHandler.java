package com.ddshteam.web.netty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ddshteam.web.controller.util.JwtTokenUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

public class MessageChannelHandler extends SimpleChannelInboundHandler<Object> {

	public MessageChannelHandler(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}

	private String url;
	private WebSocketServerHandshaker handshaker;

	@Override
	public void channelActive(ChannelHandlerContext context) throws Exception {
		NettyConfig.group.add(context.channel());
	}

	@Override
	public void channelInactive(ChannelHandlerContext context) throws Exception {
		NettyConfig.group.remove(context.channel());
		NettyConfig.accountKey.values().remove(context.channel().id());
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext context) throws Exception {
		context.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable throwable) throws Exception {
		throwable.printStackTrace();
		context.close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext context, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent stateEvent = (IdleStateEvent) evt;
			PingWebSocketFrame ping = new PingWebSocketFrame();
			switch (stateEvent.state()) {
			case READER_IDLE:
				context.writeAndFlush(ping);
				break;
			case WRITER_IDLE:
				context.writeAndFlush(ping);
				break;
			case ALL_IDLE:
				break;
			}
		}
	}

	@Override
	protected void channelRead0(ChannelHandlerContext context, Object msg) throws Exception {
		// TODO Auto-generated method stub
		if (msg instanceof FullHttpRequest) {
			doHandlerHttpRequest(context, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			doHandlerWebSocketFrame(context, (WebSocketFrame) msg);
		}
	}

	private void doHandlerHttpRequest(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) {
		if (!fullHttpRequest.decoderResult().isSuccess()
				|| (!"websocket".equals(fullHttpRequest.headers().get("Upgrade")))) {
			sendHttpResponse(context, fullHttpRequest,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
		}
		// 只处理GET
		if (fullHttpRequest.method().equals(HttpMethod.GET)) {
			QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());
			Map<String, List<String>> parame = decoder.parameters();
			List<String> token = parame.get("token");
			if (null == token || token.isEmpty() || null == token.get(0) || "".equals(token.get(0))) {
				sendHttpResponse(context, fullHttpRequest,
						new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			} else {
				String account = JwtTokenUtil.verifyToken(token.get(0));
				// 测试代码
				// String account = token.get(0);
				if (null == account) {
					sendHttpResponse(context, fullHttpRequest,
							new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
				} else {
					if (NettyConfig.accountKey.contains(account)) {
						NettyConfig.accountKey.get(account).add(context.channel().id());
					} else {
						List<ChannelId> channels = new ArrayList<ChannelId>();
						channels.add(context.channel().id());
						NettyConfig.accountKey.put(account, channels);
					}
				}
			}
		} else {
			sendHttpResponse(context, fullHttpRequest,
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
		}

		WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(url, null, false);
		handshaker = factory.newHandshaker(fullHttpRequest);
		if (null == handshaker) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
		} else {
			handshaker.handshake(context.channel(), fullHttpRequest);
		}
	}

	private void doHandlerWebSocketFrame(ChannelHandlerContext context, WebSocketFrame webSocketFrame) {
		if (webSocketFrame instanceof CloseWebSocketFrame) {
			handshaker.close(context.channel(), (CloseWebSocketFrame) webSocketFrame.retain());
			return;
		}
		if (webSocketFrame instanceof PingWebSocketFrame) {
			context.channel().write(new PongWebSocketFrame(webSocketFrame.content().retain()));
			return;
		}
		if (webSocketFrame instanceof PongWebSocketFrame) {
			PingWebSocketFrame ping = new PingWebSocketFrame(webSocketFrame.content().retain());
			context.channel().writeAndFlush(ping);
			return;
		}
		if (!(webSocketFrame instanceof TextWebSocketFrame)) {
			throw new RuntimeException(this.getClass().getName());
		}
		// String request = ((TextWebSocketFrame) webSocketFrame).text();
		// TextWebSocketFrame textWebSocketFrame = new
		// TextWebSocketFrame(context.channel().id() + ":" + request);
		NettyConfig.group.writeAndFlush(webSocketFrame.retain());
	}

	private void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest fullHttpRequest,
			DefaultFullHttpResponse defaultFullHttpResponse) {
		if (!defaultFullHttpResponse.status().equals(HttpResponseStatus.OK)) {
			ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.status().toString(), CharsetUtil.UTF_8);
			defaultFullHttpResponse.content().writeBytes(buf);
			buf.release();
		}
		// 服务端向客户端发送数据
		ChannelFuture future = context.channel().writeAndFlush(defaultFullHttpResponse);
		if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
}

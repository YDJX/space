package org.mythink.netty.fourteenth;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage)msg;
		if(message.getHeader() != null && message.getHeader().getType() == MessageType.LOGIN_RESP.value()){
			byte loginResult = (byte)message.getBody();
			if(loginResult != (byte)0){
				ctx.close();
			} else {
				System.out.println("Login is ok :"+message);
			}
		} else {
			ctx.fireChannelRead(msg);
		}
	}
	
	private NettyMessage buildLoginReq(){
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_REQ.value());
		message.setHeader(header);
		return message;
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}
}

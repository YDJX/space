package org.mythink.netty.fourteenth;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class HeartBeatRespHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = new NettyMessage();
		if(message.getHeader() != null && message.getHeader().getType() == MessageType.HEAT_REQ.value()){
			System.out.println("Receive client heart beat message : ----> " + message);
			NettyMessage heartBeat = buildHeatBeat();
			System.out.println("Send heart beat response message to client: ----> "+heartBeat);
			ctx.writeAndFlush(heartBeat);
		} else {
			ctx.fireChannelRead(msg);
		}
	}
	
	private NettyMessage buildHeatBeat(){
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_RESP.value());
		message.setHeader(header);
		return message;
	}
}

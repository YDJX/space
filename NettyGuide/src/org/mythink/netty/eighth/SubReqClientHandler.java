package org.mythink.netty.eighth;

import org.mythink.netty.codec.protobuf.SubscribeReqProto;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter implements ChannelHandler {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<10;i++){
			ctx.write(req(i));
		}
		ctx.flush();
	}
	
	private SubscribeReqProto.SubscribeReq req(int i){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(i);
		builder.setUserName("fangqinglong");
		builder.setProductName("Netty book for protobuf");
		builder.setAddress("Beijing chaoyang");
		return builder.build();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("Receive server response :["+msg+"]");
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

package org.mythink.netty.seventh;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SubReqClientHandler extends ChannelHandlerAdapter implements ChannelHandler {

	public SubReqClientHandler() {
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<100;i++){
			ctx.write(subReq(i));
		}
		ctx.flush();
	}
	
	private SubscribeReq subReq(int i){
		SubscribeReq req = new SubscribeReq();
		req.setSubReqID(i);
		req.setUserName("fangqinglong");
		req.setPhoneNumber("12834567890");
		req.setAddress("北京朝阳区望京soho 塔2 C座"+i);
		req.setProductName("netty 权威指南");
		return req;
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		SubscribeResp resp = (SubscribeResp)msg;
		System.out.println("Receive server response: ["+resp +"]");
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

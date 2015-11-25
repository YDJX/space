package org.mythink.netty.second;

import java.util.logging.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler4 extends ChannelHandlerAdapter implements ChannelHandler {

	private static final Logger logger = Logger.getLogger(TimeClientHandler4.class.getName());
	private int counter;
	private byte[] req;
	
	public TimeClientHandler4() {
		req = ("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message =null;
		
		for(int i=0;i<1000;i++){
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			ctx.writeAndFlush(message);
		}
		
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buf = (ByteBuf)msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.readBytes(bytes);
		String resp = new String(bytes,"UTF-8");
		System.out.println("Now is :"+ resp +"; the counter is :"+ ++counter);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		System.out.println("handler is over.close right now");
		ctx.close();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.warning("Unexcpected exception from downstream : "+ cause.getMessage());
		ctx.close();
	}
}

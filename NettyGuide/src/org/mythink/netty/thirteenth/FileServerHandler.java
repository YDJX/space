package org.mythink.netty.thirteenth;

import java.io.File;
import java.io.RandomAccessFile;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

public class FileServerHandler extends SimpleChannelInboundHandler<String> {

	private final String CR  = System.getProperty("line.separator");
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		File file = new File(msg);
		if(file.exists()){
			if(!file.isFile()){
				ctx.writeAndFlush("Not a  file :"+file+CR);
				return;
			}
			
			ctx.write(file+" "+ file.length()+CR);
			RandomAccessFile raf = new RandomAccessFile(msg, "r");
			FileRegion region = new DefaultFileRegion(raf.getChannel(), 0, file.length());
			ctx.write(region);
			ctx.writeAndFlush(CR);
			raf.close();
		} else {
			ctx.writeAndFlush("File not found:"+file+CR);
		}
		
	}
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}

package org.mythink.netty.second;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TimeClient4 {

	private void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new TimeClientHandler4());
				
			}
		});
		ChannelFuture f;
			f = b.connect(new InetSocketAddress(host, port)).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
		
	}
	
	public static void main(String[] args){
		int port = 8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.parseInt(args[0],10);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		new TimeClient4().connect("127.0.0.1", port);
	}
}

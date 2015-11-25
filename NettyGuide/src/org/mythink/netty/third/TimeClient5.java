package org.mythink.netty.third;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient5 {

	private void connect(String host,int port){
		EventLoopGroup group = new NioEventLoopGroup();
		try {
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>() {

			/**
			 * 实现支持了tcp粘包问题
			 */
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new TimeClientHandler5());
				
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
		new TimeClient5().connect("127.0.0.1", port);
	}
}

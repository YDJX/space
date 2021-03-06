package org.mythink.netty.twelfth;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> implements ChannelHandler {

	private static final String[] DICTIONARY={"只要功夫深，铁杵磨成针。","旧时王谢堂前燕，分入寻常百姓家。","洛阳亲友如相问，一片冰心在玉壶","一寸光阴一寸金，寸金难买寸光阴。","老骥伏枥，志在千里。烈士暮年壮心不已。"};

	
	private String nextQuote(){
		int quotId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
		return DICTIONARY[quotId];
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
		String req = packet.content().toString(CharsetUtil.UTF_8);
		System.out.println(req);
		if("谚语字典查询？".equals(req)){
			ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer("谚语查询结果："+nextQuote(),CharsetUtil.UTF_8), packet.sender()));
		}
	}
}

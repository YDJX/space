package org.mythink.netty.fourteenth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

	MarshDecod mdecode;
	
	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		mdecode = new MarshDecod();
	}
	
	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		ByteBuf frame = (ByteBuf)super.decode(ctx, in);
		if(frame == null){
			return null;
		}
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setCrcCode(in.readInt());
		header.setLength(in.readInt());
		header.setSessionID(in.readLong());
		header.setType(in.readByte());
		header.setPriority(in.readByte());
		
		int size = in.readInt();
		if(size>0){
			Map<String, Object> attach = new HashMap<>();
			int keySize = 0;
			byte[] keyArray = null;
			String key = null;
			for(int i=0;i<size;i++){
				keySize = in.readInt();
				keyArray = new byte[keySize];
				in.readBytes(keyArray);
				key = new String(keyArray,"UTF-8");
				attach.put(key, mdecode.decode(in));
			}
			keyArray = null;
			key = null;
			header.setAttachment(attach);
		}
		if(in.readableBytes() > 4){
			message.setBody(mdecode.decode(in));
		}
		message.setHeader(header);
		return message;
	}
}

package org.mythink.netty.fourteenth;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

//	MarshallingEncoder marshallingEncoder ;
//	public NettyMessageEncoder() {
//		MarshallingConfiguration mconf = new MarshallingConfiguration();
//		mconf.setVersion(5);
//		MarshallerFactory mfac = Marshalling.getProvidedMarshallerFactory("serial");
//		this.marshallingEncoder = new MarshallingEncoder(new DefaultMarshallerProvider(mfac, mconf));
//	}
	MarshEncod mencode;
	public NettyMessageEncoder() throws IOException {
		mencode = new MarshEncod();
	}
	
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
		if(msg == null|| msg.getHeader() == null)
			throw new Exception("the encode message is null");
		ByteBuf sendBuf = Unpooled.buffer();
		sendBuf.writeInt(msg.getHeader().getCrcCode());
		sendBuf.writeInt(msg.getHeader().getLength());
		sendBuf.writeLong(msg.getHeader().getSessionID());
		sendBuf.writeByte(msg.getHeader().getType());
		sendBuf.writeByte(msg.getHeader().getPriority());
		sendBuf.writeInt(msg.getHeader().getAttachment().size());
		String key = null;
		byte[] keyArray = null;
		Object value = null;
		for(Map.Entry<String, Object> param : msg.getHeader().getAttachment().entrySet()){
			key = param.getKey();
			keyArray = key.getBytes();
			sendBuf.writeInt(keyArray.length);
			sendBuf.writeBytes(keyArray);
			value = param.getValue();
			mencode.encode(value, sendBuf);
		}
		key = null;
		keyArray = null;
		value = null;
		if(msg.getBody() != null){
			mencode.encode(msg.getBody(), sendBuf);
		} else {
			sendBuf.writeInt(0);
			sendBuf.setInt(4, sendBuf.readableBytes());
		}
	}

}

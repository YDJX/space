package org.mythink.netty.fourteenth;

import java.io.IOException;

import org.jboss.marshalling.ByteOutput;
import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.buffer.ByteBuf;

public class MarshEncod {

	private final byte[] LENGTH_PLACEHOLDER=new byte[4];
	
	Marshaller marshaller;
	
	public MarshEncod() throws IOException{
		MarshallingConfiguration mconf = new MarshallingConfiguration();
		mconf.setVersion(5);
		MarshallerFactory mfac = Marshalling.getProvidedMarshallerFactory("serial");
		marshaller = mfac.createMarshaller(mconf);
	}
	
	protected void encode(Object msg, ByteBuf out) throws IOException{
		try {
			int lengthPos = out.writerIndex();
			out.writeBytes(LENGTH_PLACEHOLDER);
			ByteOutput output = Marshalling.createByteOutput(out.nioBuffer());
			marshaller.start(output);
			marshaller.writeObject(msg);
			marshaller.finish();
			out.setInt(lengthPos, out.writerIndex() -lengthPos-4);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			marshaller.close();
		}
	}
}

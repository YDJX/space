package org.mythink.netty.fourteenth;

import java.io.IOException;

import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;
import org.jboss.marshalling.Unmarshaller;

import io.netty.buffer.ByteBuf;

public class MarshDecod {

	private final Unmarshaller unmarshaller;
	
	public MarshDecod() throws IOException {
		MarshallingConfiguration mconf = new MarshallingConfiguration();
		mconf.setVersion(5);
		MarshallerFactory mfac = Marshalling.getProvidedMarshallerFactory("serial");
		unmarshaller = mfac.createUnmarshaller(mconf);
	}
	
	protected Object decode(ByteBuf in) throws IOException{
		int objectSize = in.readInt();
		ByteBuf buf = in.slice(in.readerIndex(), objectSize);
		ByteInput input = Marshalling.createByteInput(buf.nioBuffer());
		Object obj = null;
		try {
			unmarshaller.start(input);
			obj = unmarshaller.readObject();
			unmarshaller.finish();
			in.readerIndex(in.readerIndex()+objectSize);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			unmarshaller.close();
		}
		return obj;
	}
}

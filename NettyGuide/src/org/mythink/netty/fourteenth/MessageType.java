package org.mythink.netty.fourteenth;

public enum MessageType {

	BUS_REQ((byte)0),BUS_RESP((byte)1),BUS_ONE_WAY((byte)2),LOGIN_REQ((byte)3),LOGIN_RESP((byte)4),HEAT_REQ((byte)5),HEAT_RESP((byte)6);
	private byte value;
	private MessageType(byte value) {
		this.value=value;
	}
	public byte value(){
		return this.value;
	}
}

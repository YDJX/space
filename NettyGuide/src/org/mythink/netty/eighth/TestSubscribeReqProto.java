package org.mythink.netty.eighth;

import java.util.ArrayList;
import java.util.List;

import org.mythink.netty.codec.protobuf.SubscribeReqProto;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestSubscribeReqProto {

	private static byte[] encode(SubscribeReqProto.SubscribeReq req){
		return req.toByteArray();
	}
	
	private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException{
		return SubscribeReqProto.SubscribeReq.parseFrom(body);
	}
	
	private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
		SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
		builder.setSubReqID(1);
		builder.setUserName("fangqinglong");
		builder.setProductName("Netty book");
		List<String> address = new ArrayList<>();
		address.add("Nanjing YuHuaTai");
		address.add("BeiJing LiuLiCha");
		address.add("ShenZhen HongShuLin");
		builder.setAddress(address.get((int)Math.floor(Math.random()*3)));
		return builder.build();
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException{
		SubscribeReqProto.SubscribeReq req = createSubscribeReq();
		System.out.println("Before encode :"+req.toString());
		SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
		System.out.println("After decode :"+ req2.toString());
		System.out.println("Assert equal :"+ req2.equals(req));
	}
}

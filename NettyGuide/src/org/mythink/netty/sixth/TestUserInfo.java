package org.mythink.netty.sixth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class TestUserInfo {

	public static void main(String[] args){
		UserInfo info = new UserInfo();
		info.buildUserID(100).buildUserName("Welcome to netty.");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(info);
			oos.flush();
			oos.close();
			byte[] b = bos.toByteArray();
			System.out.println("The jdk serializable length is :"+b.length);
			bos.close();
			
			System.out.println("-------------------------------");
			System.out.println("The byte array serializable length is :"+info.codeC(ByteBuffer.allocate(1024)).length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}

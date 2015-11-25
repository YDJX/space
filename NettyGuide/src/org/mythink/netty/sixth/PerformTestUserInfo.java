package org.mythink.netty.sixth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class PerformTestUserInfo {

	public static void main(String[] args) throws IOException{
		UserInfo info = new UserInfo();
		info.buildUserID(100).buildUserName("Welcome to netty");
		int loop = 1000000;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		long startTime = System.currentTimeMillis();
		for(int i=0;i<loop;i++){
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(info);
			oos.flush();
			oos.close();
			byte[] b = bos.toByteArray();
			bos.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("The jdk serializable cost time is :"+ (endTime-startTime)+" ms");
		System.out.println("------------------------------------------");
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		startTime = System.currentTimeMillis();
		for(int i=0;i<loop;i++){
			byte[] b = info.codeC(buffer);
		}
		endTime = System.currentTimeMillis();
		System.out.println("The byte array serializable cost time is :"+(endTime-startTime)+" ms");
		
	}
}

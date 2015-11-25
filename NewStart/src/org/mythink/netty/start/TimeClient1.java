package org.mythink.netty.start;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TimeClient1 {

	/**
	 * 别纠结这个了，socke传输时候是很难得到-1的 一方关闭时 另一方可能会得到-1
	 * 所以多数时候，socket都是利用‘/n’这种换行 或者 通过固定的字节读取，或者‘大小+数据’的协议方式
	 * 来决定如何传递数据，如何做出响应，因为数据通过tcp传输中，可能本系统合并包或者拆分包
	 * 都是由系统动态网络状况来决定的，有时看着发送的数据可能发出一部分，另一部分还在网卡的缓存区中
	 * @param args
	 */
	public static void main(String[] args){
		int port = 8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.parseInt(args[0],10);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		Socket socket =null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		BufferedInputStream ins = null;
		try {
			socket = new Socket("192.168.1.103", port);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			writer.write("QUERY TIME ORDER");
			writer.flush();
			System.out.println("1 Send order to server succeed.");
//			SocketChannel sc = socket.getChannel();
//			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
//			sc.read(readBuffer);
//			byte[] bytes = new byte[readBuffer.remaining()];
//			readBuffer.get(bytes);
			char[] cbuf = new char[10];
			String response=null;
//			int num = reader.read(cbuf);
//			System.out.println(new String(cbuf,0,num));
//			while(num!=-1){
//				if(response==null){
//					response=new String(cbuf,0,num);
//				}else {
//					response=response +new String(cbuf,0,num);
//				}
//				num = reader.read(cbuf,0,cbuf.length);
//				System.out.println("num is :"+num);
//			}
//			response =reader.readLine();
			ins = new BufferedInputStream(socket.getInputStream());
			int asize =ins.available();
			byte[] bs = new byte[10];
			int x=0;
			int total=0;
			while((x=ins.read(bs,0,bs.length))>-1){
				if(response==null){
					response=new String(bs,0,x);
				}else {
					response = response+new String(bs,0,x);
				}
				System.out.println("x is:"+x);
				System.out.println(response);
				total+=x;
				if(total>=asize){
					break;
				}
				
			}
			System.out.println("1 now is :"+response);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(writer!=null){
				writer.close();
			}
			if(socket!=null){
				try {
					socket.close();
					socket=null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

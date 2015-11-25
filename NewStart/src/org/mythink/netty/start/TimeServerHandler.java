package org.mythink.netty.start;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TimeServerHandler implements Runnable {

	private Socket socket;
	public TimeServerHandler(Socket socket) {
		this.socket=socket;
	}
	
	@Override
	public void run() {
		//BufferedReader in = null;
		BufferedInputStream in =null;
		PrintWriter out =null;
		
		try {
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			String currentTime = null;
			String body = null;
			while(true){
				//body = in.readLine();
				
				in = new BufferedInputStream(socket.getInputStream());
				int asize =in.available();
				System.out.println("available is :"+asize);
				byte[] bs = new byte[10];
				int x=0;
				int total=0;
				while((x=in.read(bs,0,bs.length))>-1){
					if(body==null){
						body=new String(bs,0,x);
					}else {
						body = body+new String(bs,0,x);
					}
					total+=x;
					System.out.println("x is:"+x+",total is :"+total);
					System.out.println(body);
					if(total>=asize){
						break;
					}
				}
				
				if(body==null){
					break;
				}
				System.out.println("The time server receive order:"+ body);
				currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
				out.write(currentTime);
				out.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
			}
			if(this.socket!=null){
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			this.socket=null;
		}

	}

}

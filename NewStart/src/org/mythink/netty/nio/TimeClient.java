package org.mythink.netty.nio;

public class TimeClient {

	public static void main(String[] args){
		int port = 8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.parseInt(args[0],10);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		new Thread(new TimeClientHandler("127.0.0.1",port),"TimeClient-001").start();
	}
}
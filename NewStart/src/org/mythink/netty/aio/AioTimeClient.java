package org.mythink.netty.aio;

public class AioTimeClient {

	public static void main(String[] args){
		int port=8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.parseInt(args[0],10);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		new Thread(new AsyncTimeClientHandler("127.0.0.1", port),"AIO-AsynchronousTimeClientHandler-001").start();
	}
}

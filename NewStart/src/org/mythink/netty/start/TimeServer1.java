package org.mythink.netty.start;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer1 {

	public static void main(String[] args){
		int port = 8080;
		if(args!=null && args.length>0){
			try {
				port = Integer.parseInt(args[0],10);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		
		ServerSocket servSocket=null;
		try {
			servSocket = new ServerSocket(port);
			System.out.println("The server start in port:"+port);
			
			Socket socket =null;
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(100,10000);
			while(true){
				socket = servSocket.accept();
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(servSocket!=null){
				try {
					System.out.println("The time server close.");
					servSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

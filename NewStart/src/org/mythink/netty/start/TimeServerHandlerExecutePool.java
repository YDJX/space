package org.mythink.netty.start;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerExecutePool {

	private ExecutorService executor;
	
	public TimeServerHandlerExecutePool(int maxSize,int queueSize) {
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize), new ThreadPoolExecutor.AbortPolicy());
	}
	
	public void execute(java.lang.Runnable task){
		executor.execute(task);
	}
}

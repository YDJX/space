package org.mythink.java8.test;

import java.util.concurrent.atomic.AtomicInteger;

public class SerialNum {

	// Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
        new ThreadLocal<Integer>() {
            @Override protected Integer initialValue() {
                return nextId.getAndIncrement();
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }
	public static void main(String[] args){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" "+SerialNum.get()+" "+SerialNum.get());
				
			}
		},"first").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" "+SerialNum.get()+" "+SerialNum.get());
				
			}
		},"second").start();
	}
}

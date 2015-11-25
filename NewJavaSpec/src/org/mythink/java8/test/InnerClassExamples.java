package org.mythink.java8.test;

import org.mythink.java8.test.StaticOuter.StaticInner;

class InstanceOuter {
	public InstanceOuter(int xx){
		x=xx;
	}
	private int x;
	
	class InstanceInner{
		public void printSomething(){
			System.out.println("value of x in my outer is :"+x);
		}
	}
}

class StaticOuter {
	private static int x = 24;
	static class StaticInner{
		public void printSomething(){
			System.out.println("value of x in static inner is :"+x);
		}
	}
}

public class InnerClassExamples {

	public static void main(String[] args){
		InstanceOuter outer = new InstanceOuter(12);
		InstanceOuter.InstanceInner inner = outer.new InstanceInner();
		inner.printSomething();
		
		StaticOuter.StaticInner inner1 = new StaticOuter.StaticInner();
		inner1.printSomething();
	}
}

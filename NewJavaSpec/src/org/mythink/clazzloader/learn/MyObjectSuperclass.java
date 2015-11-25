package org.mythink.clazzloader.learn;

public abstract class MyObjectSuperclass {

	public abstract String callme();
	
	public String baseInfo(){
		System.out.println("I'm MyObjectSuperclass. excute baseInfo");
		return "Hello,World!";
	}
}

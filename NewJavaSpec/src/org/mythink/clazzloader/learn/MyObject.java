package org.mythink.clazzloader.learn;

public class MyObject extends MyObjectSuperclass implements MyObjectInterface {

	private String name;
	
	
	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public MyObject() {
	}
	
	public MyObject(String name) {
		this.name = name;
	}
	
	@Override
	public String callme() {
		return "MyObject say:"+baseInfo();
	}
	
	@Override
	public String trans(String name) {
		return "my name is "+name;
	}
	
	@Override
	public String life(String style) {
		return "three times BBQ every day ";
	}
}

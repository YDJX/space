package org.mythink.netty.bean;

public class Birthday {

	private String birthday;
	public Birthday() {
	}
	
	public Birthday(String birthday) {
		super();
		this.birthday = birthday;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.birthday;
	}
}

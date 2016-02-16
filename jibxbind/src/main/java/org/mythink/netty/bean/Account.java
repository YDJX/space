package org.mythink.netty.bean;

public class Account {

	private int id;
	private String name;
	private String email;
	private String address;
	private Birthday birthday;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Birthday getBirthday() {
		return birthday;
	}

	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.id+"#"+this.name+"#"+this.address+"#"+this.email+"#"+this.birthday;
	}

}

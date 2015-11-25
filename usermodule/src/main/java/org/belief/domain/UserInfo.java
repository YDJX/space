package org.belief.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String userName;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private boolean enabled; //whether forbidden
	@Column(nullable=false)
	private String userEmail;
	@Column
	private int mobileNum;
	@Column
	private String telephoneNum;
	
	
	public final Long getId() {
		return id;
	}
	public final void setId(Long id) {
		this.id = id;
	}
	public final String getUserName() {
		return userName;
	}
	public final void setUserName(String userName) {
		this.userName = userName;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	public final boolean isEnabled() {
		return enabled;
	}
	public final void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public final String getUserEmail() {
		return userEmail;
	}
	public final void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public final int getMobileNum() {
		return mobileNum;
	}
	public final void setMobileNum(int mobileNum) {
		this.mobileNum = mobileNum;
	}
	public final String getTelephoneNum() {
		return telephoneNum;
	}
	public final void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}
	
	
}

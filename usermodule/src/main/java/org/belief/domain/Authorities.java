package org.belief.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 授权信息
 * @author ydjx
 *
 */
@Entity
public class Authorities {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String authority;//role,  no policy ,enum
	
	public final String getUserName() {
		return userName;
	}
	public final void setUserName(String userName) {
		this.userName = userName;
	}
	public final String getAuthority() {
		return authority;
	}
	public final void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}

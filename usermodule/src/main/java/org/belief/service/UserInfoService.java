package org.belief.service;

import java.util.List;

import org.belief.domain.UserInfo;

public interface UserInfoService {

	public boolean addUser(UserInfo userinfo);
	
	public List<UserInfo> showAllUsers();
	
	public boolean delUser(UserInfo userinfo);
}

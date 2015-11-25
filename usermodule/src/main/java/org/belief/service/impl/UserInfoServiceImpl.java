package org.belief.service.impl;

import java.util.List;

import org.belief.domain.UserInfo;
import org.belief.domain.UserInfoRepository;
import org.belief.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoServiceImpl implements UserInfoService {

	private UserInfoRepository userInfoRepository;
	
	public final UserInfoRepository getUserInfoRepository() {
		return userInfoRepository;
	}

	@Autowired
	public final void setUserInfoRepository(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}

	@Override
	public boolean addUser(UserInfo userinfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserInfo> showAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delUser(UserInfo userinfo) {
		// TODO Auto-generated method stub
		return false;
	}

}

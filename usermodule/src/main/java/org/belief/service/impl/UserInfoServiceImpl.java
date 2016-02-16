package org.belief.service.impl;

import java.util.List;

import org.belief.domain.UserInfo;
import org.belief.domain.UserInfoRepository;
import org.belief.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userInfoService")
@Transactional
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
		try {
			userInfoRepository.saveAndFlush(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<UserInfo> showAllUsers() {
		return userInfoRepository.findAll();
	}

	@Override
	public boolean delUser(UserInfo userinfo) {
		try {
			userInfoRepository.delete(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

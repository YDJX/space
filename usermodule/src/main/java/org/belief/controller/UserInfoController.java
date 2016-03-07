package org.belief.controller;

import java.util.List;

import org.belief.domain.UserInfo;
import org.belief.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="/add")
	public String addUser(UserInfo userinfo){
		boolean issucc =  userInfoService.addUser(userinfo);
		if(issucc){
			return "index.html";
		}
		return "error.html";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public List<UserInfo> getUsers(){
		return userInfoService.showAllUsers();
	}
}

package org.belief.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@EnableAutoConfiguration
public class HelloController {

	@RequestMapping(value="/hello")
	@ResponseBody
	public String helloWorld(){
		return "哈喽，spring boot!";
	}
}

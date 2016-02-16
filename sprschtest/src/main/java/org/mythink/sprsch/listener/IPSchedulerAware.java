package org.mythink.sprsch.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;

public class IPSchedulerAware implements ServletContextAware {

	private ServletContext application;
	public void setServletContext(ServletContext context) {
		this.application = context;
	}
	

}

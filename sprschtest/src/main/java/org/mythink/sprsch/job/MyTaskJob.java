package org.mythink.sprsch.job;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MyTaskJob {

	public void executeJob(){
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA);
		System.out.println(formater.format(ldt)+"开始执行任务。");
	}
}

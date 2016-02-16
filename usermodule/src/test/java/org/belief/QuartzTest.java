package org.belief;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {

	public static void main(String[] args) {
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
			
			JobDetail jdetail = JobBuilder.newJob(HelloJob.class)
					.withIdentity("job1", "group1")
					.usingJobData("jobSays","Hello World!") //add jobDataMap
					.usingJobData("myFloatValue", 3.1415926f) //add jobDataMap
					.build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("tri1", "group1").startNow().
					withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever()).build();
			scheduler.scheduleJob(jdetail, trigger);
			
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			scheduler.shutdown();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
}

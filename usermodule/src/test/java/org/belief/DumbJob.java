package org.belief;

import java.util.ArrayList;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class DumbJob implements Job {

	private String jobSays;
	private float myFloatValue;
	private ArrayList<Date> myStateDate;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("quartz execute job fire time is :"+context.getFireTime());

		JobKey key = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getMergedJobDataMap();//JobDetail().getJobDataMap();
//		String jobSays = dataMap.getString("jobSays");
//		float myFloatValue = dataMap.getFloat("myFloatValue");
//		ArrayList<Date>  myStateDate = (ArrayList<Date>)dataMap.get("myStateDate");  
		myStateDate.add(new Date());
		System.out.println("Instance "+key + " of HelloJob Says: "+ jobSays + " and value is : " + myFloatValue);
	}

	public final void setJobSays(String jobSays) {
		this.jobSays = jobSays;
	}

	public final void setMyFloatValue(float myFloatValue) {
		this.myFloatValue = myFloatValue;
	}

	public final void setMyStateDate(ArrayList<Date> myStateDate) {
		this.myStateDate = myStateDate;
	}

	
}

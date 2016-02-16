package org.mythink.sprsch.listener;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.ReflectionException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.mythink.sprsch.spring.util.SpringContextUtil;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MySchedulerListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		//

	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context= arg0.getServletContext();
		context.setInitParameter("localhost", "127.0.0.1");
//		ApplicationContext wac = new ClassPathXmlApplicationContext(new String[]{"service-config.xml"});
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(arg0.getServletContext());
		System.out.println("****************************************");
		System.out.println("************start listener**************");
		System.out.println("****************************************");
		StdScheduler schedulerFactoryBean = wac.getBean("schedulerFactoryBean", StdScheduler.class);
//		SchedulerFactoryBean schedulerFactoryBean = SpringContextUtil.getBean("schedulerFactoryBean", SchedulerFactoryBean.class);
		if(null==schedulerFactoryBean){
			System.out.println("====================null================");
		}else {
			System.out.println("++++++++schedulerFactoryBean is not null+++++++++++++++++++++++");
		}
		
		try {
			System.out.println("----------start endpoints-----------");
			List<String> list = getEndPoints();
			for(String str :list){
				System.out.println("YDJX  ip port::"+str);
			}
			System.out.println("----------end   endpoints-----------");
		} catch (MalformedObjectNameException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (AttributeNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstanceNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MBeanException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ReflectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		HashMap<String, Boolean> ipmap = wac.getBean("ipmap", HashMap.class);
//		HashMap<String, Boolean> ipmap = SpringContextUtil.getBean("ipmap");
		System.out.println("YDJX====ipmap is null?"+ipmap.isEmpty());
		for(String key :ipmap.keySet()){
			System.out.println("YDJX----key is :----"+key);
			Collection<InetAddress> cs = getAllHostAddress();
			for(InetAddress ia : cs){
				String address =ia.getHostAddress();
				
				if(ipmap.containsKey(address)){
					System.out.println("YDJX======包含此地址："+address);
					if(!schedulerFactoryBean.isStarted()){
						try {
							schedulerFactoryBean.start();
						} catch (SchedulerException e) {
							System.out.println("启动失败！！！");
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	}

	public static Collection<InetAddress> getAllHostAddress(){
		Collection<InetAddress> cos = null;
		try {
			Enumeration<NetworkInterface>  networkInterfaces =  NetworkInterface.getNetworkInterfaces();
			cos = new ArrayList<InetAddress>();
			while(networkInterfaces.hasMoreElements()){
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				if(networkInterface.isLoopback()){ // pass 127.0.0.1
					Enumeration<InetAddress> inetAddresss = networkInterface.getInetAddresses();
					int i=0;
					while(inetAddresss.hasMoreElements()){
						InetAddress inetAddress = inetAddresss.nextElement();
						System.out.println(i+++"YDJX==============="+inetAddress);
					}
					continue;
				}
				Enumeration<InetAddress> inetAddresss = networkInterface.getInetAddresses();
				while(inetAddresss.hasMoreElements()){
					InetAddress inetAddress = inetAddresss.nextElement();
					cos.add(inetAddress);
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		for(InetAddress inet:cos){
			
			try {
				System.out.println("address="+inet.getHostAddress()+" hsotname=" +inet.getHostName()+" localhsot name="+inet.getLocalHost()
				.getHostName());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		return cos;
	}
	
	static List<String> getEndPoints() throws MalformedObjectNameException,
    NullPointerException, UnknownHostException, AttributeNotFoundException,
    InstanceNotFoundException, MBeanException, ReflectionException {
	MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	Set<ObjectName> objs = mbs.queryNames(new ObjectName("*:type=Connector,*"),
	        Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
	String hostname = InetAddress.getLocalHost().getHostName();
	InetAddress[] addresses = InetAddress.getAllByName(hostname);
	ArrayList<String> endPoints = new ArrayList<String>();
	for (Iterator<ObjectName> i = objs.iterator(); i.hasNext();) {
	    ObjectName obj = i.next();
	    String scheme = mbs.getAttribute(obj, "scheme").toString();
	    String port = obj.getKeyProperty("port");
	    for (InetAddress addr : addresses) {
	        String host = addr.getHostAddress();
	        String ep = scheme + "://" + host + ":" + port;
	        endPoints.add(ep);
	    }
	}
	return endPoints;
	}
}

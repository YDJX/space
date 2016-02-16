package org.mythink.test;

import java.net.InetAddress;
import java.util.Collection;

import org.junit.Test;
import org.mythink.sprsch.listener.MySchedulerListener;

public class IPAddressTest {

	@Test
	public void getHostIpTest(){
		Collection<InetAddress> collection =MySchedulerListener.getAllHostAddress();
		
	}
}

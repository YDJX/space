package org.mythink.believe.test;

import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mythink.believe.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/service-config.xml")
public class ProducerConsumerTest {

	@Autowired
	private ProducerService producerService;
	
	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;
	
	@Test
	public void testSend(){
		for(int i=0;i<2;i++){
			producerService.sendMessage(destination, "你好，生产者！这是消费消息："+(i+1));
		}
	}
}

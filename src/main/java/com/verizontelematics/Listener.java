package com.verizontelematics;

import java.util.concurrent.CountDownLatch;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class Listener {
	
		private final CountDownLatch latch = new CountDownLatch(1);

		
		@KafkaListener(topics = "${kafka.topic}")
		public void listen(String foo) {
			System.out.println("Received: " + foo);
			latch.countDown();
		}
		
		public CountDownLatch getLatch() {
	        return latch;
	    }

}
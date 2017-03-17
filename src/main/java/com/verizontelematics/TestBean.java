package com.verizontelematics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class TestBean {

	@Autowired
	private ConfigProperties configProperties;

	@Autowired
	private KafkaTemplate<String, String> template;

	public void send(String foo) {
		this.template.send(this.configProperties.getTopic(),"foo" );
	}

}
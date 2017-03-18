package com.verizontelematics;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Gary Russell
 *
 */
@Component
@ConfigurationProperties(prefix = "kafka")
public class ConfigProperties {

	private String brokerAddress;

	private String topic;

	private String fooTopic;

	public String getBrokerAddress() {
		return this.brokerAddress;
	}

	public void setBrokerAddress(String brokerAddress) {
		this.brokerAddress = brokerAddress;
	}

	public String getTopic() {
		return this.topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}

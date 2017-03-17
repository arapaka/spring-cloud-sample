package com.verizontelematics;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@EnableKafka()
@Import({ CommonConfiguration.class, ConfigProperties.class })
public class SpringCloudSampleApplication implements CommandLineRunner{

	 @Autowired
     private Environment env;
	 
	 @Autowired
	 private EnvironmentsRepository repository;
	
	 @Autowired
	 private  Listener listener;
	 
	
	 @RequestMapping("/valuelookup")
	 public String env(@RequestParam("prop") String prop) {
		 return env.getProperty(prop, "Not Found");
	}
	
	@RequestMapping("/test")
	public String test() {
		return env.getProperty("myvalue");
	}
	

	@RequestMapping(value="/insert")
	public String insert() {
		this.repository.save(new Environments(UUIDs.timeBased(),"IPAddress", env.getProperty("spring.cloud.client.ipAddress","Unable to Retrieve")));
		this.repository.save(new Environments(UUIDs.timeBased(),"PID", env.getProperty("PID","Unable to Retrieve")));
		this.repository.save(new Environments(UUIDs.timeBased(),"ConsulValue", env.getProperty("PID") + env.getProperty("testkey","Unable to Retrieve")));
		return "Successful entries into the database.";
		
	}
	
	@Override
	public void run(String... args) throws Exception {

		TestBean testBean = new TestBean();
		testBean.send("foo");

		
		//Listener listener = new Listener();
	   listener.listen("foo");
		//listener.latch.await(60, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
        SpringApplication.run(SpringCloudSampleApplication.class, args);
        
	}
	
	
}
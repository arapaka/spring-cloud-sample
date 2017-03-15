package com.verizontelematics;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.driver.core.utils.UUIDs;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class SpringCloudSampleApplication implements CommandLineRunner{

	 @Autowired
     private Environment env;
	 
	 @Autowired
		private EnvironmentsRepository repository;
	
	
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
		return "Success";
		
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		this.repository.deleteAll();

		// save a couple of customers
		this.repository.save(new Environments(UUIDs.timeBased(), "Alice", "Smith"));
		this.repository.save(new Environments(UUIDs.timeBased(), "Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Environments envs : this.repository.findAll()) {
			System.out.println(envs);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Environments envs : this.repository.findByValue("Smith")) {
			System.out.println(envs);
		}
	}
	
	
	public static void main(String[] args) {
        SpringApplication.run(SpringCloudSampleApplication.class, args);
	}
	
	
}
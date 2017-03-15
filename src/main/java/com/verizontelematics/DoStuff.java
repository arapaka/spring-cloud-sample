package com.verizontelematics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DoStuff {

	
	@Autowired
	private MyConfig myConfig;
	
	
	public void doSomeStuff() {
		System.out.println("I was here");
	}
	
}
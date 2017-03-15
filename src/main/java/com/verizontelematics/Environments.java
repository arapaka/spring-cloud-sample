package com.verizontelematics;

import java.util.UUID;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class Environments {
	
	@PrimaryKey
    private UUID id;
    
	@Column
    private String name;
    
	@Column
	private String value;
	

	public Environments(UUID id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		value = value;
	}
}

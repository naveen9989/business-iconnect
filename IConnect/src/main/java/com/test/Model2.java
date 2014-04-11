package com.test;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Model2 {

	String id;
	String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

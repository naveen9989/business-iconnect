package com.test;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public class DaoMongo {
	@Autowired
	MongoTemplate mongoTemplate;

	String collectionName="test1";
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	public void insert()
	{
		Model1 model1=new Model1();
		model1.setId(UUID.randomUUID().toString());
		model1.setName("Naresh");
		mongoTemplate.insert(model1, collectionName);
		Model2 model2=new Model2();
		model2.setId(model1.getId());
		model2.setAddress("hyd");
		mongoTemplate.insert(model2, "test2");
		System.out.println("Inserted");
	}

}

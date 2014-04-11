package com.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MongoTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("com/test/app-config.xml");
		DaoMongo daoMongo=applicationContext.getBean("mongoTest",DaoMongo.class);
		daoMongo.insert();
	}
}

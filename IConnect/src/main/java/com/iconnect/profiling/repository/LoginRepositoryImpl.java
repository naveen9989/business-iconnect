package com.iconnect.profiling.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.iconnect.profiling.domain.RegistrationDomain;

public class LoginRepositoryImpl implements LoginRepository<RegistrationDomain> {
	Logger logger = LoggerFactory.getLogger(LoginRepositoryImpl.class);
	private MongoTemplate mongoTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public RegistrationDomain performLoginCheck(String userName) {
		RegistrationDomain domain = mongoTemplate.findOne(new Query(Criteria
				.where("CompanyInfo.email").is(userName)),
				RegistrationDomain.class, "registration");
		if (domain != null) {
			return domain;
		} else {
			throw new IllegalArgumentException();
		}
	}

}

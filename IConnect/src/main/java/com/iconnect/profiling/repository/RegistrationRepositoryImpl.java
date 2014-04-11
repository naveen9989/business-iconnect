package com.iconnect.profiling.repository;

import java.sql.Timestamp;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.iconnect.profiling.domain.ActivationDomain;
import com.iconnect.profiling.domain.RegistrationDomain;
import com.iconnect.profiling.exceptions.ActivationFailureException;
import com.iconnect.profiling.exceptions.InvalidUrlException;
import com.iconnect.profiling.exceptions.RegistrationFailureException;
import com.iconnect.profiling.mail.Mail;
import com.mongodb.WriteResult;

/**
 * @author NaveenKumar
 * 
 */
@Repository
public class RegistrationRepositoryImpl implements
		RegistrationRepository<RegistrationDomain> {

	private Logger logger = LoggerFactory
			.getLogger(RegistrationRepositoryImpl.class);
	private static final String REG_COLLECTION_NAME = "registration";
	private static final String ACT_COLLECTION_NAME = "activation";
	private static final int ACT_CODE_LENGTH = 50;
	private String view = "";
	// All the below attributes are initialized inside constructor
	private MongoTemplate mongoTemplate;

	private ActivationDomain activationDomain;
	// below attribute is intialized using setter
	private Mail mail;

	public RegistrationRepositoryImpl() {
		// Nothing to do
	}

	public RegistrationRepositoryImpl(MongoTemplate mongoTemplate) {

		this.mongoTemplate = mongoTemplate;

	}

	public void setMailThread(Mail mail) {
		this.mail = mail;
	}

	public void setActivationModel(ActivationDomain activationModel) {
		this.activationDomain = activationModel;
	}

	/*
	 * Below method is used to insert the user submitted data in db
	 */
	@Override
	public String registerProfile(final RegistrationDomain regDomain)
			throws RegistrationFailureException {
		logger.debug("Entered into registerProfile method Setting all the values into model object");

		try {
			mongoTemplate.insert(regDomain, REG_COLLECTION_NAME);
			view = "success";
			logger.debug("SUCCESSFULLY Returned view");
		} catch (Exception e) {
			throw new RegistrationFailureException(
					"RegistrattionFailed try after some time");
		}

		// create a thread and send a mail to activate account
		new Thread() {

			@Override
			public void run() {
				/*
				 * Thread helps us to send a mail without making user to wait
				 * until the mail trigger
				 */

				logger.debug("Generating Activation Code");
				String activationCode = RegistrationHelper
						.activationCode(ACT_CODE_LENGTH);
				logger.debug("Generating Expired Date");
				Date expireDate = RegistrationHelper.generateExpireDate();

				activationDomain.setActivationCode(activationCode);
				activationDomain.setEmailId(regDomain.getCompanyInfo()
						.getEmail());
				activationDomain.setName(regDomain.getFirstName());
				activationDomain.setExpireDate(expireDate);

				sendActivationMail(activationDomain);

			}

		}.start();

		return view;
	}

	// Below method is used to send a mail
	public void sendActivationMail(ActivationDomain activationModel) {

		logger.debug("Inserting activation code and all the user details into Database");
		// save the activationModel details into database
		mongoTemplate.insert(activationModel, ACT_COLLECTION_NAME);
		logger.debug("Sending Mail");
		mail.sendMail();

	}

	// Below method is used to update userProfile Information
	@Override
	public WriteResult updateProfile(String name) {
		return null;
	}

	// Below method is used to retrieve user profile Information
	@Override
	public RegistrationDomain getProfileInfo(String name) {
		return null;
	}

	// Below method allows us to activate profile
	@Override
	public String activateProfile(String activationCode)
			throws InvalidUrlException, ActivationFailureException {

		String status = "";
		RegistrationDomain registrationDomain = null;
		// generate current timestamp
		Date date = new Date();
		Timestamp currentTimeStamp = new Timestamp(date.getTime());
		logger.debug("current timeStamp generated");
		// check whether the activation code is correct of not to particular
		// user
		logger.debug("Retriving the data from ACTIVATION collection based on activation Code");
		final ActivationDomain actDomain = mongoTemplate.findOne(new Query(
				Criteria.where("activationCode").is(activationCode)),
				ActivationDomain.class, "activation");
		if (actDomain != null) {
			try {
				// if activation code exists retreive the data of particular
				// user from registration collection
				logger.debug("Retriving the data from REGISTRATION collection based on emailId");
				registrationDomain = mongoTemplate.findOne(
						new Query(Criteria.where("companyInfo.email").is(
								actDomain.getEmailId())),
						RegistrationDomain.class, "registration");

			} catch (Exception e) {
				throw new InvalidUrlException("User has been not registered");
			}

		} else {

			throw new InvalidUrlException("Invalid Activation Code");
		}
		/*
		 * Convert the exprire date into timestamp which is stored in activation
		 * collection
		 */
		Date d = actDomain.getExpireDate();
		System.out.println(d);
		Timestamp expireTimeStamp = new Timestamp(d.getTime());
		logger.debug("Expire timestamp generated");
		// Check whether Activation code or url expired or not
		logger.debug("Checking whether activationCode is expired or not");
		if (!currentTimeStamp.before(expireTimeStamp))
			throw new InvalidUrlException("ActivationCode has been expired");
		// check whether userprofile already activated or not
		else if (registrationDomain.getActivationFlag() != 0) {
			logger.debug("Checking wheather User Already Activated or not");
			throw new InvalidUrlException("User Already has been activated");
		} else {

			/*
			 * If all above conditions satisfy update the flag to 1 in
			 * registration collection
			 */
			logger.debug("Updating User Flag(Activating Account)");
			WriteResult result = mongoTemplate.updateFirst(new Query(Criteria
					.where("companyInfo.email").is(actDomain.getEmailId())),
					Update.update("activationFlag", 1),
					RegistrationDomain.class, "registration");
			if (result.getN() != 0)
				status = "activated";
			else
				throw new ActivationFailureException();

			new Thread() {
				@Override
				public void run() {
					/*
					 * Remove the activation code of user from activation
					 * collection
					 */
					logger.debug("Removing activation Code from Database");
					mongoTemplate.remove(
							new Query(Criteria.where("activationCode").is(
									actDomain.getActivationCode())),
							"activation");

				}

			}.start();

			logger.debug("Profile Activated returning the view");

		}

		return status;

	}

	@Override
	public boolean checkEmailExists(String email) {
		RegistrationDomain domain = mongoTemplate.findOne(new Query(Criteria
				.where("CompanyInfo.email").is(email)),
				RegistrationDomain.class, "registration");
		logger.debug("Entered into repository");
		System.out.println(domain);
		if (domain != null) {
			logger.debug("returning true");
			return true;
		}
		logger.debug("returning false");
		return false;
	}
}

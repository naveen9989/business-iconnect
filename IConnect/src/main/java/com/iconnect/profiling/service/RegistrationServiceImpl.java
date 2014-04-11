package com.iconnect.profiling.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.iconnect.profiling.command.Registration;
import com.iconnect.profiling.domain.CompanyInfoDomain;
import com.iconnect.profiling.domain.RegistrationDomain;
import com.iconnect.profiling.exceptions.ActivationFailureException;
import com.iconnect.profiling.exceptions.InvalidUrlException;
import com.iconnect.profiling.exceptions.RegistrationFailureException;
import com.iconnect.profiling.repository.RegistrationRepository;

/**
 * @author NaveenKumar
 * 
 */
public class RegistrationServiceImpl implements
		IRegistrationSevice<Registration> {
	private RegistrationDomain domain;
	private RegistrationRepository<RegistrationDomain> repository;
	private Logger logger = LoggerFactory
			.getLogger(RegistrationServiceImpl.class);
	private CompanyInfoDomain companyInfo;
	private String view;

	public RegistrationServiceImpl() {
		// Nothing to do
	}

	public RegistrationServiceImpl(RegistrationDomain domain,
			RegistrationRepository<RegistrationDomain> repository) {
		this.domain = domain;
		this.repository = repository;
	}

	public void setCompanyInfo(CompanyInfoDomain companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String createProfile(Registration registration)
			throws RegistrationFailureException {
		logger.debug("Entered into createProfile Method");
		// Actual business logic lives here
		// populate all the values in domain object
		companyInfo.setAddress(registration.getAddress());
		companyInfo.setCity(registration.getCity());
		companyInfo.setCompanyName(registration.getCompanyName());
		companyInfo.setCountry(registration.getCountry());
		companyInfo.setEmail(registration.getEmail());
		companyInfo.setEmployeeSize(registration.getEmployeeSize());
		companyInfo.setEstablishedYear(registration.getEstablishedYear());
		companyInfo.setFax(registration.getFax());
		companyInfo.setIndustry(registration.getIndustry());
		companyInfo.setPhone(registration.getPhone());
		companyInfo.setRevenueSize(registration.getRevenueSize());
		companyInfo.setState(registration.getState());
		companyInfo.setIpAddress(registration.getIpAddress());

		domain.setAge(registration.getAge());
		domain.setFirstName(registration.getFirstName());
		domain.setGender(registration.getGender());
		domain.setLastName(registration.getLastName());
		domain.setDob(registration.getDob());
		domain.setCompanyInfo(companyInfo);
		domain.setRole("ROLE_SUPERUSER");
		domain.setActivationFlag(0);
		domain.setRegistrationDate(new Date());
		domain.setAccountLock(false);
		// logic for generating salted hash for password using bcrypt
		// algorithm
		String salt = BCrypt.gensalt(12);
		String hash = BCrypt.hashpw(registration.getPassword(), salt);
		logger.debug("Password Hash has been generated");
		// populate hashed password into domain object
		domain.setPassword(hash);
		logger.debug("All the values populated into domain object");
		// pass domain object to dataAccess Layer
		view = repository.registerProfile(domain);
		logger.debug("Dao returned view is::" + view);
		return view;
	}

	@Override
	// below method calls dao to activate profile
	public String activateProfile(String activationCode)
			throws InvalidUrlException, ActivationFailureException {

		return repository.activateProfile(activationCode);
	}

	@Override
	public boolean checkEmailExists(String email) {
		logger.debug("Entered into service class intracting with repository");
		return repository.checkEmailExists(email);
	}

}

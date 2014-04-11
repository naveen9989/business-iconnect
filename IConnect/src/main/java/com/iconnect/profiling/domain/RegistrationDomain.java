package com.iconnect.profiling.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author NaveenKumar
 * 
 */
public class RegistrationDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	// contains only attributes which are stored in database

	private String firstName;
	private String lastName;
	private String gender;
	private Date dob;
	private String password;
	private int age;
	private CompanyInfoDomain companyInfo;
	private String role;
	private int activationFlag;
	private Date registrationDate;
	private boolean accountLock;

	public RegistrationDomain() {
		// TODO Auto-generated constructor stub
	}

	public RegistrationDomain(String firstName, String lastName, String gender,
			Date dob, String password, int age, CompanyInfoDomain companyInfo,
			String role, int activationFlag) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.password = password;
		this.age = age;
		this.companyInfo = companyInfo;
		this.role = role;
		this.activationFlag = activationFlag;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public CompanyInfoDomain getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(CompanyInfoDomain companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActivationFlag() {
		return activationFlag;
	}

	public void setActivationFlag(int activationFlag) {
		this.activationFlag = activationFlag;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isAccountLock() {
		return accountLock;
	}

	public void setAccountLock(boolean accountLock) {
		this.accountLock = accountLock;
	}

}

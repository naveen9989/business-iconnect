package com.iconnect.profiling.domain;

import java.io.Serializable;

/**
 * @author NaveenKumar
 * 
 */
public class CompanyInfoDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	private String companyName;
	private String establishedYear;
	private String industry;
	private String revenueSize;
	private String employeeSize;
	private String email;
	private String phone;
	private String fax;
	private String address;
	private String city;
	private String state;
	private String country;
	private String ipAddress;

	public CompanyInfoDomain() {
		// No operations
	}

	public CompanyInfoDomain(String companyName, String establishedYear,
			String industry, String revenueSize, String employeeSize,
			String email, String phone, String fax, String address,
			String city, String state, String country, String ipAddress) {
		this.companyName = companyName;
		this.establishedYear = establishedYear;
		this.industry = industry;
		this.revenueSize = revenueSize;
		this.employeeSize = employeeSize;
		this.email = email;
		this.phone = phone;
		this.fax = fax;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.ipAddress = ipAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEstablishedYear() {
		return establishedYear;
	}

	public void setEstablishedYear(String establishedYear) {
		this.establishedYear = establishedYear;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getRevenueSize() {
		return revenueSize;
	}

	public void setRevenueSize(String revenueSize) {
		this.revenueSize = revenueSize;
	}

	public String getEmployeeSize() {
		return employeeSize;
	}

	public void setEmployeeSize(String employeeSize) {
		this.employeeSize = employeeSize;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}

package com.iconnect.profiling.validator;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.iconnect.profiling.command.Registration;

/**
 * @author NaveenKumar
 * 
 */
public class FormValidator implements Validator {

	public boolean supports(Class<?> targetClass) {
		return targetClass.isAssignableFrom(Registration.class);
	}

	public void validate(Object target, Errors errors) {
		validateAccountForm(target, errors);
		validateBusinessForm(target, errors);
	}

	// Below method validates first registration page(registrationStep1.jsp)
	// data
	public void validateAccountForm(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
				"required.firstName", "First Name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender",
				"required.gender", "gender is required");

		// below logic checks whether user entered valid age or not
		Registration registration = (Registration) target;
		if ((registration.getPassword() != null) && registration.getAge() <= 18)
			errors.rejectValue("age", "id.min", "Age must be greater than 18");
		// below logic checks whether password combination valid or not
		if (registration.getPassword() != null
				&& !checkPassword(registration.getPassword()) == true)
			errors.rejectValue(
					"password",
					"password.valid",
					"password must contain atleast 6 characters with combination of[atleast one Capital letter&(1-10)Digits&(@#$%_)Symbols]");

		// below logic checks whether user entered passwords are matched or not
		if (!registration.getPassword().equals(
				registration.getConfirmPassword()))
			errors.rejectValue("confirmPassword", "confirmPassword.match",
					"password Not Matched");

	}

	// Below method validates second Registration page(registrationStep2.jsp)
	// data
	public void validateBusinessForm(Object target, Errors errors) {
		Registration registration = (Registration) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName",
				"required.companyName", "CompanyName is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "establishedYear",
				"required.establishedYear", "EstablishedYear is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "industry",
				"required.industry", "Industry Name  is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employeeSize",
				"required.employeeSize", "EmployeeSize is required");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmEmail",
		 * "required.confirmEmail", "Email is required");
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone",
				"required.phone", "Phone Number is required");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city",
				"required.city", "city is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state",
				"required.state", "state is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country",
				"required.country", "country is required");

		if (!checkEmail(registration.getEmail()) == true)
			errors.rejectValue("email", "email.valid", "Email is invalid");

		if (!registration.getEmail().equals(registration.getConfirmEmail()))
			errors.rejectValue("confirmEmail", "confirmEmail.match",
					"EmailId not matched");

	}

	// logic for validating password
	public boolean checkPassword(String password) {
		String PASSWORD_STRENGTH = "((?=.*[A-Z])(?=.*\\d)(?=.*[@$#%_]).{6,20})";
		Pattern pattern = Pattern.compile(PASSWORD_STRENGTH);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	// Logic for validating email
	public boolean checkEmail(String email) {
		String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}

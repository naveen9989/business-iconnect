package com.iconnect.profiling.service;

import com.iconnect.profiling.exceptions.ActivationFailureException;
import com.iconnect.profiling.exceptions.InvalidUrlException;
import com.iconnect.profiling.exceptions.RegistrationFailureException;

/**
 * @author NaveenKumar
 * 
 * @param <Registration>
 */
public interface IRegistrationSevice<T> {
	public String createProfile(T Object) throws RegistrationFailureException;
			

	public String activateProfile(String activationCode)
			throws InvalidUrlException, ActivationFailureException;

	public boolean checkEmailExists(String email);
}

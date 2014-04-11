package com.iconnect.profiling.repository;

import com.iconnect.profiling.exceptions.ActivationFailureException;
import com.iconnect.profiling.exceptions.InvalidUrlException;
import com.iconnect.profiling.exceptions.RegistrationFailureException;
import com.mongodb.WriteResult;

/**
 * @author NaveenKumar
 * 
 * @param <RegistrationDomain>
 */
public interface RegistrationRepository<T> {
	public String registerProfile(T Object) throws RegistrationFailureException;

	public WriteResult updateProfile(String name);

	public T getProfileInfo(String name);

	public String activateProfile(String activationCode)
			throws InvalidUrlException, ActivationFailureException;

	public boolean checkEmailExists(String email);
}

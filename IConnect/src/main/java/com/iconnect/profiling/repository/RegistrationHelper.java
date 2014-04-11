package com.iconnect.profiling.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author NaveenKumar
 * 
 */
public class RegistrationHelper {

	private static Logger logger = LoggerFactory.getLogger(RegistrationHelper.class);
	private static final String RANDOM_CHARACHERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static Random random = new Random();

	// Generate the random activation code
	public static String activationCode(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
			sb.append(RANDOM_CHARACHERS.charAt(random.nextInt(RANDOM_CHARACHERS
					.length())));
		logger.debug("Random Activation Code Generated Returning the Code");
		return sb.toString();
	}

	// Generate the expire date of particular activation code
	public static Date generateExpireDate() {
		Date date = null;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		date = calendar.getTime();
		logger.debug("Expire date generated returning date");
		return date;
	}

}

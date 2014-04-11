package com.iconnect.profiling.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.iconnect.profiling.domain.RegistrationDomain;
import com.iconnect.profiling.repository.LoginRepository;

public class LoginServiceImpl implements LoginService, UserDetailsService {
	private LoginRepository<?> loginRepository;

	public LoginServiceImpl(LoginRepository<?> loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		UserDetails userDetails = null;
		RegistrationDomain domain = (RegistrationDomain) loginRepository
				.performLoginCheck(userName);
		userDetails = new User(domain.getCompanyInfo().getEmail(),
				domain.getPassword(), checkAccountEnabled(domain), true, true,
				checkAccountLock(domain), getAuthority(domain.getRole()));
		return userDetails;
	}

	@Override
	public Collection<GrantedAuthority> getAuthority(String accessLevel) {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		collection.add(new SimpleGrantedAuthority(accessLevel));
		return collection;
	}

	public boolean checkAccountEnabled(RegistrationDomain domain) {
		if (domain.getActivationFlag() != 1) {
			return false;
		}
		System.out.println("returning true");
		return true;
	}

	public boolean checkAccountLock(RegistrationDomain domain) {
		if (domain.isAccountLock() != false) {
			return false;
		}
		return true;
	}

}

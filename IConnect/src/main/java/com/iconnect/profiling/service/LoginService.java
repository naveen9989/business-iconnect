package com.iconnect.profiling.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public interface LoginService {
	public Collection<GrantedAuthority> getAuthority(String accessLevel);

}

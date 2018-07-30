package com.loit.core.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

public class AcegiDefaultUserDetailsService implements UserDetailsService {

	@Autowired(required = false)
	private AcegiUserDetailsService a;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		if (this.a == null) {
			AcegiDefaultUserDetails defaultUser = new AcegiDefaultUserDetails();
			defaultUser.setUserId(username);
			defaultUser.setUsername(username);
			defaultUser.setFullname(username);
			defaultUser.setPassword("1");
			defaultUser.setGrantedUrls(Arrays.asList(new String[] { "/**" }));
			return defaultUser;
		}
		return this.a.loadUserByUsername(username);
	}
}

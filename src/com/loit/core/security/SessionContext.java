package com.loit.core.security;

import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;

public class SessionContext {

	public static AcegiUserDetails getUser() {
		Authentication auth;
		if ((auth = SecurityContextHolder.getContext().getAuthentication()) == null) {
			return null;
		}

		if (auth.getPrincipal() instanceof AcegiUserDetails) {
			return (AcegiUserDetails) auth.getPrincipal();
		}
		return null;
	}
}

package com.loit.core.security;

import java.util.List;
import org.springframework.security.GrantedAuthority;

import com.loit.core.hibernate.model.BaseObject;

public class AcegiDefaultUserDetails extends BaseObject implements AcegiUserDetails {
	/**
	 * 
	 */
	private String userId;
	private String username;
	private String password;
	private String fullname;
	private List<String> grantedUrls;
	private List<String> forbiddenUrls;

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public List<String> getGrantedUrls() {
		return this.grantedUrls;
	}

	public void setGrantedUrls(List<String> grantedUrls) {
		this.grantedUrls = grantedUrls;
	}

	public List<String> getForbiddenUrls() {
		return this.forbiddenUrls;
	}

	public void setForbiddenUrls(List<String> forbiddenUrls) {
		this.forbiddenUrls = forbiddenUrls;
	}

	public GrantedAuthority[] getAuthorities() {
		return new GrantedAuthority[0];
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
}

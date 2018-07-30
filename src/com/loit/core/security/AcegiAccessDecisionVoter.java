package com.loit.core.security;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.intercept.web.FilterInvocation;
import org.springframework.security.vote.RoleVoter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AcegiAccessDecisionVoter extends RoleVoter {
	protected final Log log = LogFactory.getLog(getClass());

	private PathMatcher pathMatcher = new AntPathMatcher();
	private boolean enabled;

	public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		Object objAuth = authentication.getPrincipal();
		if ((objAuth instanceof AcegiUserDetails)) {
			if (!this.enabled) {
				return RoleVoter.ACCESS_GRANTED;
			}
			AcegiUserDetails userDetails = (AcegiUserDetails) objAuth;
			List<String> grantedUrls = userDetails.getGrantedUrls();
			List <String>forbiddenUrls = userDetails.getForbiddenUrls();
			if (forbiddenUrls != null) {
				for(String url:forbiddenUrls){
					if (this.pathMatcher.match(url, requestUrl)) {
						if (this.log.isDebugEnabled()) {
							this.log.debug("User " + userDetails.getUsername() + " request url " + requestUrl + " forbidden by pattern " + url);
						}
						return RoleVoter.ACCESS_DENIED;
					}
				}
			}

			if (grantedUrls != null) {
				for(String url:grantedUrls){
					if (this.pathMatcher.match(url, requestUrl)) {
						if (this.log.isDebugEnabled()) {
							this.log.debug("User " + userDetails.getUsername() + " request url " + requestUrl + " granted by pattern " + url);
						}
						return RoleVoter.ACCESS_GRANTED;
					}
				}
			}

			if (this.log.isDebugEnabled()) {
				this.log.debug("User " + userDetails.getUsername() + " request url " + requestUrl + " not granted , ");
			}
			return RoleVoter.ACCESS_DENIED;
		}
		return super.vote(authentication, object, config);
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}

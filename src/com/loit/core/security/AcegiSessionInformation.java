package com.loit.core.security;

import java.util.Date;
import org.springframework.security.concurrent.SessionInformation;

public class AcegiSessionInformation extends SessionInformation {
	private static final long serialVersionUID = 1L;
	private AcegiSessionRegistry regist;

	public AcegiSessionInformation(AcegiSessionRegistry acegiSessionRegistry, Object principal, String sessionId, Date lastRequest, boolean expired) {
		super(principal, sessionId, lastRequest);
		this.regist = acegiSessionRegistry;
		if (expired)
			super.expireNow();
	}

	public void expireNow() {
		super.expireNow();
		this.regist.expire(getSessionId());
	}

	public void refreshLastRequest() {
		super.refreshLastRequest();
		this.regist.refreshLastRequest(getSessionId());
	}
}
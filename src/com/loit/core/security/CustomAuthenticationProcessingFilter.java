package com.loit.core.security;

import com.octo.captcha.image.ImageCaptcha;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

public class CustomAuthenticationProcessingFilter extends AuthenticationProcessingFilter {

	@Autowired(required = false)
	private AcegiAuthenticationEventHandler c;
	private boolean serverSideRedirect = false;
	private boolean checkValidationCode = false;
	private String validationCodeParameter = "j_validation_code";
	private String[] validationCodeIgnoreUsers;

	protected void onPreAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
		super.onPreAuthentication(request, response);
		if ((this.checkValidationCode && ((this.validationCodeIgnoreUsers == null) || (!ArrayUtils.contains(this.validationCodeIgnoreUsers, obtainUsername(request)))))){
			String c = request.getParameter(this.validationCodeParameter);
			HttpSession b;
			Object a = (b = request.getSession()).getAttribute(this.validationCodeParameter);
			b.removeAttribute(this.validationCodeParameter);

			if ((a instanceof String)) {
				if (!((String) a).equals(c))
					throw new ValidationCodeErrorException();
			} else if (((a instanceof ImageCaptcha)) && (!((ImageCaptcha) a).validateResponse(c).booleanValue())) {
				throw new ValidationCodeErrorException();
			}
		}

		if (this.c != null)
			this.c.onPreAuthentication(request, response);
	}

	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
		super.onSuccessfulAuthentication(request, response, authResult);
		if (this.c != null)
			this.c.onSuccessfulAuthentication(request, response, authResult);
	}

	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
			throws IOException {
		super.onUnsuccessfulAuthentication(request, response, failed);
		if (this.c != null)
			this.c.onUnsuccessfulAuthentication(request, response, failed);
	}

	protected void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		if (this.serverSideRedirect)
			try {
				request.getRequestDispatcher(url).forward(request, response);
			} catch (ServletException a) {
				throw new RuntimeException(a);
			}
		else
			super.sendRedirect(request, response, url);
	}

	public void setServerSideRedirect(boolean serverSideRedirect) {
		super.setServerSideRedirect(serverSideRedirect);
		this.serverSideRedirect = serverSideRedirect;
	}

	public void setCheckValidationCode(boolean checkValidationCode) {
		this.checkValidationCode = checkValidationCode;
	}

	public void setValidationCodeParameter(String validationCodeParameter) {
		this.validationCodeParameter = validationCodeParameter;
	}

	public void setValidationCodeIgnoreUsers(String[] validationCodeIgnoreUsers) {
		this.validationCodeIgnoreUsers = validationCodeIgnoreUsers;
	}
}

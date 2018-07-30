package com.loit.core.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.ui.logout.LogoutFilter;
import org.springframework.security.ui.logout.LogoutHandler;

public class CustomLogoutFilter extends LogoutFilter {

	private boolean serverSideRedirect = false;

	public CustomLogoutFilter(String logoutSuccessUrl, LogoutHandler[] handlers) {
		super(logoutSuccessUrl, handlers);
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
		this.serverSideRedirect = serverSideRedirect;
	}

}

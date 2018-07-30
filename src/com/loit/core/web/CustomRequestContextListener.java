package com.loit.core.web;

import java.util.Locale;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.loit.core.spring.SpringContext;


public class CustomRequestContextListener implements ServletRequestListener {

	public void requestInitialized(ServletRequestEvent requestEvent) {
		if (!(requestEvent.getServletRequest() instanceof HttpServletRequest)) {
			throw new IllegalArgumentException("Request is not an HttpServletRequest: "
					+ requestEvent.getServletRequest());
		}
		HttpServletRequest httprequest = (HttpServletRequest) requestEvent.getServletRequest();
		CookieLocaleResolver cookie = (CookieLocaleResolver) SpringContext.getBeanOfType(CookieLocaleResolver.class);
		Locale locale = cookie.resolveLocale(httprequest);
		LocaleContextHolder.setLocale(locale);
	}

	public void requestDestroyed(ServletRequestEvent requestEvent) {
	}
}

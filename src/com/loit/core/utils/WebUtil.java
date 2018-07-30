package com.loit.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {
	public static HttpServletRequest getHttpRequest() {
		ServletRequestAttributes a;
		if ((a = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) == null) {
			return null;
		}
		return a.getRequest();
	}

	public static HttpSession getSession() {
		ServletRequestAttributes a;
		if ((a = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) == null) {
			return null;
		}
		return a.getRequest().getSession();
	}
}

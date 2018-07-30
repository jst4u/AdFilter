package com.loit.core.utils;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.loit.core.spring.SpringContext;

public class MessageUtil {
	private static MessageSource ms = (MessageSource) SpringContext
			.getBeanOfType(MessageSource.class);

	public static String getMessage(String code, Object[] args) {
		return ms.getMessage(code, args, LocaleContextHolder.getLocale());
	}

	public static String getMessageDefault(String code, String defaultMessage,
			Object[] args) {
		return ms.getMessage(code, args, defaultMessage,
				LocaleContextHolder.getLocale());
	}

	public static String getMessage(Locale locale, String code, Object[] args) {
		return ms.getMessage(code, args, locale);
	}

	public static String getMessageDefault(Locale locale, String code,
			String defaultMessage, Object[] args) {
		return ms.getMessage(code, args, defaultMessage, locale);
	}
}

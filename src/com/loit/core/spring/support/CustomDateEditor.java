package com.loit.core.spring.support;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import com.loit.core.utils.DateUtil;


public class CustomDateEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		if ((text == null) || (text.trim().length() == 0))
			setValue(null);
		try {
			setValue(DateUtil.parse(text));
		} catch (ParseException a) {
			throw new IllegalArgumentException("Could not parse date: " + a.getMessage(), a);
		}
	}

	public String getAsText() {
		Date a;
		if ((a = (Date) getValue()) == null) {
			return "";
		}
		return DateUtil.formatDateTime(a);
	}
}

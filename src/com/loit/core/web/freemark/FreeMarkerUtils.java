package com.loit.core.web.freemark;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FreeMarkerUtils {

	public static String convertToFreemarkerSymbol(String tpl) {
		if (tpl == null)
			return null;
		String e;
		Pattern d = Pattern.compile("\\@\\{.{0,}?\\}");

		Matcher c = d.matcher(tpl);
		String b = null;
		StringBuffer localStringBuffer1 = new StringBuffer();
		StringBuffer a = new StringBuffer();
		while (c.find()) {
			b = c.group();
			c.appendReplacement(a, "\\$" + b.substring(1, b.length()));
		}
		c.appendTail(a);
		return a.toString();
	}

}

package com.loit.core.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class ParameterUtil {
	private static final char A = ',';
	private static final String a = String.valueOf(',');

	public static boolean isParamValid(Object value) {
		if (value == null) {
			return false;
		}
		if (((value instanceof String)) && (((String) value).trim().length() == 0)) {
			return false;
		}

		if ((value.getClass().isArray())
				&& ((ArrayUtils.getLength(value) == 0) || (Array.get(value, 0) == null))) {
			return false;
		}

		return (!(value instanceof Collection))
				|| ((!((Collection) value).isEmpty()) && (((Collection) value).iterator().next() != null));
	}

	public static String joinValues(String[] values) {
		if (values == null) {
			return null;
		}
		StringBuilder b = new StringBuilder();
		String[] arrayOfString = values;
		int j = values.length;
		
		for (int i=0; i < j; i++) {
			String a;
			if (((a = arrayOfString[i]) == null) || (a.trim().length() == 0)) {
				continue;
			}
			b.append(',');
			b.append(a.replace(a, a + a));
		}
		return b.toString();
	}

	public static String[] splitValues(String values) {
		if (values == null) {
			return null;
		}
		List d = new ArrayList();
		StringBuilder c = new StringBuilder();
		
		for (int b=0;b < values.length(); b++) {
			char a;
			if ((a = values.charAt(b)) == ',') {
				if ((b + 1 < values.length()) && (values.charAt(b + 1) == ',')) {
					c.append(',');
					b++;
				} else if (c.length() > 0) {
					d.add(c.toString());
					c.setLength(0);
				}
			} else {
				c.append(a);
			}
			if ((b != values.length() - 1) || (c.length() <= 0))
				continue;
			d.add(c.toString());
		}

		return (String[]) d.toArray(new String[d.size()]);
	}
}

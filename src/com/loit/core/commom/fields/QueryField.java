package com.loit.core.commom.fields;

import java.text.ParseException;
import java.util.Date;

import com.loit.core.hibernate.model.BaseObject;
import com.loit.core.security.SessionContext;
import com.loit.core.spring.support.CustomBeanWrapper;
import com.loit.core.utils.DateUtil;

public class QueryField extends BaseObject {
	public static final String FIELD_TYPE_STRING = "String";
	public static final String FIELD_TYPE_INT = "int";
	public static final String FIELD_TYPE_LONG = "long";
	public static final String FIELD_TYPE_DOUBLE = "double";
	public static final String FIELD_TYPE_DATE = "Date";
	public static final String FIELD_TYPE_STRING_ARRAY = "String[]";
	public static final String FIELD_TYPE_INT_ARRAY = "int[]";
	public static final String FIELD_TYPE_SESSION_CONTEXT_PROPERTY = "session";
	private String fieldName;
	private String fieldType;
	private String fieldStringValue;
	private Object fieldValue;
	private String operator;

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldStringValue() {
		return this.fieldStringValue;
	}

	public void setFieldStringValue(String fieldStringValue) {
		this.fieldStringValue = fieldStringValue;
	}

	public Object getFieldValue() {
		if (this.fieldValue != null) {
			return this.fieldValue;
		}
		if (this.fieldStringValue == null) {
			return null;
		}
		if ("String".equals(this.fieldType))
			return this.fieldStringValue;
		if ("int".equals(this.fieldType))
			return Integer.valueOf(Integer.parseInt(this.fieldStringValue));
		if ("long".equals(this.fieldType))
			return Long.valueOf(Long.parseLong(this.fieldStringValue));
		if ("double".equals(this.fieldType))
			return Double.valueOf(Double.parseDouble(this.fieldStringValue));
		if ("Date".equals(this.fieldType)) {
			if (this.fieldStringValue.matches("\\d+"))
				return new Date(Long.parseLong(this.fieldStringValue));
			try {
				return DateUtil.parse(this.fieldStringValue);
			} catch (ParseException d) {
				throw new RuntimeException(d);
			}
		}
		if ("String[]".equals(this.fieldType))
			return this.fieldStringValue.split(",");
		if ("int[]".equals(this.fieldType)) {
			String[] c;
			int[] b = new int[(c = this.fieldStringValue.split(",")).length];
			for (int a = 0; a < c.length; a++) {
				b[a] = Integer.parseInt(c[a]);
			}
			return b;
		}
		if ("session".equals(this.fieldType)) {
			return new CustomBeanWrapper(SessionContext.getUser())
					.getPropertyValueRecursively(this.fieldStringValue);
		}
		return this.fieldStringValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
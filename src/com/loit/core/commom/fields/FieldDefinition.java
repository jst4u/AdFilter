package com.loit.core.commom.fields;

//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;

import org.springframework.beans.factory.BeanNameAware;
//import org.springframework.context.i18n.LocaleContextHolder;

import com.loit.core.hibernate.model.BaseObject;

public class FieldDefinition extends BaseObject implements BeanNameAware {
	public static final String FIELD_TYPE_STRING = "string";
	public static final String FIELD_TYPE_TEXT = "text";
	public static final String FIELD_TYPE_BYTES = "bytes";
	public static final String FIELD_TYPE_INT = "int";
	public static final String FIELD_TYPE_DOUBLE = "double";
	public static final String FIELD_TYPE_DATE = "date";
	public static final String FIELD_TYPE_DATETIME = "datetime";
	public static final String FIELD_TYPE_TIME = "time";
	public static final String FIELD_TYPE_MONTH = "month";
	public static final String FIELD_TYPE_SELECTCODE = "selectCode.";
	private String beanName;
	private String fieldName;
	private String label;
	private String fieldType;
	private boolean sortable;
	private boolean nullable;
	private int length;
	private int precision;
	private int scale;
	private int width;
//	private Map<Locale, String> map = new HashMap();

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return this.beanName;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLabel() {
		return this.label;
//		Locale locale = LocaleContextHolder.getLocale();
//		if (!this.map.containsKey(locale)) {
//			synchronized (this.map) {
//				if (!this.map.containsKey(locale)) {
//					String l = this.beanName == null ? this.label : MessageUtil.getMessageDefault(this.beanName, this.label, new Object[0]);
//					this.map.put(locale, l);
//					return l;
//				}
//			}
//		}
//		return (String) this.map.get(locale);
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isSortable() {
		return this.sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public boolean isNullable() {
		return this.nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return this.precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return this.scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isSelectCode() {
		if (this.fieldType == null)
			return false;
		return this.fieldType.startsWith("selectCode.");
	}

	public String getSelectCodeType() {
		if (isSelectCode()) {
			return this.fieldType.substring("selectCode.".length());
		}
		return null;
	}
}

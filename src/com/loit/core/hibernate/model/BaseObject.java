package com.loit.core.hibernate.model;

import java.beans.PropertyDescriptor;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.loit.core.utils.DateUtil;

public class BaseObject {

	public String toString() {
		StringBuilder objString = new StringBuilder();
		objString.append(getClass().getName());
		objString.append("{");
		PropertyDescriptor properties[] = PropertyUtils.getPropertyDescriptors(this);
		for (int i = 0; i < properties.length; i++) {
			String pName = properties[i].getName();
			if ("class".equals(pName)) {
				continue;
			}

			objString.append(pName);
			objString.append("=");
			if (!PropertyUtils.isReadable(this, pName)) {
				objString.append("!read");
			}
			Object value = null;
			try {
				value = PropertyUtils.getSimpleProperty(this, pName);
				if (value instanceof String) {
					value = "\"" + value + "\"";
				} else if (value instanceof Date) {
					value = DateUtil.formatDateTime((Date) value);
				}
			} catch (Exception e) {
				value = "!error";
			}
			objString.append(value);
			if (i + 1 < properties.length) {
				objString.append(",");
			}
		}

		objString.append("}");
		return objString.toString();
	}

	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}

package com.loit.core.spring.support;

import java.beans.PropertyEditorSupport;

import com.loit.core.utils.StringUtil;

public class CustomStringArrayEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		if(null!=text){
			setValue(text.split(","));
		}else{
			setValue(text);
		}
	}

	public String getAsText() {
		return StringUtil.ArrayToString((String[])getValue());
//		return ParameterUtils.joinValues((String[]) getValue());
	}
}

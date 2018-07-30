package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class SpinnerTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] SpinnerOptionNamesString = {"min", "max"};
	protected static String[] SpinnerOptionNamesBoolean = {"editable","disabled"};
	protected static String[] SpinnerOptionNamesNumber = {"width","height","increment"};
	
	protected static String[] SpinnerEventNames = { "spin", "onSpinUp", "onSpinDown"};
	
	static{
		SpinnerOptionNamesString = (String[]) ArrayUtils.addAll(SpinnerOptionNamesString, ValidateBoxTag.ValidateBoxOptionNamesString);
		SpinnerOptionNamesBoolean =(String[]) ArrayUtils.addAll(SpinnerOptionNamesBoolean, ValidateBoxTag.ValidateBoxOptionNamesBoolean);
		SpinnerOptionNamesNumber =(String[]) ArrayUtils.addAll(SpinnerOptionNamesNumber, ValidateBoxTag.ValidateBoxOptionNamesNumber);
		SpinnerEventNames = (String[]) ArrayUtils.addAll(SpinnerEventNames, ValidateBoxTag.ValidateBoxEventNames);
	}
	
	public SpinnerTag(){
		this.setTagClass("easyui-spinner");
		this.setTagName("input");
		this.setOptionNamesString(SpinnerOptionNamesString);
		this.setOptionNamesBoolean(SpinnerOptionNamesBoolean);
		this.setOptionNamesNumber(SpinnerOptionNamesNumber);
		this.setEventNames(SpinnerEventNames);
	}
	
	

}

package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;

public class TimeSpinnerTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] TimeSpinnerOptionNamesString = {"separator","min", "max"};
	protected static String[] TimeSpinnerOptionNamesBoolean = {"showSeconds","editable","disabled"};
	protected static String[] TimeSpinnerOptionNamesNumber = {"highlight","width","height","increment"};
	
	protected static String[] TimeSpinnerOptionEventNames = { "spin", "onSpinUp", "onSpinDown"};
	
	static{
		TimeSpinnerOptionNamesString = (String[]) ArrayUtils.addAll(TimeSpinnerOptionNamesString, SpinnerTag.SpinnerOptionNamesString);
		TimeSpinnerOptionNamesBoolean =(String[]) ArrayUtils.addAll(TimeSpinnerOptionNamesBoolean, SpinnerTag.SpinnerOptionNamesBoolean);
		TimeSpinnerOptionNamesNumber =(String[]) ArrayUtils.addAll(TimeSpinnerOptionNamesNumber, SpinnerTag.SpinnerOptionNamesNumber);
		TimeSpinnerOptionEventNames = (String[]) ArrayUtils.addAll(TimeSpinnerOptionEventNames, SpinnerTag.SpinnerEventNames);
	}
	
	public TimeSpinnerTag(){
		this.setTagClass("easyui-timespinner");
		this.setTagName("input");
		this.setOptionNamesString(TimeSpinnerOptionNamesString);
		this.setOptionNamesBoolean(TimeSpinnerOptionNamesBoolean);
		this.setOptionNamesNumber(TimeSpinnerOptionNamesNumber);
		this.setEventNames(TimeSpinnerOptionEventNames);
	}
	
	

}

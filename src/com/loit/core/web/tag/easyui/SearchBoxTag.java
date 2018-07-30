package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ArrayUtils;

public class SearchBoxTag extends BaseEasyUITag {

	private static final long serialVersionUID = 1L;

	protected static String[] SearchBoxOptionNamesString = { "prompt", "value"};
	protected static String[] SearchBoxOptionNamesBoolean = { };
	protected static String[] SearchBoxOptionNamesNumber = { "width" , "height"};

	protected static String[] SearchBoxEventNames = { "menu" , "searcher"};

	static{
		SearchBoxOptionNamesString = (String[]) ArrayUtils.addAll(SearchBoxOptionNamesString, MenuButtonTag.MenuButtonOptionNamesString);
		SearchBoxOptionNamesBoolean =(String[]) ArrayUtils.addAll(SearchBoxOptionNamesBoolean, MenuButtonTag.MenuButtonOptionNamesBoolean);
		SearchBoxOptionNamesNumber =(String[]) ArrayUtils.addAll(SearchBoxOptionNamesNumber, MenuButtonTag.MenuButtonOptionNamesNumber);
		SearchBoxEventNames = (String[]) ArrayUtils.addAll(SearchBoxEventNames, MenuButtonTag.MenuButtonEventNames);
	}
	
	public SearchBoxTag() {
		super.setTagClass("easyui-searchbox");
		super.setTagName("input");
		this.setOptionNamesString(SearchBoxOptionNamesString);
		this.setOptionNamesBoolean(SearchBoxOptionNamesBoolean);
		this.setOptionNamesNumber(SearchBoxOptionNamesNumber);
		this.setEventNames(SearchBoxEventNames);
	}
	
}

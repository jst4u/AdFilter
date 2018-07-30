package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ArrayUtils;

public class DateTimeBoxTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;

	private static String[] DateTimeBoxOptionNamesString = { "timeSeparator","style","value","id"};
	private static String[] DateTimeBoxOptionNamesBoolean = { "showSeconds"};
	private static String[] DateTimeBoxOptionNamesNumber = { };

	private static String[] DateTimeBoxEventNames = {};

	static{
		DateTimeBoxOptionNamesString = (String[]) ArrayUtils.addAll(DateTimeBoxOptionNamesString, DateBoxTag.DateBoxOptionNamesString);
		DateTimeBoxOptionNamesBoolean =(String[]) ArrayUtils.addAll(DateTimeBoxOptionNamesBoolean, DateBoxTag.DateBoxOptionNamesBoolean);
		DateTimeBoxOptionNamesNumber =(String[]) ArrayUtils.addAll(DateTimeBoxOptionNamesNumber, DateBoxTag.DateBoxOptionNamesNumber);
		DateTimeBoxEventNames = (String[]) ArrayUtils.addAll(DateTimeBoxEventNames, DateBoxTag.DateBoxEventNames);
	}
	
	public DateTimeBoxTag() {
		this.setTagClass("easyui-datetimebox");
		this.setTagName("input");
		this.setOptionNamesString(DateTimeBoxOptionNamesString);
		this.setOptionNamesBoolean(DateTimeBoxOptionNamesBoolean);
		this.setOptionNamesNumber(DateTimeBoxOptionNamesNumber);
		this.setEventNames(DateTimeBoxEventNames);
	}
	
	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer("<input");
		this.prepareAttrNames(sb);
		this.prepareAttribute(sb, "required", getRequired());
		this.prepareOptions(sb, this.getOptionNamesString(), this.getOptionNamesBoolean(), this.getOptionNamesNumber(),
				this.getEventNames());

		sb.append("/>");

		this.outHtml(sb);

		return EVAL_BODY_INCLUDE;
	}

}

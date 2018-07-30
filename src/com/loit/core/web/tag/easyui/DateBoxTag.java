package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang.ArrayUtils;

public class DateBoxTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;

	protected static String[] DateBoxOptionNamesString = { "currentText", "closeText", "okText"};
	protected static String[] DateBoxOptionNamesBoolean = { "disabled"};
	protected static String[] DateBoxOptionNamesNumber = {"panelWidth", "panelHeight" };

	protected static String[] DateBoxEventNames = {"formatter" ,"parser","onSelect","validType"};

	static{
		
		DateBoxOptionNamesString = (String[]) ArrayUtils.addAll(DateBoxOptionNamesString, CalendarTag.CalendarOptionNamesString);
		DateBoxOptionNamesBoolean =(String[]) ArrayUtils.addAll(DateBoxOptionNamesBoolean, CalendarTag.CalendarOptionNamesBoolean);
		DateBoxOptionNamesNumber =(String[]) ArrayUtils.addAll(DateBoxOptionNamesNumber, CalendarTag.CalendarOptionNamesNumber);
		DateBoxEventNames = (String[]) ArrayUtils.addAll(DateBoxEventNames, CalendarTag.CalendarEventNames);
	}
	
	public DateBoxTag() {
		this.setTagClass("easyui-datebox");
		this.setTagName("input");
		this.setOptionNamesString(DateBoxOptionNamesString);
		this.setOptionNamesBoolean(DateBoxOptionNamesBoolean);
		this.setOptionNamesNumber(DateBoxOptionNamesNumber);
		this.setEventNames(DateBoxEventNames);
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

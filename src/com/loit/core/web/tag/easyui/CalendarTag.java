package com.loit.core.web.tag.easyui;

public class CalendarTag extends BaseEasyUIDivTag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] CalendarOptionNamesString = {"weeks","months","current"};
	protected static String[] CalendarOptionNamesBoolean = {"fit","border"};
	protected static String[] CalendarOptionNamesNumber = {"width","height","firstDay","year","month"};
	
	protected static String[] CalendarEventNames = { "onSelect"};
	
	public CalendarTag(){
		this.setTagClass("easyui-calendar");
		this.setOptionNamesString(CalendarOptionNamesString);
		this.setOptionNamesBoolean(CalendarOptionNamesBoolean);
		this.setOptionNamesNumber(CalendarOptionNamesNumber);
		this.setEventNames(CalendarEventNames);
	}
}

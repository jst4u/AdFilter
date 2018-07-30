package com.loit.core.web.tag.easyui;

import org.apache.commons.lang.ArrayUtils;


public class PaginationTag extends BaseEasyUITag{
	
	private static final long serialVersionUID = 1L;
	protected static String[] PaginationOptionNamesString = {"beforePageText","afterPageText","style"};
	protected static String[] PaginationOptionNamesBoolean = {"loading","showPageList","showRefresh"};
	protected static String[] PaginationOptionNamesNumber = {"total","pageSize", "pageNumber"};
	
	protected static String[] PaginationEventNames = {"pageList","buttons","onSelectPage","onBeforeRefresh", "onRefresh", "onChangePageSize"};
	
	static{
		PaginationOptionNamesString = (String[]) ArrayUtils.addAll(PaginationOptionNamesString, LinkButtonTag.LinkButtonOptionNamesString);
		PaginationOptionNamesBoolean =(String[]) ArrayUtils.addAll(PaginationOptionNamesBoolean, LinkButtonTag.LinkButtonOptionNamesBoolean);
		PaginationOptionNamesNumber =(String[]) ArrayUtils.addAll(PaginationOptionNamesNumber, LinkButtonTag.LinkButtonOptionNamesNumber);
		PaginationEventNames = (String[]) ArrayUtils.addAll(PaginationEventNames, LinkButtonTag.LinkButtonEventNames);
	}
	
	public PaginationTag(){
		this.setTagClass("easyui-pagination");
		this.setTagName("div");
		this.setOptionNamesString(PaginationOptionNamesString);
		this.setOptionNamesBoolean(PaginationOptionNamesBoolean);
		this.setOptionNamesNumber(PaginationOptionNamesNumber);
		this.setEventNames(PaginationEventNames);
	}
	
	
}

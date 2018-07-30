package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

public class FormTag extends BaseEasyUITag {
	/**
	 * Dependencies
	 * panel
	 * resizable
	 */
	private static final long serialVersionUID = 1L;

	private static String[] FormOptionNamesString = {"url"};
	private static String[] FormOptionNamesBoolean = {};
	private static String[] FormOptionNamesNumber = {};
	private static String[] FormEventNames = {"onSubmit","success","onBeforeLoad","onLoadSuccess","onLoadError"};
	
	private String frozened ;
	
	public String getFrozened() {
		return frozened;
	}

	public void setFrozened(String frozened) {
		this.frozened = frozened;
	}

	public FormTag() {
		this.setTagClass("");
		this.setOptionNamesString(FormOptionNamesString);
		this.setOptionNamesBoolean(FormOptionNamesBoolean);
		this.setOptionNamesNumber(FormOptionNamesNumber);
		this.setEventNames(FormEventNames);
	}
	
	public int doStartTag(){
		StringBuffer sb = new StringBuffer("<form");
		this.prepareAttrNames(sb);
		this.prepareOptions(sb, this.getOptionNamesString(), this.getOptionNamesBoolean(), this.getOptionNamesNumber(),
				this.getEventNames());
		sb.append(">");
		if(getType()=="ft"){
			sb.append("<table>");
		}
		this.outHtml(sb);
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspException {
		if(getType()=="ft"){
			this.outHtml("</table>");
		}
		
		this.outHtml("</form>");
		release();
		return EVAL_PAGE;
	}
}

package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

public class BodyTag extends BaseHtmlTag {
	
	private static final long serialVersionUID = 1L;
	
	private String onload;
	private String onContextMenu;

	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer("<body");
		prepareAttribute(sb, "class", super.getStyleClass());
		prepareAttribute(sb, "style", super.getStyle());
		prepareAttribute(sb, "onload", this.getOnload());
		prepareAttribute(sb, "onclick", super.getOnclick());
		prepareAttribute(sb, "onkeydown", super.getOnkeydown());
		prepareAttribute(sb, "onContextMenu", this.getOnContextMenu());
		sb.append(">");
		
		super.outHtml(sb);
		
		return EVAL_BODY_INCLUDE;
	}



	public String getOnContextMenu() {
		return onContextMenu;
	}

	public void setOnContextMenu(String onContextMenu) {
		this.onContextMenu = onContextMenu;
	}

	public String getOnload() {
		return onload;
	}

	public void setOnload(String onload) {
		this.onload = onload;
	}
}

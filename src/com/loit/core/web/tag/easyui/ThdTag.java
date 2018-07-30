package com.loit.core.web.tag.easyui;

import javax.servlet.jsp.JspException;

public class ThdTag extends ValidateBoxTag {

	private static final long serialVersionUID = 1L;

	// th
	private String label;
	private String lstyle;
	private String lcolspan;
	private String lrowspan;
	// td
	private String tstyle;
	private String tcolspan;
	private String trowspan;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLstyle() {
		return lstyle;
	}

	public void setLstyle(String lstyle) {
		this.lstyle = lstyle;
	}

	public String getLcolspan() {
		return lcolspan;
	}

	public void setLcolspan(String lcolspan) {
		this.lcolspan = lcolspan;
	}

	public String getLrowspan() {
		return lrowspan;
	}

	public void setLrowspan(String lrowspan) {
		this.lrowspan = lrowspan;
	}

	public String getTstyle() {
		return tstyle;
	}

	public void setTstyle(String tstyle) {
		this.tstyle = tstyle;
	}

	public String getTcolspan() {
		return tcolspan;
	}

	public void setTcolspan(String tcolspan) {
		this.tcolspan = tcolspan;
	}

	public String getTrowspan() {
		return trowspan;
	}

	public void setTrowspan(String trowspan) {
		this.trowspan = trowspan;
	}

	public int doStartTag() throws JspException {
		StringBuffer sb = new StringBuffer("");
		if (this.getLabel() != null && !"".equals(this.getLabel())) {
			sb.append("<th");
			prepareAttribute(sb, "style", this.getLstyle());
			prepareAttribute(sb, "colspan", this.getLcolspan());
			prepareAttribute(sb, "rowspan", this.getLrowspan());
			sb.append(">");
			sb.append(this.getLabel());
			sb.append("</th>");
		}

		sb.append("<td");
		prepareAttribute(sb, "style", this.getTstyle());
		prepareAttribute(sb, "colspan", this.getTcolspan());
		prepareAttribute(sb, "rowspan", this.getTrowspan());
		sb.append(">");
		
		this.outHtml(sb);
		if(null != getTagType() || null != getType()){
			super.doStartTag();
		}
		
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException {
		super.doEndTag();
		StringBuffer sb = new StringBuffer("</td>");
		this.outHtml(sb);
		release();
		return EVAL_PAGE;
	}
}

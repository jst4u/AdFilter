package com.loit.core.web.tag;

import javax.servlet.jsp.PageContext;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class JspContextModel implements TemplateHashModel {
	public static final int ANY_SCOPE = -1;
	public static final int PAGE_SCOPE = 1;
	public static final int REQUEST_SCOPE = 2;
	public static final int SESSION_SCOPE = 3;
	public static final int APPLICATION_SCOPE = 4;
	private final PageContext pageContext;
	private final int scope;

	public JspContextModel(PageContext pageContext, int scope) {
		this.pageContext = pageContext;
		this.scope = scope;
	}

	public TemplateModel get(String key) throws TemplateModelException {
		Object a = this.scope == -1 ? this.pageContext.findAttribute(key) : this.pageContext.getAttribute(key, this.scope);
		return BeansWrapper.getDefaultInstance().wrap(a);
	}

	public boolean isEmpty() {
		return false;
	}
}

package com.loit.core.web.freemark;

import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class CustomObjectWrapper extends DefaultObjectWrapper {
	public TemplateModel wrap(Object obj) throws TemplateModelException {
		if (obj == null) {
			obj = "";
		}
		return super.wrap(obj);
	}
}
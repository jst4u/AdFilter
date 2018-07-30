package com.loit.core.web.freemark;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.loit.core.exception.SysException;
import com.loit.core.spring.support.CustomBeanWrapper;

public class OutDirective implements TemplateDirectiveModel {
	public static final String PARAM_PROPERTY_KEY = "property";
	public static final String PARAM_FORMATE_KEY = "formate";

	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
		String i = null;
		String h = null;
		TemplateHashModel g;
		StringModel f = (StringModel) env.getDataModel().get("data");
		CustomBeanWrapper e = new CustomBeanWrapper( f.getWrappedObject());
		Iterator d =  params.entrySet().iterator();
		while (d.hasNext()) {
			Map.Entry c = (Map.Entry) d.next();
			String a = (String) c.getKey();
			if ("property".equals(a))
				i = c.getValue().toString();
			if (!"formate".equals(a))
				continue;
			h = c.getValue().toString();
		}
		if (i == null)
			throw new SysException("param 'property' is null");

		Object b = e.getPropertyValueRecursively(i);
		b = b == null ? "" : b;
		env.getOut().write(e.getPropertyValueRecursively(i).toString());
	}
}

package com.loit.core.utils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class TemplateUtils {

	private static Configuration config = new Configuration();

	static {
		config.setClassForTemplateLoading(TemplateUtils.class, "/");
		config.setObjectWrapper(new DefaultObjectWrapper());
		config.setDefaultEncoding("UTF-8");
	}

	public static String process(String templateName, Map<String, Object> dataModel) throws IOException,
			TemplateException {
		StringWriter b = new StringWriter();
		Template a;
		config.getTemplate(templateName).process(dataModel, b);
		return b.toString();
	}

}

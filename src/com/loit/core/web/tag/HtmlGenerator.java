package com.loit.core.web.tag;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import com.loit.core.utils.TemplateUtils;
import com.loit.core.web.freemark.CustomObjectWrapper;
import com.loit.core.web.freemark.OutDirective;
import com.loit.core.web.freemark.PagingDirective;
//import com.loit.core.web.freemark.SysCodeDirective;
import com.loit.core.web.tag.support.CustomMap;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class HtmlGenerator {
	private static Configuration cfg = new Configuration();

	static {
		cfg.setObjectWrapper(new CustomObjectWrapper());
		cfg.setDefaultEncoding("UTF-8");
		cfg.setSharedVariable("out", new OutDirective());
		cfg.setSharedVariable("paging", new PagingDirective());
		//cfg.setSharedVariable("syscode", new SysCodeDirective());
	}

	public static String process(String name, String ftl, Map<String, Object> dataModel) throws Exception {
		Template b = new Template(null, new StringReader(ftl), cfg);
		StringWriter a = new StringWriter();
		b.process(dataModel, a);
		return a.toString();
	}

	public static String process(String templateName, CustomMap dataModel) throws Exception {
		return TemplateUtils.process(templateName + ".ftl", dataModel);
	}
}

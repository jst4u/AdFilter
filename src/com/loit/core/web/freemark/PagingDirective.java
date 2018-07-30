package com.loit.core.web.freemark;

import java.io.IOException;
import java.util.Map;

import com.loit.core.commom.query.QueryData;
import com.loit.core.exception.SysException;
import com.loit.core.web.tag.TagGenerator;
import com.loit.core.web.tag.support.CustomMap;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;

public class PagingDirective implements TemplateDirectiveModel {
	public void execute(Environment env, Map map, TemplateModel[] model, TemplateDirectiveBody body) throws TemplateException, IOException {
		TemplateHashModel e;
		StringModel d;
		if (!((d = (StringModel) (e = env.getDataModel()).get("data")).getWrappedObject() instanceof QueryData))
			throw new SysException("非CommonQueryService，不能翻页！");
		QueryData c = (QueryData) d.getWrappedObject();
		CustomMap b;
		(b = new CustomMap()).put("totalRows", Integer.valueOf(c.getPagingInfo().getTotalRows()));
		b.put("currentPage", Integer.valueOf(c.getPagingInfo().getCurrentPage()));
		b.put("totalPages", Integer.valueOf(c.getPagingInfo().getTotalPages()));
		b.put("id", e.get("id"));
		try {
			env.getOut().write(TagGenerator.generateTag("wltplcomponent_paging", b));
		} catch (Exception a) {
			throw new SysException(a);
		}
	}
}

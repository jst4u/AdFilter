package com.loit.core.web.tag;

import java.util.Map;

import com.loit.core.utils.TemplateUtils;

public class TagGenerator
{
  public static String generateTag(String templateName, Map<String, Object> dataModel)
    throws Exception
  {
    return TemplateUtils.process("/template/ui/" + templateName + ".ftl", dataModel);
  }
}

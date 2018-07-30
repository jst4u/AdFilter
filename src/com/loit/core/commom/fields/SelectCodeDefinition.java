package com.loit.core.commom.fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.loit.core.commom.query.CommonQueryManager;
import com.loit.core.hibernate.model.BaseObject;
import com.loit.core.spring.SpringContext;

public class SelectCodeDefinition extends BaseObject
{
  public static final String DEFINITION_BEAN_ID_PREFIX = "selectCode.";
  public static final String SYSTEM_CODE_TEMPLATE_BEAN_ID = "selectCode.SystemCodeTemplate";
  private String D;
  private List<QueryField> d;
  private String C;
  private String c;
  private String B;
  private String b;
  private Map<String, String> A;
  private Map<Locale, String> a = new HashMap<Locale, String>();

  public String getQueryType() {
    return this.D;
  }
  public void setQueryType(String queryType) {
    this.D = queryType;
  }
  public List<QueryField> getQueryFields() {
    return this.d;
  }
  public void setQueryFields(List<QueryField> queryFields) {
    this.d = queryFields;
  }
  public String getOrderBy() {
    return this.C;
  }
  public void setOrderBy(String orderBy) {
    this.C = orderBy;
  }
  public String getKeyFieldName() {
    return this.c;
  }
  public void setKeyFieldName(String keyFieldName) {
    this.c = keyFieldName;
  }
  public String getLabelFieldName() {
    Locale h = LocaleContextHolder.getLocale();
    if (!this.a.containsKey(h)) {
      synchronized (this.a) {
        if (!this.a.containsKey(h))
        {
          String g;
          String f = g = this.B;

          String[] e = h.toString().split("_");
          List d = new ArrayList();
          List c = ((CommonQueryManager)SpringContext.getBeanOfType(CommonQueryManager.class)).getQueryDataItemFields(this.D);
          String[] arrayOfString1;
          int j = (arrayOfString1 = e).length;
          for (int i = 0; i < j; i++) { String b = arrayOfString1[i];
            d.add(b);
            String a = StringUtils.join(d, '_');
            if ((this.A != null) && (this.A.containsKey(a))) {
              g = (String)this.A.get(a);
            } else {
              f = f + b.substring(0, 1).toUpperCase() + b.substring(1).toLowerCase();
              if (c.contains(f)) {
                g = f;
              }
            }
          }
          this.a.put(h, g);
          return g;
        }
      }
    }
    return (String)this.a.get(h);
  }
  public void setLabelFieldName(String labelFieldName) {
    this.B = labelFieldName;
  }
  public String getIconFieldName() {
    return this.b;
  }
  public void setIconFieldName(String iconFieldName) {
    this.b = iconFieldName;
  }
  public Map<String, String> getLocaledLabelFieldNames() {
    return this.A;
  }
  public void setLocaledLabelFieldNames(Map<String, String> localedLabelFieldNames) {
    this.A = localedLabelFieldNames;
  }
}
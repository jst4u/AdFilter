package com.loit.core.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;



public class NavigationTag extends BodyTagSupport
{
  public int doStartTag()
    throws JspException
  {
    return 1;
  }

  public int doEndTag() throws JspException {
    return 6;
  }
}
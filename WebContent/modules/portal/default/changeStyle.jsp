<%@page import="com.loit.core.commom.SysConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/loit.tld" prefix="loit" %>
<%
	String contextPath = request.getContextPath();
	String themesName =request.getParameter("tn");
	if(null == themesName || themesName.equals("")){//url指定的样式级别最高
		themesName = (String)request.getSession().getAttribute("THEME_DEFAULT");//当前用户session的样式级别第二
		if(null == themesName || themesName.equals("")){//都没有 就取系统默认样式
			themesName = SysConfig.getSysCfg("THEME_DEFAULT");
		}
		if(null == themesName || themesName.equals("")){//系统忘了配置了，设置一个最默认的
			themesName = "default";
		}
	}else{
		if(",bootstrap,black,cupertino,dark,default,gray,metro,mblue,mgray,mgreen,morange,mred,".indexOf(","+themesName+",")<0){
			themesName = "default";
		}
	}
	 request.getSession().setAttribute("THEME_DEFAULT",themesName);
%>

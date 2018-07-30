<%@page import="com.loit.core.commom.SysConfig"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
	//String systemName = SocketProUtil.getSystemProperties().getProperty("esm_type");

	request.getRequestDispatcher(SysConfig.getSysCfg("url.login")).forward(request, response);
	
	//response.sendRedirect(url+"/modules/apps/login/login.jsp");
%>

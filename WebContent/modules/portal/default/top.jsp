<%@page import="com.loit.apps.login.CurrentUser" import="java.util.*" import ="java.text.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<link class="ui_themes_class" rel="stylesheet" type="text/css" href="<%=contextPath%>/modules/portal/default/rs/frame.css" />
<title>系统标题</title>
</head>
<body style="border: 0; font-family: 微软雅黑; font-size: 14px; color: black; background:url(<%=contextPath%>/modules/apps/login/images/bg0.jpg) no-repeat 0 -10px;">
	<div id="logo" style="float:left; margin:13px 0 0 10px;">
		<img id="logo_img" src="<%=contextPath%>/modules/apps/login/images/nlogo.png" style="height:50px;">
	</div>
	<div id="sysname_img"
		style="float: left; position: relative; left: 10px; bottom: -17px; display: none">
		<img id="sysname_img" src="rs/img/top-sysname.png" height="38">
	</div>
	<div id="user_txt" style="float: left; padding:40px 0 0 60px;">
		<span>
			<%=CurrentUser.currentUser().getFullname()%>，欢迎您！&nbsp;&nbsp;
		</span> 
		<span id="localdate" style="">
		</span>
	</div>
	<div style="float:right; padding:13px 26px 0 0;">
	    <div style="">
		样式- 
		  <select id="styleName" onchange="changeStyle(this.value)" value="">
			<optgroup label="原生样式" style="font-weight: bold;">原生样式</optgroup>
			<option value="default">default</option>
			<option value="black">black</option>
			<option value="bootstrap">bootstrap</option>
			<option value="gray">gray</option>
			<option value="metro">metro</option>
			<optgroup label="metro样式" style="font-weight: bold;"></optgroup>
			<option value="mblue">mblue</option>
			<option value="mgray">mgray</option>
			<option value="mgreen">mgreen</option>
			<option value="morange">morange</option>
			<option value="mred">mred</option>
		  </select>
	   </div>
		<div style="float: right; margin-top:15px; margin-right: 10px; cursor: pointer; background:url(rs/img/exit.png) no-repeat; padding-left:30px; line-height:22px;"
			onclick="logout()" title="退出本系统">
			<span>退出</span>
		</div>
		<div style="float: right; margin-top:15px; margin-right: 15px; cursor: pointer; background:url(rs/img/pass.png) no-repeat; padding-left:30px; line-height:22px;"
			onclick="to_changePassword()" title="修改用户密码">
			<span>密码</span>
		</div>
	</div>
	
	
</body>
</html>
<script language="javascript">

var reg = 0;

$.ajaxSetup({cache: false });
$(function() {
	setInterval("localdate()",1000);
});

function logout(){
	window.parent.logout();
}

function to_changePassword() {
	//top.to_changePassword();
	window.open("changePassword.jsp", '修改密码', 'height=156,width=326');
}

function changeStyle(styleName){
	var changeUrl = "changeStyle.jsp";
	$.get(changeUrl, {tn:styleName}, function (data, textStatus){
		top.location = top.location;
	});
}
document.getElementById("styleName").value = '<%=themesName %>';

function localdate(){
	var today = new Date();
	var day; 
	if(today.getDay()==0) day = "星期日";
	if(today.getDay()==1) day = "星期一";
	if(today.getDay()==2) day = "星期二";
	if(today.getDay()==3) day = "星期三";
	if(today.getDay()==4) day = "星期四";
	if(today.getDay()==5) day = "星期五";
	if(today.getDay()==6) day = "星期六";
	
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	var hms = (h < 10 ? '0' + h : h) + ":" + (m < 10 ? '0' + m : m) 	+ ":" + (s < 10 ? '0' + s : s);
	var date = (today.getFullYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日" + "&nbsp;"+hms+"&nbsp;&nbsp;"+day ;
	$("#localdate").html(date);
}
</script>
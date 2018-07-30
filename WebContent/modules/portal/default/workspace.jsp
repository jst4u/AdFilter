<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<script type="text/javascript" src="<%=contextPath%>/modules/apps/palace/js/jquery.maphilight.js"></script>
</head>
<div  align="center" style="position:fixed;top:0;left:0;bottom:0;right:0;z-index:-1; ">
<img src="/palace/modules/apps/palace/images/workplace.gif" class="map" usemap="#Map">
<map name="Map">
<!-- 
  <area shape="circle" coords="97,441,57" href="#">
  <area shape="circle" coords="200,302,58" href="#" >
  <area shape="circle" coords="294,441,57" href="#" >
  -->
  <area id="area1" shape="circle" coords="302,160,56" title="有网关状态异常，点击查看详情。" href="http://localhost:8080/palace/modules/apps/palace/appGatewayBase.jsp">
  <area id="area2" shape="circle" coords="399,302,59" title="有中继状态异常，点击查看详情。" href="http://localhost:8080/palace/modules/apps/palace/appRelayBase.jsp">
  <area id="area3" shape="circle" coords="491,440,58" title="有节点状态异常，点击查看详情。" href="http://localhost:8080/palace/modules/apps/palace/appNodeBase.jsp">
  <area id="area4" shape="circle" coords="710,440,57" title="有电池状态异常，点击查看详情。" href="http://localhost:8080/palace/modules/apps/palace/appBatteryBase.jsp">
<!-- 
  <area shape="circle" coords="809,301,59" href="#">
  <area shape="circle" coords="909,441,59" href="#">
  <area shape="circle" coords="910,160,59" href="#">
  <area shape="circle" coords="1008,301,58" href="#">
  <area shape="circle" coords="1106,440,58" href="#">
  -->
</map>
</div>
</html>
<SCRIPT LANGUAGE="JavaScript">
//初始化变量，标示为设为假，网关、中继、节点、电池异常条数设为0
var flag = false;
var gatewayCount = 0;
var relayCount = 0;
var nodeCount = 0;
var batteryCount = 0;

//30秒刷新一次页面
function myrefresh(){
	window.location.reload();
	}
setTimeout('myrefresh()',10000); //指定20秒刷新一次 

//页面载入执行查询和设置闪动方法
$(document).ready(function() {
	$('.map').maphilight();
	go();
	});

//设置热点闪烁，实质是改变热点颜色是否一直显示属性
function DealMD(v)
{		
		var data = $(v).data('maphilight') || {};
		data.fill = true;
		data.fillColor = 'ff0000'; //填充颜色
		data.fillOpacity = 0.8; //填充颜色透明度
		data.stroke = true; //是否有边框
		data.strokeColor = '000000';
		data.strokeOpacity = 0;
		data.strokeWidth = 1;
		data.fade = true;
		data.alwaysOn = flag; //表示总显示
		data.neverOn = false;
		data.groupBy = false;
		data.wrapClass = true;
		
		$(v).data('maphilight', data); //改变v(也就是点击区域)区域的高亮数据
		$('.map').maphilight(); //刷新下

	}

//根据不同设备的异常条数设置图片上对应的热点
function wink(){
	
	flag = !flag;
	if(gatewayCount>0){
		
		DealMD($("#area1"));
	}
	if(relayCount>0){
		
		DealMD($("#area2"));
	}
	if(nodeCount>0){
		
		DealMD($("#area3"));
	}
	if(batteryCount>0){
		
		DealMD($("#area4"));
	}
	
}


var winktime=null;
function go(){
//快速变换属性起到闪烁的效果
  winktime=setInterval('wink()',200);
//查询设备异常条数
  getGatewayCount();
  getRelayCount();
  getNodeCount();
  getBatteryCount();
  
}

function getGatewayCount(){
	
	appGatewayBaseManager.getExceptionCount( function(data) {
		gatewayCount = data;
	})
  
}
function getRelayCount(){
	
	appRelayBaseManager.getExceptionCount( function(data) {
		relayCount = data;
	})
  
}
function getNodeCount(){
	
	appNodeBaseManager.getExceptionCount( function(data) {
		nodeCount = data;
	})
  
}

function getBatteryCount(){
	
	appBatteryBaseManager.getExceptionCount( function(data) {
		batteryCount = data;
	})
  
}



</SCRIPT>
<%@page import="java.util.List"%>
<%@page import="com.loit.apps.system.service.SelfSystemManagerImpl"%>
<%@page import="com.loit.apps.system.model.SysFunctionModel"%>
<%@page import="com.loit.core.spring.CommonManager"%>
<%@page import="com.loit.core.spring.SpringContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp" %>
<style>
.menu-show, .menu-hide,.banner-show,.banner-hide{
   	margin-top:5px;
   	margin-left:5px;
	width:13px;
	height:13px;
	cursor:hand;
	background:red;
} 
.menu-show {
	background: url(rs/img/control-right.gif) no-repeat;
}
.menu-hide{
    background: url(rs/img/control-left.gif) no-repeat;
} 

.banner-show{
   background: url(rs/img/control-down.gif) no-repeat;
} 

.banner-hide{
  background: url(rs/img/control-up.gif) no-repeat;
} 
</style>
<script language="javascript">
function switchMenuDisplay(){
	if($("#left-control").hasClass("menu-hide")){
		window.parent.hideLeft();
		$("#left-control").removeClass("menu-hide").addClass("menu-show").attr("title", "显示菜单");
	}else{
		window.parent.showLeft();
		$("#left-control").removeClass("menu-show").addClass("menu-hide").attr("title", "收起菜单");
	}
}

function switchBannerDisplay(){
	if($("#banner-control").hasClass("banner-hide")){
		window.parent.hideTop();
		$("#banner-control").removeClass("banner-hide").addClass("banner-show").attr("title", "显示系统标题");
	}else{
		window.parent.showTop();
		$("#banner-control").removeClass("banner-show").addClass("banner-hide").attr("title", "收起系统标题");
	}
}
function selectMenu(){
	var selectFunc = $(this).tabs('getSelected');
	var funcId = selectFunc.attr("id");
	if (funcId == "index") {//是首页
		window.parent.hideLeft();
		$("#left-control").removeClass("menu-hide").addClass("menu-show").attr("title", "显示菜单");
		window.parent.document.getElementsByTagName("frame")["mainFrame"].contentWindow.location.href=contextPath + "/modules/portal/default/workspace.jsp";
		return;
	} else {
		parent.frames['leftFrame'].location.href = "menu.jsp?tn=<%=themesName %>&funcId="+funcId;
		if($("#left-control").hasClass("menu-show")){
			window.parent.showLeft();
			$("#left-control").removeClass("menu-show").addClass("menu-hide").attr("title", "收起菜单");
		}
	}
}

</script>
<title>一级顶部菜单</title>
</head>
<body class="easyui-layout" style="background:url(/loit/modules/apps/login/images/bg0.jpg) no-repeat 0 -86px;">
	<div data-options="region:'west',border:false"  style="width:30px;padding:2px;">
			<div id="left-control" class="menu-show" onclick="switchMenuDisplay(); " title=收起菜单">
			</div>
	</div>
	<div data-options="region:'center',border:false" style="width:30px;overflow:hidden;" >
		 	<div id="tt" class="easyui-tabs"  data-options="fit:true,border:false,plain:true,onSelect:selectMenu"   >  
					<div title="首页" id="index" >  
		    		</div>
			<%
				//获取用户一级菜单
				SelfSystemManagerImpl ssm = SpringContext.getBeanOfType(SelfSystemManagerImpl.class);
				
				List<SysFunctionModel> sfmList = ssm.getTopFunctionForCurrentUser();
				for(int i=0;i<sfmList.size();i++){
					SysFunctionModel func = sfmList.get(i);
					%>
					<div title="<%=func.getName()%>"  id="<%=func.getFuncId() %>"  defaultview="<%=func.getViewname() %>" >  
		    		</div>  
					<%
					
				}
			%>
		     
		</div>  
	</div>
	<div data-options="region:'east',border:false" style="width:30px;padding:2px;">
			<div id="banner-control" class="banner-hide" onclick="switchBannerDisplay();" title="收起系统标题">
				
			</div>
	</div>
	
</body>
</html>
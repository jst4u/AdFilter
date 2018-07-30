<%@page import="com.loit.core.utils.StringUtil"%>
<%@page import="com.loit.core.spring.SpringContext"%>
<%@page import="java.util.List"%>
<%@page import="com.loit.apps.system.service.SelfSystemManagerImpl"%>
<%@page import="com.loit.apps.system.model.SysFunctionModel"%>
<%@page import="com.loit.apps.system.service.SysFunctionManagerImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<style type="text/css">
.menuitem {
   cursor: pointer;
   text-align:center;
   padding:36px 0 10px;
   background:url(<%=contextPath%>/modules/portal/menuimg/default.png) no-repeat center 0;
}

.menuitem:hover{background-image:url(<%=contextPath%>/modules/portal/menuimg/defaulto.png);}

.clickedMenuitem {
   background:url(<%=contextPath%>/modules/portal/menuimg/defaulto.png) no-repeat center 0;
}
</style>

<script language="javascript">
function selectItem(funcLink, funcName, funcId){
	if(funcLink.indexOf("http://") == 0){
		window.parent.document.getElementsByTagName("frame")["mainFrame"].contentWindow.location.href=funcLink;
	}else{
		funcLink=contextPath+funcLink;
	}
	//parent.frames["mainFrame"].location.href = funcLink;
	window.parent.document.getElementsByTagName("frame")["mainFrame"].contentWindow.location.href=funcLink;
 	$(".clickedMenuitem").removeClass("clickedMenuitem");
	$("#" + funcId).addClass("clickedMenuitem");
}
</script>
<title>二级左侧菜单</title>
</head>				
<body style="background-color:#F6FAFD;">
	<%
	//当前菜单名称
	String id = request.getParameter("funcId");
	if(null == id){
		return;
	}
	System.out.println(id);
	
	//获取用户所有菜单
	SelfSystemManagerImpl ssm = SpringContext.getBeanOfType(SelfSystemManagerImpl.class);
	List<SysFunctionModel> sfmList = ssm.getAllCurrentUserFunction();
	
	%>
	<loit:panel id="p" fit="true" style="border-bottom:0px">	
		<loit:accordion fit="true" border="false">
			<%
			//获得二级分类
			for (int i = 0; i < sfmList.size(); i++) {
				SysFunctionModel secLevelFunc = sfmList.get(i);
				if (null != secLevelFunc.getParentId() && secLevelFunc.getParentId().equals(id)) {
					%>
					<div title="<%=secLevelFunc.getName() %>" style="padding: 10px;">
					<%
					//获得二级分类下的第三级菜单
					for (int j = 0; j < sfmList.size(); j++) {
						SysFunctionModel thirdLevelFunc = sfmList.get(j);
						if (null != thirdLevelFunc.getParentId() && thirdLevelFunc.getParentId().equals(secLevelFunc.getFuncId())) {
							String funcId = thirdLevelFunc.getFuncId();
							String funcImg = thirdLevelFunc.getFuncImg();
							if (null ==funcImg || funcImg.trim().length()==0){
								funcImg = "/default.png";
							}
							funcImg = contextPath+"/modules/portal/menuimg"+funcImg;
							
							String funcName = thirdLevelFunc.getName() ;
							if(null == funcName){
								funcName="";
							}
							
							String funcLink = thirdLevelFunc.getViewname();
							if(null == funcLink){
								funcLink="";
							}
							
							String remarks =   thirdLevelFunc.getRemarks();
							if(null == remarks || "".equals(remarks)){
								remarks=funcName;
							}
							%>
							<div id="<%=funcId %>" class="menuitem" title="<%=remarks %>" onclick="selectItem('<%=funcLink%>', '<%=funcName%>', '<%=funcId %>')">
									<div><%=funcName%></div>
							</div>
							<%
						} else {
							continue;
						}
				} %>
					</div>
					<%
				} else {
					continue;
				}
			}
			%>
		</loit:accordion>
		
	</loit:panel>	
</body>
</html>
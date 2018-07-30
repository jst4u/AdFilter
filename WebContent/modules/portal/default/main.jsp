<%@page import="com.loit.core.commom.SysConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/base/include/header.jsp" %>
<title><%=SysConfig.getSysCfg("APP_NAME")%></title>
<script>
	function logout() {
		location.href = "<%=request.getContextPath()+SysConfig.getSysCfg("url.logout")%>";
	}

	function hideLeft() {
		var col = window.frames.centerMain.cols;
		col1 = col.substring(col.indexOf(","));
		window.frames.centerMain.cols = 0 + col1;
	}
	function showLeft() {
		var col = window.frames.centerMain.cols;
		col1 = col.substring(col.indexOf(","));
		window.frames.centerMain.cols = 120 + col1;
	}
	function showTop() {
		window.frames.WebFrameSet.rows = "76,30,*,28";
	}
	function hideTop() {
		window.frames.WebFrameSet.rows = "0,28,*,18";
	}

</script>
</head>

<frameset rows="76,30,*,28" cols="*" frameborder="no" border="0" framespacing="0" name="WebFrameSet" id="WebFrameSet">

	<frame border="0" frameborder="no" marginheight="0" marginwidth="0" src="top.jsp?tn=<%=themesName %>"
		name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />

	<frame border="0" frameborder="no" marginheight="0" marginwidth="0" leftmargin="0" topmargin="0"
		src="control-top.jsp?tn=<%=themesName %>" name="controltopFrame" scrolling="no" noresize="noresize"
		id="controltopFrame" />

	<frameset cols="0,*" t border="0" frameborder="0" framespacing="0" marginwidth="0" marginheight="0" leftmargin="0"
		topmargin="0" id="centerMain">
		<frame src="menu.jsp?tn=<%=themesName %>" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" />
		<frame border="0" frameborder="no" src="workspace.jsp?tn=<%=themesName %>" name="mainFrame"  id="mainFrame" />
	</frameset>

	<frame border="0" frameborder="no" marginheight="0" marginwidth="0" leftmargin="0" topmargin="0"
		src="frame_footer.jsp?tn=<%=themesName %>" name="footerFrame" scrolling="no" noresize="noresize" id="footerFrame" />


</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
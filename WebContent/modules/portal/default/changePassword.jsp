<%@page import="com.loit.core.commom.SysConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<%@include file="/base/include/header.jsp"%>
		<title><%=SysConfig.getSysCfg("APP_NAME")%></title>
		<script>
			function changePassword(){
				if ($("#changePasswordForm").form('validate')) {
					var param = $("#changePasswordForm").form("toJSON");
					var oldPassword = param.oldPassword;
					var newPassword = param.newPassword;
					var confirmPassword = param.confirmPassword;
					if (newPassword != confirmPassword) {
						$.messager.alert("提示信息", "新密码与确认密码不一致，请修改后再保存！", "info");
						return;
					}
					$.messager.progress();
					sysUserManager.changePassword(oldPassword, newPassword, function(data) {
						$.messager.progress("close");
						if (data == 1) {
							$.messager.alert("提示信息", "密码修改成功！", "info", function(data) {
								window.close();
							});
						} else if (data == 0) {
							$("#changePasswordForm").form('clear');
							$.messager.alert("提示信息", "原密码错误！", "info");
						}
					});
				} else {
					$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
					return;
				}
			}
		</script>
	</head>
	
	<body>
		<loit:panel id="changePasswordWin" title="修改密码" style="width: 330px; height:160px; padding: 10px;">
			<loit:form id="changePasswordForm" method="post" type="ft">
				<tr>
					<loit:thd label="旧密码:" tagType="validatebox" type="Password" name="oldPassword" required="true" style="width:170px"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="新密码:" tagType="validatebox" type="Password" name="newPassword" required="true" style="width:170px"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="确认密码:" tagType="validatebox" type="Password" name="confirmPassword" required="true" style="width:170px"></loit:thd>
				</tr>
				<tr>
				<loit:thd tcolspan="4" tstyle="text-align: center">
					<loit:linkbutton iconCls="icon-save" onclick="changePassword()">确定</loit:linkbutton>
					<loit:linkbutton iconCls="icon-cancel" onclick="window.close();">取消</loit:linkbutton>
				</loit:thd>
				</tr>
			</loit:form>
		</loit:panel>
		
	</body>
</html>
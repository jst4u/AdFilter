<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>角色管理</title>
</head>
<loit:body>
	<loit:panel title="角色管理" fit="true" style=" padding: 5px;">
		<loit:layout fit="true">
			<loit:layoutCenter title="系统角色" style="padding: 0px" iconCls="tree-file">
				<loit:layout fit="true">
					<loit:layoutNorth title="查询条件" style="height:70px" iconCls="icon-search">
						<div style="padding: 5px">
							<loit:form id="queryformSysRole" method="post">
								<table align="left">
									<tr>
										<loit:thd label="角色ID:" tagType="validatebox" name="roleId" operator="likeAnywhere"></loit:thd>
										<loit:thd label="角色编号:" tagType="validatebox" name="code" operator="likeAnywhere"></loit:thd>
										<loit:thd label="角色名称:" tagType="validatebox" name="name" operator="likeAnywhere"></loit:thd>
										<loit:thd>
											<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>&nbsp;&nbsp;
			    						</loit:thd>
										<loit:thd>
											<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
										</loit:thd>
									</tr>
								</table>
							</loit:form>
						</div>
					</loit:layoutNorth>
					<loit:layoutCenter title="查询结果" iconCls="icon-ok" tools="#gridButton">
						<loit:datagrid id="dgSysRole" queryform="#queryformSysRole" querysource="com.loit.apps.system.model.SysRoleModel" rownumbers="true" singleSelect="true" pagination="true" fit="true" border="false" onDblClickRow="onDCRoles" onClickRow="onSCRoles">
							<loit:column field="roleId" title="角色ID"  hidden="true"></loit:column>
							<loit:column field="code" title="角色编码"></loit:column>
							<loit:column field="name" title="角色名称"></loit:column>
							<loit:column field="state" title="角色状态"></loit:column>
							<loit:column field="remarks" title="角色备注"></loit:column>
							<loit:column field="createTime" title="创建时间"></loit:column>
							<loit:column field="modifyTime" title="修改时间"></loit:column>
							<loit:column field="recVer" title="版本号"></loit:column>
						</loit:datagrid>
					</loit:layoutCenter>
				</loit:layout>
			</loit:layoutCenter>
			<loit:layoutEast split="false" style="width: 300px;">
				<loit:panel title="角色权限" style="padding: 0px; " fit="true" border="false" iconCls="tree-file">
					<loit:panel title="角色用户" style="padding: 0px; overflow-y:hidden;" border="false" iconCls="tree-file" tools="#userButton">
						<loit:datagrid id="userTable" iconCls="icon-ok"  queryform="hiddenform" querysource="sql:select u.user_Id,u.login_Name,u.name,u.code,ur.Role_User_Id from Sys_USER u, Sys_User_Role ur where u.user_Id=ur.user_Id and ur.role_Id='{roleId}'" singleSelect="true" sortName="name" sortOrder="desc" url="none" onDblClickRow="edit_user" style="height:223px" >
							<loit:column field="USER_ID" width="40" hidden="true" title="用户ID"></loit:column>
							<loit:column field="LOGIN_NAME" width="80" align="center" sortable="true" title="登陆名称"></loit:column>
							<loit:column field="NAME" width="80" align="center" sortable="true" title="用户名称"></loit:column>
						</loit:datagrid>
					</loit:panel>
					<form id="hiddenform" method="post">
						<input type="hidden" id="hRoleId" name="roleId">
					</form>
					<loit:panel title="角色功能" style="padding: 0px;" iconCls="tree-file" >
						<loit:tree id="funcTree" querysource="com.loit.apps.system.model.SysFunctionModel"  idField="funcId" textField="name" pidField="parentId" openLevel="2" animate="true" lines="true" checkbox="true"></loit:tree>
						<loit:linkbutton iconCls="icon-save" onclick="save_func()">保存</loit:linkbutton>
					</loit:panel>
				</loit:panel>
			</loit:layoutEast>
		</loit:layout>
	</loit:panel>
	<div id="gridButton">
		<loit:linkbutton tagClass="icon-add" onclick="to_add()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="to_edit()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="to_del()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-reload" onclick="query()"></loit:linkbutton>
	</div>
	<div id="userButton">
		<loit:linkbutton tagClass="icon-add" onclick="add_user()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="edit_user()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="del_user()"></loit:linkbutton>
	</div>
	<div id="funcButton">
		<loit:linkbutton tagClass="icon-add" onclick="add_func()"></loit:linkbutton>
	</div>
	<loit:window id="editWindowSysRole" title="Modal Window" modal="true" closed="true" style="width: 300px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformSysRole" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="roleId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="角色名称:" tagType="validatebox" name="name" required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="角色编码:" tagType="validatebox" name="code" required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="角色状态:" tagType="validatebox" name="state" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="角色备注:" tagType="validatebox" name="remarks" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd tcolspan="4" tstyle="text-align: center">
						<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindowSysRole').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
	<loit:window id="editWindowSysUserRole" title="Modal Window" modal="true" closed="true" style="width: 300px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformSysUserRole" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="roleUserId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="用户:" tagType="combobox" name="userId" codeType="userIdName" valueField="id" panelHeight="150" required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="角色:" tagType="combobox" name="roleId" codeType="roleIdName" valueField="id" panelHeight="150" required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="状态:" tagType="validatebox" name="state" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="标识:" tagType="validatebox" name="remarks" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd tcolspan="4" tstyle="text-align: center">
						<loit:linkbutton iconCls="icon-save" onclick="save_user()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindowSysUserRole').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgSysRole').datagrid('query');
	}
	
	function clearForm() {
		$('#queryformSysRole').form('clear');
	}

	//新增
	function to_add() {
		$("#editformSysRole").form('clear');
		$('#editWindowSysRole').window('setTitle', "增加");
		$('#editWindowSysRole').window('open');
	}

	//修改
	function to_edit() {
		var row = $('#dgSysRole').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.roleId;
		sysRoleManager.get(idValue, function(data) {
			$('#editformSysRole').form('load', data);
		});
		$('#editWindowSysRole').window('setTitle', "修改");
		$('#editWindowSysRole').window('open');
	}

	//保存
	function to_save() {
		if ($("#editformSysRole").form('validate')) {
			var data = $("#editformSysRole").form("toJSON");
			$.messager.progress();
			$('#editWindowSysRole').window('close');
			sysRoleManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
				$('#dgSysRole').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	
	//删除
	function to_del() {
		var row = $('#dgSysRole').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				sysRoleManager.delByPk(row.roleId, function(data) {
					$.messager.progress("close");
					if (data == 1) {
						$.messager.show({
							title:'系统消息',
							msg:'删除成功！',
							timeout:2000,
							showType:'slide'
						});
						$('#dgSysRole').datagrid('reload');
					} else if (data == 2) {
						$.messager.alert('提示信息', '已有用户赋予了该角色，不能进行删除操作！', 'info');
					} else if (data == 3) {
						$.messager.alert('提示信息', '该角色已赋予了权限，不能进行删除操作！', 'info');
					}
				});
			}
		});
	}
	
	//单击角色，查询拥有的权限
	function onSCRoles() {
		var roles = $('#dgSysRole').datagrid("getSelected");
		var idValue = roles.roleId;
		$("#hRoleId").attr("value", idValue);
		$('#userTable').datagrid('query');
		query_func();
	}
	
	//双击角色，打开用户编辑窗口
	function onDCRoles() {
		to_edit();
	}

	//新增用户角色
	function add_user() {
		$("#editformSysUserRole").form('clear');
		var role = $('#dgSysRole').datagrid("getSelected");
		if (role) {
			var roleId = role.roleId;
			var data = {
				"roleId" : roleId
			};
			$('#editWindowSysUserRole').form('load', data);
		}
		$('#editWindowSysUserRole').window('setTitle', "增加");
		$('#editWindowSysUserRole').window('open');
	}

	//修改用户角色
	function edit_user() {
		var row = $('#userTable').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.ROLE_USER_ID;
		sysUserRoleManager.get(idValue, function(data) {
			$('#editformSysUserRole').form('load', data);
		});

		$('#editWindowSysUserRole').window('setTitle', "修改");
		$('#editWindowSysUserRole').window('open');
	}

	//保存用户角色
	function save_user() {
		if ($("#editformSysUserRole").form('validate')) {
			var data = $("#editformSysUserRole").form("toJSON");
			$.messager.progress();
			$('#editWindowSysUserRole').window('close');
			sysUserRoleManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
				$('#userTable').datagrid('query');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除用户角色
	function del_user() {
		var row = $('#userTable').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		     if (res) {
				$.messager.progress();
				sysUserRoleManager.removeByPk(row.ROLE_USER_ID, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'删除成功！',
						timeout:2000,
						showType:'slide'
					});
					$('#userTable').datagrid('query');
				});
		     }
		});
	}

	//保存角色功能
	function save_func() {
		var func = $("#funcTree").tree("getChoosed");
		var role = $('#dgSysRole').datagrid("getSelected");
		var data;
		if (!role) {
			$.messager.alert('提示信息', '请选择要增加功能的角色！', 'info');
		}
		$.messager.progress();
		var roleId = role.roleId;
		sysRoleFuncManager.deleteByExample({"roleId" : roleId}, function(rd) {
			for ( var i = 0; i < func.length; i++) {
				var funcId = func[i];
				data = {
					"roleId" : roleId,
					"funcId" : funcId
				};
				if (data) {
					sysRoleFuncManager.save(data, function(resdata) {
					});
				}
			}
			$.messager.progress("close");
			//query_func();
			$.messager.show({
				title:'系统消息',
				msg:'保存成功！',
				timeout:2000,
				showType:'slide'
			});
		});
	}
	
	//查询角色功能
	function query_func() {
		var selected = $('#funcTree').tree('getChecked');
		for ( var i = 0; i < selected.length; i++) {
			$('#funcTree').tree('uncheck', selected[i].target);
		}
		var roles = $('#dgSysRole').datagrid("getSelected");
		var idValue = roles.roleId;
		sysRoleFuncManager.findNodesByExample({"roleId" : idValue}, function(data) {
			for ( var i = 0; i < data.length; i++) {
				var fid = data[i].funcId;
				var node = $('#funcTree').tree('find', fid);
				$('#funcTree').tree('check', node.target);
			}
		});
	}

</script>
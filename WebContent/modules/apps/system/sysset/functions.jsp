<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>

<title>菜单管理</title>
</head>
<body>
	<loit:panel id="panel1" title="菜单管理" fit="true" border="false" style="padding: 5px;">
		<loit:layout fit="true">
			<loit:layoutWest style="width: 800px" split="false">
				<loit:panel id="panel2" title="系统菜单列表" fit="true" border="false" iconCls="tree-file" tools="#funcTreeButton">
					<loit:treegrid startId="1" id="tdg" onDblClickRow="onDCFunc" onClickRow="onSCGridRow" border="false" animate="false" fit="true" rownumbers="true" idField="funcId" treeField="name" pageSize="5000" sortName="funSeq" sortOrder="asc" querysource="com.loit.apps.system.model.SysFunctionModel" pidField="parentId">
						<loit:column field="funcId" title="功能ID" hidden="true"></loit:column>
						<loit:column field="name" title="功能名称"></loit:column>
						<loit:column field="viewname" title="功能路径"></loit:column>
						<loit:column field="createTime" title="创建时间"></loit:column>
						<loit:column field="modifyTime" title="修改时间"></loit:column>
						<loit:column field="recVer" title="版本号"></loit:column>
					</loit:treegrid>
				</loit:panel>
			</loit:layoutWest>
			<loit:layoutCenter iconCls="tree-file" title="功能权限分配信息" style="padding: 0px">
				<loit:datagrid id="roleTable" iconCls="icon-ok" title="分配的角色" queryform="hiddenform" querysource="sql:select  r.role_Id,r.name,r.code,rf.role_Func_Id from Sys_Role r, Sys_Role_Func rf where r.role_Id=rf.role_Id and rf.func_Id='{funcId}'" singleSelect="true" sortName="name" sortOrder="desc" url="none" rownumbers="true" onDblClickRow="edit_role" style="height:224px" tools="#roleButton">
					<loit:column field="ROLE_ID" width="40" hidden="true" title="角色ID"></loit:column>
					<loit:column field="NAME" width="80" align="center" sortable="true" title="角色名称"></loit:column>
					<loit:column field="CODE" width="90" align="center" sortable="true" title="角色编码"></loit:column>
				</loit:datagrid>
				<form id="hiddenform" method="post">
					<input type="hidden" id="hFuncId" name="funcId">
				</form>
				<loit:datagrid id="userTable" iconCls="icon-ok" title="分配的用户" queryform="hiddenform" querysource="sql:select u.user_Id,u.login_Name,u.name,uf.user_Func_Id from Sys_User u, Sys_User_Func uf where u.user_Id = uf.user_Id and uf.func_Id='{funcId}'" singleSelect="true" sortName="name" sortOrder="desc" url="none" rownumbers="true" onDblClickRow="edit_user" style="height:224px" tools="#userButton">
					<loit:column field="USER_ID" width="40" hidden="true" title="用户ID"></loit:column>
					<loit:column field="LOGIN_NAME" width="80" align="center" sortable="true" title="登陆名称"></loit:column>
					<loit:column field="NAME" width="90" align="center" sortable="true" title="用户名称"></loit:column>
				</loit:datagrid>
			</loit:layoutCenter>
		</loit:layout>
	</loit:panel>
	<div id="funcTreeButton">
		<loit:linkbutton tagClass="icon-add" onclick="addFunc()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="editFunc()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="removeFunc()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-reload" onclick="reloadFunc()"></loit:linkbutton>
	</div>
	<div id="roleButton">
		<loit:linkbutton tagClass="icon-add" onclick="add_role()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="edit_role()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="del_role()"></loit:linkbutton>
	</div>
	<div id="userButton">
		<loit:linkbutton tagClass="icon-add" onclick="add_user()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="edit_user()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="del_user()"></loit:linkbutton>
	</div>
	<loit:window id="w" title="Modal Window" modal="true" closed="true" style="width:420px;padding:5px;">
		<div>
			<loit:form id="ff" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="funcId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="功能名称:" tagType="validatebox" name="name" required="true"></loit:thd>
					<th>上级功能:</th>
					<td><loit:combotree startId="1" required="true" id="funcTree" name="parentId" panelWidth="200" querysource="com.loit.apps.system.model.SysFunctionModel" idField="funcId" textField="name" pidField="parentId"></loit:combotree></td>
				</tr>
				<tr>
					<loit:thd label="功能等级:" tagType="validatebox" name="funcLevel" required="false"></loit:thd>
					<loit:thd label="功能排序:" tagType="validatebox" name="funSeq"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="功能链接:" tagType="validatebox" name="viewname"></loit:thd>
					<loit:thd label="动态链接:" tagType="validatebox" name="dllPath" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="功能图标:" tagType="validatebox" name="funcImg"></loit:thd>
					<loit:thd label="功能参数:" tagType="validatebox" name="funcArg" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="功能类型:" tagType="validatebox" name="funcType" required="false"></loit:thd>
					<loit:thd label="是否可用:" tagType="validatebox" name="disabled" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="状态:" tagType="validatebox" name="state" required="false"></loit:thd>
					<loit:thd label="系统:" tagType="validatebox" name="sys" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="功能编码:" tagType="validatebox" name="funcCode"></loit:thd>
					<loit:thd label="备注:" tagType="validatebox" name="remarks" required="false"></loit:thd>
				</tr>
			</loit:form>
		</div>
		<div style="text-align: center; pading: 5px">
			<loit:linkbutton iconCls="icon-save" onclick="saveFunc()">保存</loit:linkbutton>
			<loit:linkbutton iconCls="icon-cancel" onclick="$('#w').window('close');">取消</loit:linkbutton>
		</div>
	</loit:window>
	<loit:window id="editWindowSysRoleFunc" title="Modal Window" modal="true" closed="true" style="width: 240px; padding: 5px;">
		<div style="text-align: center;">
			<loit:form id="editformSysRoleFunc" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="roleFuncId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="角色:" tagType="combobox" name="roleId" codeType="roleIdName" valueField="id" panelHeight="150" required="true"></loit:thd>
				</tr>
				<tr>
					<th>功能:</th>
					<td><loit:combotree startId="1" required="true" id="funcTree" name="funcId" panelWidth="200" querysource="com.loit.apps.system.model.SysFunctionModel" idField="funcId" textField="name" pidField="parentId"></loit:combotree></td>
				</tr>
				<tr>
					<loit:thd label="状态:" tagType="validatebox" name="state" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注:" tagType="validatebox" name="remarks" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd tcolspan="4" tstyle="text-align: center">
						<loit:linkbutton iconCls="icon-save" onclick="save_role()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindowSysRoleFunc').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
	<loit:window id="editWindowSysUserFunc" title="Modal Window" modal="true" closed="true" style="width: 240px; padding: 5px;">
		<div style="text-align: center;">
			<loit:form id="editformSysUserFunc" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="userFuncId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="用户:" tagType="combobox" name="userId" codeType="userIdName" valueField="id" panelHeight="150" required="true"></loit:thd>
				</tr>
				<tr>
					<th>功能:</th>
					<td><loit:combotree startId="1" required="true" id="funcTree" name="funcId" panelWidth="200" querysource="com.loit.apps.system.model.SysFunctionModel" idField="funcId" textField="name" pidField="parentId"></loit:combotree></td>
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
						<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindowSysUserFunc').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
</body>
</html>

<script language="javascript">
	function addFunc() {
		$("#ff").form('clear');
		var func = $('#tdg').treegrid("getSelected");
		if (func) {
			var funcId = func.funcId;
			var data = {
				"parentId" : funcId
			};
			$('#ff').form('load', data);
		}
		$('#w').window('setTitle', " 增加");
		$('#w').window('open');
	}

	function editFunc() {
		var func = $('#tdg').treegrid("getSelected");
		if (!func) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var funcId = func.funcId;
		sysFunctionManager.get(funcId, function(data) {
			$('#ff').form('reset');
			$('#ff').form('load', data);
			var t = $('#funcTree').combotree('tree'); // get the tree object
			var n = t.tree('getSelected');
			t.tree('expandTo', n.target);
		});

		$('#w').window('setTitle', '修改');
		$('#w').window('open');
	}

	function saveFunc() {
		if ($("#ff").form('validate')) {
			var data = $("#ff").form("toJSON");
			$.messager.progress();
			$('#w').window('close');
			sysFunctionManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:3000,
					showType:'slide'
				});
				reloadFunc();
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}

	function removeFunc() {
		var func = $('#tdg').treegrid("getSelected");
		if (!func) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				sysFunctionManager.delByPk(func.funcId, function(data) {
					$.messager.progress("close");
					if (data == 1) {
						$.messager.show({
							title:'系统消息',
							msg:'删除成功！',
							timeout:3000,
							showType:'slide'
						});
						reloadFunc();
					} else if (data == 2) {
						$.messager.alert('提示信息', '该功能模块存在下属模块，不能进行删除操作！', 'info');
					} else if (data == 3) {
						$.messager.alert('提示信息', '该功能模块已赋权给了角色，不能进行删除操作！', 'info');
					}
				});
			}
		});
	}

	function reloadFunc() {
		$('#tdg').treegrid('reload');
		$('#funcTree').combotree('reload');
	}
	//单击功能，查询拥有的用户和角色
	function onSCGridRow() {
		var row = $('#tdg').treegrid("getSelected");
		var idValue = row.funcId;
		$("#hFuncId").attr("value", idValue);
		$('#roleTable').datagrid('query');
		$('#userTable').datagrid('query');
	}
	//双击功能，打开功能编辑窗口
	function onDCFunc() {
		editFunc();
	}

	//新增用户功能
	function add_user() {
		$("#editformSysUserFunc").form('clear');
		var func = $('#tdg').treegrid("getSelected");
		if (func) {
			var funcId = func.funcId;
			var data = {
				"funcId" : funcId
			};
			$('#editformSysUserFunc').form('load', data);
		}
		$('#editWindowSysUserFunc').window('setTitle', "增加");
		$('#editWindowSysUserFunc').window('open');
	}
	//修改用户功能
	function edit_user() {
		var row = $('#userTable').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.USER_FUNC_ID;
		sysUserFuncManager.get(idValue, function(data) {
			$('#editformSysUserFunc').form('load', data);
		});

		$('#editWindowSysUserFunc').window('setTitle', "修改");
		$('#editWindowSysUserFunc').window('open');
	}
	//保存用户功能
	function save_user() {
		if ($("#editformSysUserFunc").form('validate')) {
			var data = $("#editformSysUserFunc").form("toJSON");
			$.messager.progress();
			$('#editWindowSysUserFunc').window('close');
			sysUserFuncManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:3000,
					showType:'slide'
				});
				$('#userTable').datagrid('query');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除用户功能
	function del_user() {
		var row = $('#userTable').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				sysUserFuncManager.removeByPk(row.USER_FUNC_ID, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'删除成功！',
						timeout:3000,
						showType:'slide'
					});
					$('#userTable').datagrid('query');
				});
			}
		});
	}

	//新增角色功能
	function add_role() {
		$("#editformSysRoleFunc").form('clear');
		var func = $('#tdg').treegrid("getSelected");
		if (func) {
			var funcId = func.funcId;
			var data = {
				"funcId" : funcId
			};
			$('#editWindowSysRoleFunc').form('load', data);
		}
		$('#editWindowSysRoleFunc').window('setTitle', "增加");
		$('#editWindowSysRoleFunc').window('open');
	}
	//修改角色功能
	function edit_role() {
		var row = $('#roleTable').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.ROLE_FUNC_ID;
		sysRoleFuncManager.get(idValue, function(data) {
			$('#editformSysRoleFunc').form('load', data);
		});

		$('#editWindowSysRoleFunc').window('setTitle', "修改");
		$('#editWindowSysRoleFunc').window('open');
	}
	//保存角色功能
	function save_role() {
		if ($("#editformSysRoleFunc").form('validate')) {
			var data = $("#editformSysRoleFunc").form("toJSON");
			$.messager.progress();
			$('#editWindowSysRoleFunc').window('close');
			sysRoleFuncManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:3000,
					showType:'slide'
				});
				$('#roleTable').datagrid('query');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除角色功能
	function del_role() {
		var row = $('#roleTable').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				sysRoleFuncManager.removeByPk(row.ROLE_FUNC_ID, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'删除成功！',
						timeout:3000,
						showType:'slide'
					});
					$('#roleTable').datagrid('query');
				});
			}
		});
	}
</script>
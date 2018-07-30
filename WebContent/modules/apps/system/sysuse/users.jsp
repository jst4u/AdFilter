<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>用户管理</title>
</head>
<body>
	<loit:panel id="panel1" title="用户管理" fit="true" style=" padding: 5px;">
		<loit:layout fit="true">
			<loit:layoutWest style="width: 180px" split="false">
				<loit:panel title="用户组织" style="padding: 5px;" fit="true" iconCls="tree-file" border="false">
					<loit:tree startId="1" id="orgTree" querysource="com.loit.apps.system.model.SysOrganizeModel" idField="organizeId" textField="name" pidField="parentOrganizeId" openLevel="3" animate="true" lines="true"></loit:tree>
				</loit:panel>
			</loit:layoutWest>
			<loit:layoutCenter title="系统用户" style="padding: 0px" iconCls="tree-file">
				<loit:layout fit="true">
					<loit:layoutNorth iconCls="icon-search" title="查询条件" style="height:70px">
						<div style="padding: 5px">
							<loit:form id="queryform" method="post">
								<table align="left">
									<tr>
										<input id="orgId" type="hidden" name="organizeId">
										<loit:thd label="登陆名称:" tagType="validatebox" name="loginName" operator="likeAnywhere"></loit:thd>
										<loit:thd label="用户姓名:" tagType="validatebox" name="name" operator="likeAnywhere"></loit:thd>
										<loit:thd label="用户状态:" tagType="combobox" name="state" id="state" panelHeight="auto" codeType="userStatus" codePlease="-----请选择-----" codeFilter="" editable="false" valueField="id" operator="likeAnywhere"></loit:thd>
										<loit:thd>
											<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>
										</loit:thd>
										<loit:thd>
											<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
										</loit:thd>
										<loit:thd>
											<loit:linkbutton iconCls="icon-ok" onclick="resetPSD()">重置密码</loit:linkbutton>
										</loit:thd>
									</tr>
								</table>
							</loit:form>
						</div>
					</loit:layoutNorth>
					<loit:layoutCenter title="查询结果" iconCls="icon-ok" tools="#gridButton">
						<loit:datagrid id="dg" queryform="queryform" querysource="service:sysUserManager.getUsers" rownumbers="true" singleSelect="true" pagination="true" fit="true" sortName="loginName" sortOrder="desc" idField="userId" onDblClickRow="onDCGridRow" onClickRow="onSCGridRow">
							<loit:column field="userId" hidden="true" title="用户ID"></loit:column>
							<loit:column field="loginName" sortable="true" title="登陆名称"></loit:column>
							<loit:column field="name" sortable="true" title="用户姓名"></loit:column>
							<loit:column field="organizeId" sortable="true" title="所属部门" codeType="organize"></loit:column>
							<loit:column field="mobileTele" title="移动电话"></loit:column>
							<loit:column field="officeTel" title="办公电话"></loit:column>
							<loit:column field="email" title="电子邮件"></loit:column>
							<loit:column field="addrId" title="地址"></loit:column>
							<loit:column field="state" sortable="true" codeType="userStatus" title="用户状态"></loit:column>
							<loit:column field="createTime" title="创建时间"></loit:column>
							<loit:column field="modifyTime" title="修改时间"></loit:column>
							<loit:column field="REMARKS" title="备注"></loit:column>
						</loit:datagrid>
					</loit:layoutCenter>
				</loit:layout>
			</loit:layoutCenter>
			<loit:layoutEast split="false" style="width: 200px;">
				<loit:panel title="用户权限" style="padding: 0px;" fit="true" iconCls="tree-file" border="false">
					<loit:datagrid id="userRoleTable" title="用户角色" queryform="hiddenform" iconCls="icon-ok" querysource="sql:select r.role_Id,r.name,r.code,ur.Role_User_id from Sys_Role r, Sys_User_Role ur where r.role_Id=ur.role_Id and ur.user_Id='{userId}'" singleSelect="true" sortName="name" sortOrder="desc" url="none" onDblClickRow="edit_role" style="height:222px"
						toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){add_role()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){edit_role()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){del_role()}}]">
						<loit:column field="ROLE_ID" width="40" hidden="true" title="角色ID"></loit:column>
						<loit:column field="NAME" width="80" align="center" sortable="true" title="角色名称"></loit:column>
						<loit:column field="CODE" width="90" align="center" sortable="true" title="角色编码"></loit:column>
					</loit:datagrid>
					<form id="hiddenform" method="post">
						<input type="hidden" id="hUserId" name="userId" value="userId">
					</form>
					<loit:datagrid id="userFuncTable" title="用户功能" queryform="hiddenform" iconCls="icon-ok" querysource="sql:select f.func_Id,f.func_Code,f.name,uf.user_Func_Id from Sys_Function f, Sys_User_Func uf where f.func_Id=uf.func_Id and uf.user_Id='{userId}'" singleSelect="true" sortName="name" sortOrder="desc" url="none" onDblClickRow="edit_func" style="height:222px"
						toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){add_func()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){edit_func()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){del_func()}}]">
						<loit:column field="USER_FUNC_ID" hidden="true" title="用户功能ID"></loit:column>
						<loit:column field="NAME" align="center" sortable="true" title="功能名称"></loit:column>
						<loit:column field="FUNC_CODE" align="center" sortable="true" title="功能编码"></loit:column>
					</loit:datagrid>
				</loit:panel>
			</loit:layoutEast>
		</loit:layout>
	</loit:panel>
	<div id="gridButton">
		<a href="javascript:void(0)" class="icon-add" title="增加" onclick="javascript:to_add()"></a> 
		<a href="javascript:void(0)" class="icon-edit" title="修改" onclick="javascript:to_edit()"></a> 
		<a href="javascript:void(0)" class="icon-remove" title="删除" onclick="javascript:to_del()"></a> 
		<a href="javascript:void(0)" class="icon-cancel" title="禁用" onclick="javascript:to_forbid()"></a> 
		<a href="javascript:void(0)" class="icon-ok" title="启用" onclick="javascript:to_enable()"></a> 
		<a href="javascript:void(0)" class="icon-reload" title="刷新" onclick="javascript:to_reload()"></a>
	</div>

	<loit:window id="addWindow" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="addform" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="userId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="登陆名称:" tagType="validatebox" name="loginName" required="true"></loit:thd>
					<loit:thd label="用户名:" tagType="validatebox" name="name" required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="用户编码:" tagType="validatebox" name="code"></loit:thd>
					<th>所属部门:</th>
					<td><loit:combotree startId="1" required="true" id="organizeId" name="organizeId" panelWidth="150" querysource="com.loit.apps.system.model.SysOrganizeModel" idField="organizeId" textField="name" pidField="parentOrganizeId"></loit:combotree></td>
				</tr>
				<tr>
					<loit:thd label="证书类型:" tagType="validatebox" name="certifiTypeId" required="false"></loit:thd>
					<loit:thd label="证书编码:" tagType="validatebox" name="certifiCode" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="密码:" tagType="validatebox" name="password" required="true"></loit:thd>
					<loit:thd label="允许修改密码:" tagType="validatebox" name="allowChangePassword" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="工作类型:" tagType="validatebox" name="workTypeId" required="false"></loit:thd>
					<loit:thd label="用户类型:" tagType="validatebox" name="userTypeId" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="检查标示:" tagType="validatebox" name="checkFlag" required="false"></loit:thd>
					<loit:thd label="教育类型:" tagType="validatebox" name="educationTypeId" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="家庭电话:" tagType="validatebox" name="homeTel" required="false"></loit:thd>
					<loit:thd label="办公电话:" tagType="validatebox" name="officeTel" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="移动电话:" tagType="validatebox" name="mobileTele" required="false"></loit:thd>
					<loit:thd label="地址:" tagType="validatebox" name="addrId" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="电子邮箱:" tagType="validatebox" name="email" required="true" validType="email"></loit:thd>
				</tr>
			</loit:form>
		</div>
		<div style="text-align: center; pading: 5px">
			<loit:linkbutton iconCls="icon-save" onclick="to_save(0)">保存</loit:linkbutton>
			<loit:linkbutton iconCls="icon-cancel" onclick="$('#addWindow').window('close');">取消</loit:linkbutton>
		</div>
	</loit:window>
	
	<loit:window id="editWindow" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editform" method="post" type="ft">
				<tr>
					<loit:thd tagType="validatebox" name="userId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="登陆名称:" tagType="validatebox" name="loginName" required="true"></loit:thd>
					<loit:thd label="用户名:" tagType="validatebox" name="name" required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="用户编码:" tagType="validatebox" name="code"></loit:thd>
					<th>所属部门:</th>
					<td><loit:combotree startId="1" required="true" id="organizeId" name="organizeId" panelWidth="150" querysource="com.loit.apps.system.model.SysOrganizeModel" idField="organizeId" textField="name" pidField="parentOrganizeId"></loit:combotree></td>
				</tr>
				<tr>
					<loit:thd label="证书类型:" tagType="validatebox" name="certifiTypeId" required="false"></loit:thd>
					<loit:thd label="证书编码:" tagType="validatebox" name="certifiCode" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="允许修改密码:" tagType="validatebox" name="allowChangePassword" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="工作类型:" tagType="validatebox" name="workTypeId" required="false"></loit:thd>
					<loit:thd label="用户类型:" tagType="validatebox" name="userTypeId" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="检查标示:" tagType="validatebox" name="checkFlag" required="false"></loit:thd>
					<loit:thd label="教育类型:" tagType="validatebox" name="educationTypeId" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="家庭电话:" tagType="validatebox" name="homeTel" required="false"></loit:thd>
					<loit:thd label="办公电话:" tagType="validatebox" name="officeTel" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="移动电话:" tagType="validatebox" name="mobileTele" required="false"></loit:thd>
					<loit:thd label="地址:" tagType="validatebox" name="addrId" required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="电子邮箱:" tagType="validatebox" name="email" required="true" validType="email"></loit:thd>
				</tr>
			</loit:form>
		</div>
		<div style="text-align: center; pading: 5px">
			<loit:linkbutton iconCls="icon-save" onclick="to_save(1)">保存</loit:linkbutton>
			<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindow').window('close');">取消</loit:linkbutton>
		</div>
	</loit:window>
	
	<loit:window id="editWindowSysUserFunc" title="Modal Window" modal="true" closed="true" style="width: 300px; padding: 10px;">
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
						<loit:linkbutton iconCls="icon-save" onclick="save_func()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindowSysUserFunc').window('close');">取消</loit:linkbutton>
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
						<loit:linkbutton iconCls="icon-save" onclick="save_role()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel" onclick="$('#editWindowSysUserRole').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
</body>
</html>
<script type="text/javascript">
	function query() {
		$('#dg').datagrid('query');
	}

	function clearForm() {
		$('#queryform').form('clear');
		//var data = $('#state').combobox('getData');
		//$("#state").combobox('select', data[0].id);
		$('#state').combobox('select', '');
	}

	//单击组织树，查询组织所属的用户
	$('#orgTree').tree({
		onClick : function(node) {
			$("#orgId").attr("value", node.id);
			query();
		}
	});

	//单击用户，查询用户拥有的角色和权限
	function onSCGridRow() {
		var row = $('#dg').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.userId;
		$("#hUserId").attr("value", idValue);
		//$('#userRoleTable').datagrid('query');
		$('#userRoleTable').datagrid('query');
		$('#userFuncTable').datagrid('query');
	}
	//双击用户，打开用户编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#addform").form('clear');
		var org = $('#orgTree').tree("getSelected");
		if (org) {
			var organizeId = org.id;
			var data = {
				"organizeId" : organizeId
			};
			$('#addform').form('load', data);
		}
		$('#addWindow').window('setTitle', " 增加");
		$('#addWindow').window('open');
	}

	//修改
	function to_edit() {
		var row = $('#dg').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.userId;
		sysUserManager.get(idValue, function(data) {
			$('#editform').form('load', data);
		});

		$('#editWindow').window('setTitle', " 修改");
		$('#editWindow').window('open');
	}

	//保存用户
	function to_save(flag) {
		if (flag == 0) {
			if ($("#addform").form('validate')) {
				var data = $("#addform").form("toJSON");
				$.messager.progress();
				$('#addWindow').window('close');
				sysUserManager.saveModel(data, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'保存成功！',
						timeout:3000,
						showType:'slide'
					});
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
				return;
			}
		} else {
			if ($("#editform").form('validate')) {
				var data = $("#editform").form("toJSON");
				$.messager.progress();
				$('#editWindow').window('close');
				sysUserManager.saveModel(data, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'保存成功！',
						timeout:3000,
						showType:'slide'
					});
					$('#dg').datagrid('reload');
				});
			} else {
				$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
				return;
			}
		}
	}

	//删除用户
	function to_del() {
		var row = $('#dg').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		     if (res) {
				$.messager.progress();
				sysUserManager.removeByPk(row.userId, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'删除成功！',
						timeout:2000,
						showType:'slide'
					});
					$('#dg').datagrid('reload');
				});
		     }
		});
	}

	//禁用用户
	function to_forbid() {
		var row = $('#dg').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要禁用的用户！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定禁用该用户？", function(res) {
		     if (res) {
				$.messager.progress();
				sysUserManager.forbidUser(row.userId, function(data) {
					$.messager.progress("close");
					$.messager.alert('提示信息', '操作成功！', 'info');
					$('#dg').datagrid('reload');
				});
		     }
		});
	}
	
	//启用用户
	function to_enable() {
		var row = $('#dg').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要启用的数据行！", "info");
			return;
		}
		$.messager.progress();
		sysUserManager.enableUser(row.userId, function(data) {
			$.messager.progress("close");
			$.messager.alert('提示信息', '操作成功！', 'info');
			$('#dg').datagrid('reload');
		});
	}
	
	//新增用户功能
	function add_func() {
		$("#editformSysUserFunc").form('clear');
		var user = $('#dg').datagrid("getSelected");
		if (user) {
			var userId = user.userId;
			var data = {
				"userId" : userId
			};
			$('#editWindowSysUserFunc').form('load', data);
		}
		$('#editWindowSysUserFunc').window('setTitle', "增加");
		$('#editWindowSysUserFunc').window('open');
	}
	//修改用户功能
	function edit_func() {
		var row = $('#userFuncTable').datagrid("getSelected");
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
	function save_func() {
		if ($("#editformSysUserFunc").form('validate')) {
			var data = $("#editformSysUserFunc").form("toJSON");
			$.messager.progress();
			$('#editWindowSysUserFunc').window('close');
			sysUserFuncManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
				$('#userFuncTable').datagrid('query');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除用户功能
	function del_func() {
		var row = $('#userFuncTable').datagrid("getSelected");
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
						timeout:2000,
						showType:'slide'
					});
					$('#userFuncTable').datagrid('query');
				});
		     }
		});
	}

	//新增用户角色
	function add_role() {
		$("#editformSysUserRole").form('clear');
		var user = $('#dg').datagrid("getSelected");
		if (user) {
			var userId = user.userId;
			var data = {
				"userId" : userId
			};
			$('#editWindowSysUserRole').form('load', data);
		}
		$('#editWindowSysUserRole').window('setTitle', "增加");
		$('#editWindowSysUserRole').window('open');
	}

	//修改用户角色
	function edit_role() {
		var row = $('#userRoleTable').datagrid("getSelected");
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
	function save_role() {
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
				$('#userRoleTable').datagrid('query');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除用户角色
	function del_role() {
		var row = $('#userRoleTable').datagrid("getSelected");
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
					$('#userRoleTable').datagrid('query');
				});
		     }
		});
	}
	//重置密码
	function resetPSD () {
		var row = $('#dg').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要重置密码的数据行！", "info");
			return;
		}
		$.messager.progress();
		sysUserManager.passwordReset(row.userId, function(data) {
			$.messager.progress("close");
			$.messager.alert("提示信息", "密码已成功重置为“888888”！", "info", function (data) {
				window.close();
			});
		});
	}
</script>
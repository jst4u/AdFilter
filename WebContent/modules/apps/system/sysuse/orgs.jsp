<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>组织管理</title>
</head>
<body>
	<loit:panel title="组织管理" fit="true" border="false" style="padding: 5px;">
		<loit:layout fit="true">
			<loit:layoutWest split="false">
				<loit:panel title="组织列表" fit="true" border="false" iconCls="tree-file" tools="#orgsTreeButton">
					<loit:treegrid id="tdg" onDblClickRow="onDCOrgs" border="false" animate="false" fit="true" rownumbers="true" querysource="com.loit.apps.system.model.SysOrganizeModel" idField="organizeId" treeField="name" pidField="parentOrganizeId">
						<loit:column field="organizeId" title="组织ID" hidden="true"></loit:column>
						<loit:column field="name" title="组织名称"></loit:column>
						<loit:column field="organizeTypeId" title="组织类型编码"></loit:column>
						<loit:column field="manage" codeType="userIdName" title="管理员"></loit:column>
						<loit:column field="contact" title="联系人"></loit:column>
						<loit:column field="createTime" title="创建时间"></loit:column>
						<loit:column field="modifyTime" title="修改时间"></loit:column>
						<loit:column field="recVer" title="版本号"></loit:column>
					</loit:treegrid>
				</loit:panel>
			</loit:layoutWest>
		</loit:layout>
	</loit:panel>
	<div id="orgsTreeButton">
		<loit:linkbutton tagClass="icon-add" onclick="addOrgs()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="editOrgs()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="removeOrgs()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-reload" onclick="reloadOrgs()"></loit:linkbutton>
	</div>
	<loit:window id="w" title="Modal Window" modal="true" closed="true" style="width: 500px; padding:10px;">
		<div>
			<loit:form id="ff" method="post" type="ft">
				<tr>
					<loit:thd label="组织名称:" tagType="validatebox" name="name" required="true"></loit:thd>
					<loit:thd label="地区编码:" tagType="validatebox" name="districtId"></loit:thd>
					<loit:thd tagType="" name="organizeId" type="hidden"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="组织类型编码:" tagType="validatebox" name="organizeTypeId" required="true"></loit:thd>
					<loit:thd label="上级编码:" tagType="combobox" codeType="organize" valueField="id" name="parentOrganizeId"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="管理员:" tagType="combobox" codeType="userIdName" valueField="id" name="manage"></loit:thd>
					<loit:thd label="联系人:" tagType="validatebox" name="contact"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="联系电话:" tagType="" name="contactTel"></loit:thd>
					<loit:thd label="传真:" tagType="validatebox" name="fax"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="邮箱:" tagType="validatebox" name="email"></loit:thd>
					<loit:thd label="组织排序:" tagType="validatebox" name="leve"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="EX1:" tagType="validatebox" name="ex1"></loit:thd>
					<loit:thd label="EX2:" tagType="validatebox" name="ex2"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="EX3:" tagType="validatebox" name="ex3"></loit:thd>
					<loit:thd label="EX4:" tagType="validatebox" name="ex4"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="EX5:" tagType="validatebox" name="ex5"></loit:thd>
					<loit:thd label="EX6:" tagType="validatebox" name="ex6"></loit:thd>
				</tr>
			</loit:form>
		</div>
		<div style="text-align: center; pading: 5px">
			<loit:linkbutton iconCls="icon-save" onclick="saveOrgs()">保存</loit:linkbutton>
			<loit:linkbutton iconCls="icon-cancel" onclick="$('#w').window('close');">取消</loit:linkbutton>
		</div>
	</loit:window>
</body>
</html>
<script language="javascript">
	function addOrgs() {
		$("#ff").form('clear');
		var orgs = $('#tdg').treegrid("getSelected");
		if (orgs) {
			var orgsId = orgs.organizeId;
			var data = {
				"parentOrganizeId" : orgsId
			};
			$('#ff').form('load', data);
		}

		$('#w').window('setTitle', " 增加功能菜单");
		$('#w').window('open');
	}
	function editOrgs() {
		var orgs = $('#tdg').treegrid("getSelected");
		if (!orgs) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var orgsId = orgs.organizeId;
		sysOrganizeManager.get(orgsId, function(data) {
			$('#ff').form('reset');
			$('#ff').form('load', data);

		});
		$('#w').window('setTitle', '修改功能菜单');
		$('#w').window('open');
	}
	
	function saveOrgs() {
		if ($("#ff").form('validate')) {
			var data = $("#ff").form("toJSON");
			$.messager.progress();
			$('#w').window('close');
			sysOrganizeManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.alert('提示信息', '保存成功！', 'info');
				reloadOrgs();
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	
	function removeOrgs() {
		var orgs = $('#tdg').treegrid("getSelected");
		if (!orgs) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		     if (res) {
		    	 $.messager.progress();
		    	 sysOrganizeManager.removeByPk(orgs.organizeId, function(data) {
		 			$.messager.alert('提示信息', '删除成功！', 'info');
		 			reloadOrgs();
		 		});
		     }
		 			$.messager.progress("close");
		});
	}

	function reloadOrgs() {
		$('#tdg').treegrid('reload');
	}

	function onDCOrgs() {
		editOrgs();
	}
</script>
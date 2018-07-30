<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>系统代码管理</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="查询条件" style="height:100px">
			<div  style="padding:5px">
				<loit:form id="queryformSysCodeType" method="post" type="ft">
						<tr>
			    			<loit:thd label="编码类型代号:" tagType="validatebox" name="codeTypeCode" operator="likeAnywhere"></loit:thd>
							<loit:thd label="代码类型分类:" tagType="validatebox" name="codeTypeType" operator="likeAnywhere"></loit:thd>
						</tr>
						<tr >
			    			<loit:thd tcolspan="6" tstyle="text-align: center">
			    				<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>&nbsp&nbsp
			    				<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
			    			</loit:thd>
			    		</tr>
					</loit:form>
			</div>
		</loit:layoutNorth>
		<loit:layoutCenter title="查询结果">
			<loit:layout fit="true">
				<loit:layoutWest title="代码类型" style="width: 700px" collapsible="false" tools="#codeTypeButton">
					<loit:datagrid id="dgSysCodeType" queryform="#queryformSysCodeType" querysource="com.loit.apps.system.model.SysCodeTypeModel" rownumbers="true" singleSelect="true"
					pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow" onClickRow="queryCode" sortName="codeTypeType" sortOrder="desc"  idField="codeTypeId">
						<loit:column field="codeTypeId" title="代码类型ID" hidden="true"></loit:column>
						<loit:column field="codeTypeName" sortable="true" title="代码类型名称"></loit:column>
						<loit:column field="codeTypeCode" sortable="true" title="编码类型代号"></loit:column>
						<loit:column field="codeTypeDesc" sortable="true" title="代码类型描述"></loit:column>
						<loit:column field="codeTypeType" sortable="true" title="代码类型分类"></loit:column>
						<loit:column field="remarks" sortable="true" title="备注说明"></loit:column>
						<loit:column field="dynColValue" sortable="true" title="显示的值"></loit:column>
						<loit:column field="dynColName" sortable="true" title="代码名称"></loit:column>
						<loit:column field="dynTablename" sortable="true" title="查询的表名(Model)"></loit:column>
					</loit:datagrid>
				</loit:layoutWest>
				<loit:layoutCenter  title="代码" tools="#codeButton">
					<form id="hiddenform" method="post">
						<input type="hidden" id="hCodeTypeId" name="codeTypeId" value="codeTypeId">
					</form>
					<loit:datagrid id="dgSysCode" queryform="#hiddenform" querysource="com.loit.apps.system.model.SysCodeModel" rownumbers="true" singleSelect="true"
					 fit="true" border="false" onDblClickRow="onDblClick" tools="#codeButton">
						<loit:column field="codeId" title="代码ID" hidden="true"></loit:column>
						<loit:column field="codeName" title="代码名称"></loit:column>
						<loit:column field="codeValue" title="显示的值"></loit:column>
						<loit:column field="codeDesc" title="代码描述"></loit:column>
						<loit:column field="codeOrder" title="代码排序"></loit:column>
						<loit:column field="remarks" title="备注说明"></loit:column>
					</loit:datagrid>
				</loit:layoutCenter>
			</loit:layout>
		</loit:layoutCenter>
	</loit:layout>
	<div id="codeTypeButton">
		<loit:linkbutton tagClass="icon-add" onclick="to_add()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="to_edit()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="to_del()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-reload" onclick="query()"></loit:linkbutton>
	</div>
	<div id="codeButton">
		<loit:linkbutton tagClass="icon-add" onclick="add_code()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="edit_code()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="del_code()"></loit:linkbutton>
	</div>
	<loit:window id="editWindowSysCodeType" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformSysCodeType" method="post" type="ft">
				<tr>
					<loit:thd label="代码类型ID:" tagType="validatebox" name="codeTypeId"></loit:thd>
					<loit:thd label="代码类型名称:" tagType="validatebox" name="codeTypeName"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="编码类型代号:" tagType="validatebox" name="codeTypeCode"
						required="true"></loit:thd>
					<loit:thd label="代码类型描述:" tagType="validatebox" name="codeTypeDesc"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="代码类型分类:" tagType="combobox" name="codeTypeType"
						codeType="codeType" valueField="id" panelHeight="auto"
						required="true"></loit:thd>
					<loit:thd label="备注说明:" tagType="validatebox" name="remarks"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="显示的值:" tagType="validatebox" name="dynColValue"
						required="true"></loit:thd>
					<loit:thd label="代码名称:" tagType="" name="dynColName"
						required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="查询的表名(Model):" tagType="validatebox"
						name="dynTablename" required="true"></loit:thd>
					<loit:thd label="代码显示条件:" tagType="validatebox" name="dynWhere"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="系统自带代码:" tagType="validatebox" name="codeFrom"
						required="false"></loit:thd>
					<loit:thd label="creator:" tagType="validatebox" name="creator"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="createTime:" tagType="validatebox"
						name="createTime" required="false"></loit:thd>
					<loit:thd label="modifier:" tagType="validatebox" name="modifier"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="modifyTime:" tagType="validatebox"
						name="modifyTime" required="false"></loit:thd>
					<loit:thd label="recVer:" tagType="validatebox" name="recVer"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd tcolspan="4" tstyle="text-align: center">
						<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel"
							onclick="$('#editWindowSysCodeType').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
	<loit:window id="editWindowSysCode" title="Modal Window" modal="true"
		closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformSysCode" method="post" type="ft">
				<tr>
					<loit:thd label="代码ID:" tagType="validatebox" name="codeId"></loit:thd>
					<loit:thd label="代码类型代号:" tagType="combobox" codeType="code" valueField="id" name="codeTypeId"
						required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="代码名称:" tagType="validatebox" name="codeName"
						required="true"></loit:thd>
					<loit:thd label="显示的值:" tagType="validatebox" name="codeValue"
						required="true"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="代码描述:" tagType="validatebox" name="codeDesc"
						required="false"></loit:thd>
					<loit:thd label="代码排序:" tagType="validatebox" name="codeOrder"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注说明:" tagType="validatebox" name="remarks"
						required="false"></loit:thd>
					<loit:thd label="creator:" tagType="validatebox" name="creator"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="createTime:" tagType="validatebox"
						name="createTime" required="false"></loit:thd>
					<loit:thd label="modifier:" tagType="validatebox" name="modifier"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="modifyTime:" tagType="validatebox"
						name="modifyTime" required="false"></loit:thd>
					<loit:thd label="recVer:" tagType="validatebox" name="recVer"
						required="false"></loit:thd>
				</tr>
				<tr>
					<loit:thd tcolspan="4" tstyle="text-align: center">
						<loit:linkbutton iconCls="icon-save" onclick="save_code()">保存</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel"
							onclick="$('#editWindowSysCode').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgSysCodeType').datagrid('query');
	}
	function clearForm() {
		$('#queryformSysCodeType').form('clear');
	}
	//双击打开代码类型编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增代码类型
	function to_add() {
		$("#editformSysCodeType").form('clear');
		$('#editWindowSysCodeType').window('setTitle', "增加");
		$('#editWindowSysCodeType').window('open');
	}

	//修改代码类型
	function to_edit() {
		var row = $('#dgSysCodeType').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.codeTypeId;
		sysCodeTypeManager.get(idValue, function(data) {
			$('#editformSysCodeType').form('load', data);
		});

		$('#editWindowSysCodeType').window('setTitle', "修改");
		$('#editWindowSysCodeType').window('open');
	}

	//保存代码类型
	function to_save() {
		if ($("#editformSysCodeType").form('validate')) {
			var data = $("#editformSysCodeType").form("toJSON");
			$.messager.progress();
			$('#editWindowSysCodeType').window('close');
			sysCodeTypeManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
				$('#dgSysCodeType').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除代码类型
	function to_del() {
		var row = $('#dgSysCodeType').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				sysCodeTypeManager.removeByPk(row.codeTypeId, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'删除成功！',
						timeout:2000,
						showType:'slide'
					});
					$('#dgSysCodeType').datagrid('reload');
				});
			}
		});
	}
	//单击查询代码类型对应的代码
	function queryCode() {
		var row = $('#dgSysCodeType').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.codeTypeId;
		$("#hCodeTypeId").attr("value", idValue);
		$('#dgSysCode').datagrid('query');
	}

	//双击打开代码编辑窗口
	function onDblClick() {
		edit_code();
	}
	//新增代码
	function add_code() {
		$("#editformSysCode").form('clear');
		var code = $('#dgSysCodeType').datagrid("getSelected");
		if (code) {
			var codeTypeId = code.codeTypeId;
			var data = {
				"codeTypeId" : codeTypeId
			};
			$('#editformSysCode').form('load', data);
		}
		$('#editWindowSysCode').window('setTitle', "增加");
		$('#editWindowSysCode').window('open');
	}

	//修改代码
	function edit_code() {
		var row = $('#dgSysCode').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.codeId;
		sysCodeManager.get(idValue, function(data) {
			$('#editformSysCode').form('load', data);
		});

		$('#editWindowSysCode').window('setTitle', "修改");
		$('#editWindowSysCode').window('open');
	}

	//保存代码
	function save_code() {
		if ($("#editformSysCode").form('validate')) {
			var data = $("#editformSysCode").form("toJSON");
			$.messager.progress();
			$('#editWindowSysCode').window('close');
			sysCodeManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title:'系统消息',
					msg:'保存成功！',
					timeout:2000,
					showType:'slide'
				});
				$('#dgSysCode').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除代码
	function del_code() {
		var row = $('#dgSysCode').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				sysCodeManager.removeByPk(row.codeId, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title:'系统消息',
						msg:'删除成功！',
						timeout:2000,
						showType:'slide'
					});
					$('#dgSysCode').datagrid('reload');
				});
			}
		});
	}
</script>
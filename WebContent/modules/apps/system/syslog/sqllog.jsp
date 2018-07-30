<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>数据库操作日志管理</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="数据库操作日志管理" style="height:180px">
			<div style="padding:5px">
				<loit:form id="queryformSysSqlLog" method="post" type="ft">
					<tr>
	    				<loit:thd label="sqlLogId:" tagType="validatebox" name="sqlLogId" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="userId:" tagType="validatebox" name="userId" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="sqlStatement:" tagType="validatebox" name="sqlStatement" operator="likeAnywhere"></loit:thd>
					</tr>
					<tr>
	    				<loit:thd label="logTime:" tagType="validatebox" name="logTime" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="timeUsed:" tagType="validatebox" name="timeUsed" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="serviceAccessIndex:" tagType="validatebox" name="serviceAccessIndex" operator="likeAnywhere"></loit:thd>
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
			<loit:datagrid id="dgSysSqlLog" queryform="#queryformSysSqlLog" querysource="com.loit.apps.system.model.SysSqlLogModel" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow" 
			toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){to_add()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){to_edit()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){to_del()}}]">
				<loit:column field="sqlLogId" title="sqlLogId"></loit:column>
				<loit:column field="userId" title="userId"></loit:column>
				<loit:column field="sqlStatement" title="sqlStatement"></loit:column>
				<loit:column field="logTime" title="logTime"></loit:column>
				<loit:column field="timeUsed" title="timeUsed"></loit:column>
				<loit:column field="serviceAccessIndex" title="serviceAccessIndex"></loit:column>
			</loit:datagrid>
		</loit:layoutCenter>
		<loit:layoutSouth title="结果说明" style="height:100px">
		</loit:layoutSouth>
	</loit:layout>
	<loit:window id="editWindowSysSqlLog" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformSysSqlLog" method="post" type="ft">
				<tr>
					<loit:thd label="sqlLogId:" tagType="validatebox" name="sqlLogId"></loit:thd>
					<loit:thd label="userId:" tagType="validatebox" name="userId"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="sqlStatement:" tagType="validatebox" name="sqlStatement"></loit:thd>
					<loit:thd label="logTime:" tagType="validatebox" name="logTime"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="timeUsed:" tagType="validatebox" name="timeUsed"></loit:thd>
					<loit:thd label="serviceAccessIndex:" tagType="validatebox" name="serviceAccessIndex"></loit:thd>
				</tr>
				<tr >
	    			<loit:thd tcolspan="4" tstyle="text-align: center">
				    	<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindowSysSqlLog').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgSysSqlLog').datagrid('query');
	}
	function clearForm() {
		$('#queryformSysSqlLog').form('clear');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editformSysSqlLog").form('clear');
		$('#editWindowSysSqlLog').window('setTitle', "增加");
		$('#editWindowSysSqlLog').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dgSysSqlLog').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.sqlLogId;
		sysSqlLogManager.get(idValue, function(data) {
			$('#editformSysSqlLog').form('load', data);
		});
		$('#editWindowSysSqlLog').window('setTitle', "修改");
		$('#editWindowSysSqlLog').window('open');
	}
	//保存
	function to_save() {
		if ($("#editformSysSqlLog").form('validate')) {
			var data = $("#editformSysSqlLog").form("toJSON");
			$.messager.progress();
			$('#editWindowSysSqlLog').window('close');
			sysSqlLogManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.alert('提示信息', '保存成功！', 'info');
				$('#dgSysSqlLog').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dgSysSqlLog').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				sysSqlLogManager.removeByPk(row.sqlLogId, function(data) {
					$.messager.progress("close");
					$.messager.alert('提示信息', '删除成功！', 'info');
					$('#dgSysSqlLog').datagrid('reload');
				});
		    }
		});
	}
</script>
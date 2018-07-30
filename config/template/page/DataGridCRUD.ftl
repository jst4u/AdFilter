<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>自动生成 - ${label} -</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="查询条件" style="height:${columns?size*30}px">
			<div style="padding:5px">
				<loit:form id="queryform${className}" method="post" type="ft">
					<#list columns as column>
						<#if column_index % 3 == 0>
					<tr>
						</#if>
	    				<loit:thd label="${column.label}:" tagType="validatebox" name="${column.fieldName}" operator="likeAnywhere"></loit:thd>
						<#if column_index % 3 == 2>
					</tr>
						</#if>
					</#list>
						<#if columns?size % 3 != 0>
					</tr>
						</#if>
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
			<loit:datagrid id="dg${className}" queryform="#queryform${className}" querysource="${entityName}" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow" 
			toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){to_add()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){to_edit()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){to_del()}}]">
				<#list columns as column>
				<loit:column field="${column.fieldName}" title="${column.label}"></loit:column>
				</#list>
			</loit:datagrid>
		</loit:layoutCenter>
		<loit:layoutSouth title="结果说明" style="height:100px">
		</loit:layoutSouth>
	</loit:layout>
	<loit:window id="editWindow${className}" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editform${className}" method="post" type="ft">
				<#list columns as column>
					<#if column_index % 2 == 0>
				<tr>
					</#if>
					<loit:thd label="${column.label}:" tagType="validatebox" name="${column.fieldName}"></loit:thd>
					<#if column_index % 2 == 1>
				</tr>
					</#if>
				</#list>
					<#if columns?size % 2 != 0>
				</tr>
					</#if>
				<tr >
	    			<loit:thd tcolspan="4" tstyle="text-align: center">
				    	<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindow${className}').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dg${className}').datagrid('query');
	}
	function clearForm() {
		$('#queryform${className}').form('clear');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editform${className}").form('clear');
		$('#editWindow${className}').window('setTitle', "增加");
		$('#editWindow${className}').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dg${className}').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.${pkFieldName};
		${managerName}.get(idValue, function(data) {
			$('#editform${className}').form('load', data);
		});
		$('#editWindow${className}').window('setTitle', "修改");
		$('#editWindow${className}').window('open');
	}
	//保存
	function to_save() {
		if ($("#editform${className}").form('validate')) {
			var data = $("#editform${className}").form("toJSON");
			$.messager.progress();
			$('#editWindow${className}').window('close');
			${managerName}.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title : '系统消息',
					msg : '保存成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dg${className}').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dg${className}').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				${managerName}.removeByPk(row.${pkFieldName}, function(data) {
					$.messager.progress("close");
					$.messager.show({
								title : '系统消息',
								msg : '删除成功！',
								timeout : 2000,
								showType : 'slide'
							});
					$('#dg${className}').datagrid('reload');
				});
		    }
		});
	}
</script>
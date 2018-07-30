<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>服务日志管理</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="服务日志管理" style="height:140px">
			<div style="padding:5px">
				<loit:form id="queryformSysLog" method="post" type="ft">
					<tr>
	    				<loit:thd label="操作用户:" tagType="combobox" id="operUserId" name="operUserId" operator="likeAnywhere" codeType="userIdName" valueField="id" codePlease="-----请选择-----"></loit:thd>
	    				<loit:thd label="操作对象:" tagType="validatebox" name="operOject" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="操作动作:" tagType="validatebox" name="operAction" operator="likeAnywhere"></loit:thd>
	    				</tr>
			    		<tr>
	    				<loit:thd label="开始时间:" tagType="datetimebox" name="logDate" showSeconds="true" operator=">"></loit:thd>
						<loit:thd label="结束时间:" tagType="datetimebox" name="logDate" showSeconds="true" operator="<"></loit:thd>
					</tr>
					<tr >
		    			<loit:thd tcolspan="8" tstyle="text-align: center">
		    				<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>&nbsp&nbsp
		    				<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
		    			</loit:thd>
		    		</tr>
				</loit:form>
			</div>
		</loit:layoutNorth>
		<loit:layoutCenter title="查询结果">
			<loit:datagrid id="dgSysLog" queryform="#queryformSysLog" querysource="com.loit.apps.system.model.SysLogModel" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" 
			>
				<loit:column field="logId" title="logId" hidden="true"></loit:column>
				<loit:column field="operUserId" title="操作用户" codeType="userIdName" ></loit:column>
				<loit:column field="logDate" title="操作时间"></loit:column>
				<loit:column field="operOject" title="操作对象"></loit:column>
				<loit:column field="operAction" title="操作动作"></loit:column>
				<loit:column field="logDesc" title="描述"></loit:column>
				<loit:column field="result" title="结果"></loit:column>
				<loit:column field="remarks" title="备注"></loit:column>
			</loit:datagrid>
		</loit:layoutCenter>
	</loit:layout>
	<loit:window id="editWindowSysLog" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformSysLog" method="post" type="ft">
				<tr>
					<loit:thd label="logId:" tagType="validatebox" name="logId"></loit:thd>
					<loit:thd label="operUserId:" tagType="validatebox" name="operUserId"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="logDate:" tagType="validatebox" name="logDate"></loit:thd>
					<loit:thd label="operOject:" tagType="validatebox" name="operOject"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="operAction:" tagType="validatebox" name="operAction"></loit:thd>
					<loit:thd label="modiRecords:" tagType="validatebox" name="modiRecords"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="logDesc:" tagType="validatebox" name="logDesc"></loit:thd>
					<loit:thd label="result:" tagType="validatebox" name="result"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="state:" tagType="validatebox" name="state"></loit:thd>
					<loit:thd label="remarks:" tagType="validatebox" name="remarks"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="creator:" tagType="validatebox" name="creator"></loit:thd>
					<loit:thd label="createTime:" tagType="validatebox" name="createTime"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="modifier:" tagType="validatebox" name="modifier"></loit:thd>
					<loit:thd label="modifyTime:" tagType="validatebox" name="modifyTime"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="recVer:" tagType="validatebox" name="recVer"></loit:thd>
				</tr>
				<tr >
	    			<loit:thd tcolspan="4" tstyle="text-align: center">
				    	<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindowSysLog').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgSysLog').datagrid('query');
	}
	function clearForm() {
		$('#queryformSysLog').form('clear');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editformSysLog").form('clear');
		$('#editWindowSysLog').window('setTitle', "增加");
		$('#editWindowSysLog').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dgSysLog').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.logId;
		sysLogManager.get(idValue, function(data) {
			$('#editformSysLog').form('load', data);
		});
		$('#editWindowSysLog').window('setTitle', "修改");
		$('#editWindowSysLog').window('open');
	}
	//保存
	function to_save() {
		if ($("#editformSysLog").form('validate')) {
			var data = $("#editformSysLog").form("toJSON");
			$.messager.progress();
			$('#editWindowSysLog').window('close');
			sysLogManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.alert('提示信息', '保存成功！', 'info');
				$('#dgSysLog').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dgSysLog').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				sysLogManager.removeByPk(row.logId, function(data) {
					$.messager.progress("close");
					$.messager.alert('提示信息', '删除成功！', 'info');
					$('#dgSysLog').datagrid('reload');
				});
		    }
		});
	}
</script>
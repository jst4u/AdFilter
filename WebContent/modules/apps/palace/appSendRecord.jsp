<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>发送记录</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="查询条件" style="height:75px">
			<div style="padding:5px; float:left;">
				<loit:form id="queryformAppSendRecord" method="post" type="ft">
					<tr>
	    				<loit:thd label="发送类型:" tagType="validatebox" name="sendType" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="设备编号:" tagType="validatebox" name="equCode" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="发送时间:" tagType="validatebox" name="sendTime" operator="likeAnywhere"></loit:thd>
		    			<loit:thd tcolspan="6" tstyle="text-align: center">
		    				<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>&nbsp&nbsp
		    				<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
		    			</loit:thd>
		    		</tr>
				</loit:form>
			</div>
		</loit:layoutNorth>
		<loit:layoutCenter title="查询结果">
			<loit:datagrid id="dgAppSendRecord" queryform="#queryformAppSendRecord" querysource="com.loit.apps.project.palace.model.AppSendRecordModel" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow"  sortName="sendTime" sortOrder="desc"
			toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){to_add()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){to_edit()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){to_del()}}]">
				<loit:column field="id" title="ID" hidden="true"></loit:column>
				<loit:column field="sendType" title="发送类型" sortable="true"></loit:column>
				<loit:column field="equCode" title="设备编号" sortable="true"></loit:column>
				<loit:column field="data1" title="数据1"></loit:column>
				<loit:column field="data2" title="数据2"></loit:column>
				<loit:column field="data3" title="数据3"></loit:column>
				<loit:column field="sendTime" title="发送时间" sortable="true"></loit:column>
				<loit:column field="res" title="发送结果"></loit:column>
				<loit:column field="ext1" title="备注1"></loit:column>
				<loit:column field="ext2" title="备注2"></loit:column>
				<loit:column field="creator" title="creator"></loit:column>
				<loit:column field="createTime" title="createTime"></loit:column>
				<loit:column field="modifier" title="modifier"></loit:column>
				<loit:column field="modifyTime" title="modifyTime"></loit:column>
				<loit:column field="recVer" title="recVer"></loit:column>
			</loit:datagrid>
		</loit:layoutCenter>
	</loit:layout>
	<loit:window id="editWindowAppSendRecord" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformAppSendRecord" method="post" type="ft">
				<tr>
					<input type="hidden" name="id"/>
					<loit:thd label="发送类型:" tagType="validatebox" name="sendType"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="设备编号:" tagType="validatebox" name="equCode"></loit:thd>
					<loit:thd label="数据1:" tagType="validatebox" name="data1"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="数据2:" tagType="validatebox" name="data2"></loit:thd>
					<loit:thd label="数据3:" tagType="validatebox" name="data3"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="发送时间:" tagType="validatebox" name="sendTime"></loit:thd>
					<loit:thd label="发送结果:" tagType="validatebox" name="res"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注1:" tagType="validatebox" name="ext1"></loit:thd>
					<loit:thd label="备注2:" tagType="validatebox" name="ext2"></loit:thd>
				</tr>
				<tr >
	    			<loit:thd tcolspan="4" tstyle="text-align: center">
				    	<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindowAppSendRecord').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgAppSendRecord').datagrid('query');
	}
	function clearForm() {
		$('#queryformAppSendRecord').form('clear');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editformAppSendRecord").form('clear');
		$('#editWindowAppSendRecord').window('setTitle', "增加");
		$('#editWindowAppSendRecord').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dgAppSendRecord').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.id;
		appSendRecordManager.get(idValue, function(data) {
			$('#editformAppSendRecord').form('load', data);
		});
		$('#editWindowAppSendRecord').window('setTitle', "修改");
		$('#editWindowAppSendRecord').window('open');
	}
	//保存
	function to_save() {
		if ($("#editformAppSendRecord").form('validate')) {
			var data = $("#editformAppSendRecord").form("toJSON");
			$.messager.progress();
			$('#editWindowAppSendRecord').window('close');
			appSendRecordManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title : '系统消息',
					msg : '保存成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dgAppSendRecord').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dgAppSendRecord').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				appSendRecordManager.removeByPk(row.id, function(data) {
					$.messager.progress("close");
					$.messager.show({
								title : '系统消息',
								msg : '删除成功！',
								timeout : 2000,
								showType : 'slide'
							});
					$('#dgAppSendRecord').datagrid('reload');
				});
		    }
		});
	}
</script>
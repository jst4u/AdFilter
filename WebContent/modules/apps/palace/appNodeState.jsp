<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>- AppNodeState -</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="查询条件" style="height:75px">
			<div style="padding:5px; float:left;">
				<loit:form id="queryformAppNodeState" method="post" type="ft">
					<tr>
	    				<loit:thd label="节点ID:" tagType="validatebox" name="nodeId" operator="likeAnywhere"></loit:thd>
	    				<loit:thd label="系统时间:" tagType="validatebox" name="systemTime" operator="likeAnywhere"></loit:thd>
		    			<loit:thd tcolspan="6" tstyle="text-align: center">
		    				<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>&nbsp&nbsp
		    				<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
		    			</loit:thd>
		    		</tr>
				</loit:form>
			</div>
		</loit:layoutNorth>
		<loit:layoutCenter title="查询结果">
			<loit:datagrid id="dgAppNodeState" queryform="#queryformAppNodeState" querysource="com.loit.apps.project.palace.model.AppNodeStateModel" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow" sortName="sendTime" sortOrder="desc"
			toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){to_add()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){to_edit()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){to_del()}}]">
				<loit:column field="id" title="ID" hidden="true"></loit:column>
				<loit:column field="nodeId" title="节点ID" sortable="true"></loit:column>
				<loit:column field="currentValue" title="电流值"></loit:column>
				<loit:column field="voltageValue" title="电压值"></loit:column>
				<loit:column field="sendTime" title="发送时间" sortable="true"></loit:column>
				<loit:column field="isRepeat" title="是否重发"></loit:column>
				<loit:column field="repeatTimes" title="重发次数"></loit:column>
				<loit:column field="repeatTime" title="重发时间"></loit:column>
				<loit:column field="systemTime" title="系统时间" sortable="true"></loit:column>
				<loit:column field="remarks1" title="出厂编号"></loit:column>
				<loit:column field="remarks2" title="备注2"></loit:column>
				<loit:column field="remarks3" title="备注3"></loit:column>
				<loit:column field="recVer" title="版本号"></loit:column>
			</loit:datagrid>
		</loit:layoutCenter>
	</loit:layout>
	<loit:window id="editWindowAppNodeState" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformAppNodeState" method="post" type="ft">
				<tr>
					<loit:thd label="节点ID:" tagType="validatebox" name="nodeId"></loit:thd>
					<loit:thd label="电流值:" tagType="validatebox" name="currentValue"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="电压值:" tagType="validatebox" name="voltageValue"></loit:thd>
					<loit:thd label="发送时间:" tagType="validatebox" name="sendTime"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="是否重发:" tagType="validatebox" name="isRepeat"></loit:thd>
					<loit:thd label="重发次数:" tagType="validatebox" name="repeatTimes"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="重发时间:" tagType="validatebox" name="repeatTime"></loit:thd>
					<loit:thd label="系统时间:" tagType="validatebox" name="systemTime"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注1:" tagType="validatebox" name="remarks1"></loit:thd>
					<loit:thd label="备注2:" tagType="validatebox" name="remarks2"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注3:" tagType="validatebox" name="remarks3"></loit:thd>
					<input type="hidden" name="id"/>
				</tr>
				<tr >
	    			<loit:thd tcolspan="4" tstyle="text-align: center">
				    	<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindowAppNodeState').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgAppNodeState').datagrid('query');
	}
	function clearForm() {
		$('#queryformAppNodeState').form('clear');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editformAppNodeState").form('clear');
		$('#editWindowAppNodeState').window('setTitle', "增加");
		$('#editWindowAppNodeState').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dgAppNodeState').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.id;
		appNodeStateManager.get(idValue, function(data) {
			$('#editformAppNodeState').form('load', data);
		});
		$('#editWindowAppNodeState').window('setTitle', "修改");
		$('#editWindowAppNodeState').window('open');
	}
	//保存
	function to_save() {
		if ($("#editformAppNodeState").form('validate')) {
			var data = $("#editformAppNodeState").form("toJSON");
			$.messager.progress();
			$('#editWindowAppNodeState').window('close');
			appNodeStateManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title : '系统消息',
					msg : '保存成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dgAppNodeState').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dgAppNodeState').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				appNodeStateManager.removeByPk(row.id, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title : '系统消息',
						msg : '删除成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#dgAppNodeState').datagrid('reload');
				});
		    }
		});
	}
</script>
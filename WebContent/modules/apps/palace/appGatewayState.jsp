<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>- AppGatewayState -</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="查询条件" style="height:75px">
			<div style="padding:5px; float:left;">
				<loit:form id="queryformAppGatewayState" method="post" type="ft">
					<tr>
	    				<loit:thd label="网关ID:" tagType="validatebox" name="gatewayId" operator="likeAnywhere"></loit:thd>
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
			<loit:datagrid id="dgAppGatewayState" queryform="#queryformAppGatewayState" querysource="com.loit.apps.project.palace.model.AppGatewayStateModel" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow" sortName="sendTime" sortOrder="desc"
			toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){to_add()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){to_edit()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){to_del()}}]">
				<loit:column field="id" title="ID" hidden="true"></loit:column>
				<loit:column field="gatewayId" title="网关ID" sortable="true"></loit:column>
				<loit:column field="rssiSignalIntensity" title="RSSI信号强度"></loit:column>
				<loit:column field="sendTime" title="发送时间" sortable="true"></loit:column>
				<loit:column field="systemTime" title="系统时间" sortable="true"></loit:column>
				<loit:column field="remarks1" title="出厂编号"></loit:column>
				<loit:column field="remarks2" title="备注2"></loit:column>
				<loit:column field="remarks3" title="备注3"></loit:column>
				<loit:column field="recVer" title="版本号"></loit:column>
			</loit:datagrid>
		</loit:layoutCenter>
	</loit:layout>
	<loit:window id="editWindowAppGatewayState" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformAppGatewayState" method="post" type="ft">
				<tr>
					<loit:thd label="网关ID:" tagType="validatebox" name="gatewayId"></loit:thd>
					<loit:thd label="RSSI信号强度:" tagType="validatebox" name="rssiSignalIntensity"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="发送时间:" tagType="validatebox" name="sendTime"></loit:thd>
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
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindowAppGatewayState').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgAppGatewayState').datagrid('query');
	}
	function clearForm() {
		$('#queryformAppGatewayState').form('clear');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editformAppGatewayState").form('clear');
		$('#editWindowAppGatewayState').window('setTitle', "增加");
		$('#editWindowAppGatewayState').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dgAppGatewayState').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.id;
		appGatewayStateManager.get(idValue, function(data) {
			$('#editformAppGatewayState').form('load', data);
		});
		$('#editWindowAppGatewayState').window('setTitle', "修改");
		$('#editWindowAppGatewayState').window('open');
	}
	//保存
	function to_save() {
		if ($("#editformAppGatewayState").form('validate')) {
			var data = $("#editformAppGatewayState").form("toJSON");
			$.messager.progress();
			$('#editWindowAppGatewayState').window('close');
			appGatewayStateManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title : '系统消息',
					msg : '保存成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dgAppGatewayState').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dgAppGatewayState').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				appGatewayStateManager.removeByPk(row.id, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title : '系统消息',
						msg : '删除成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#dgAppGatewayState').datagrid('reload');
				});
		    }
		});
	}
</script>
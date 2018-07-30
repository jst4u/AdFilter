<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<title>- AppRelayBase -</title>
</head>
<loit:body>
	<loit:layout fit="true">
		<loit:layoutNorth title="查询条件" style="height:75px">
			<div style="padding:5px; float:left;">
				<loit:form id="queryformAppRelayBase" method="post" type="ft">
					<tr>
	    				<loit:thd label="中继ID:" tagType="validatebox" id="relayId" name="relayId" operator="likeAnywhere" value=" "></loit:thd>
		    			<loit:thd tcolspan="6" tstyle="text-align: center">
		    				<loit:linkbutton id="searchButton" iconCls="icon-search" onclick="query()">查询</loit:linkbutton>&nbsp&nbsp
		    				<loit:linkbutton id="clearButton" iconCls="icon-redo" onclick="clearForm()">清空</loit:linkbutton>
		    			</loit:thd>
		    		</tr>
				</loit:form>
			</div>
		</loit:layoutNorth>
		<loit:layoutCenter title="查询结果">
			<loit:datagrid id="dgAppRelayBase" queryform="#queryformAppRelayBase" querysource="sql:select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 75, '正常', '异常')) AS state, b.sttime, a.* 
							from app_relay_base AS a LEFT JOIN (
							select RELAY_ID, MAX(SEND_TIME) AS sttime from app_relay_state
							group by RELAY_ID) AS b 
							ON a.RELAY_ID = b.RELAY_ID where [A.relay_id like '%{relayId}%']" rownumbers="true" singleSelect="true"
			pagination="true" fit="true" border="false" onDblClickRow="onDCGridRow" sortName="RELAY_ID" sortOrder="asc" 
			toolbar="[{text:'增加',iconCls:'icon-add',handler:function(){to_add()}},'-',{text:'修改',iconCls:'icon-edit',handler:function(){to_edit()}},'-',{text:'删除',iconCls:'icon-remove',handler:function(){to_del()}}]">
				<loit:column field="ID" title="ID" hidden="true"></loit:column>
				<loit:column field="RELAY_ID" title="中继ID" sortable="true"></loit:column>
				<loit:column field="STATE" title="中继状态" sortable="true"></loit:column>
				<loit:column field="INSTALLATION_POSITION" title="安装位置"></loit:column>
				<loit:column field="INSTALLATION_PERSONNEL" title="安装人员"></loit:column>
				<loit:column field="INSTALLATION_TIME" title="安装时间"></loit:column>
				<loit:column field="SYSTEM_TIME" title="系统时间"></loit:column>
				<loit:column field="REMARKS1" title="出厂编号"></loit:column>
				<loit:column field="REMARKS2" title="备注2"></loit:column>
				<loit:column field="REMARKS3" title="备注3"></loit:column>
				<loit:column field="REC_VER" title="版本号"></loit:column>
			</loit:datagrid>
		</loit:layoutCenter>
	</loit:layout>
	<loit:window id="editWindowAppRelayBase" title="Modal Window" modal="true" closed="true" style="width: 500px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="editformAppRelayBase" method="post" type="ft">
				<tr>
					<loit:thd label="中继ID:" tagType="validatebox" name="relayId" required="true"></loit:thd>
					<loit:thd label="中继状态:" tagType="validatebox" name="relayState"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="安装位置:" tagType="validatebox" name="installationPosition"></loit:thd>
					<loit:thd label="安装人员:" tagType="validatebox" name="installationPersonnel"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="安装时间:" tagType="datetimebox" name="installationTime"></loit:thd>
					<loit:thd label="系统时间:" tagType="datetimebox" name="systemTime"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注1:" tagType="validatebox" name="remarks1"></loit:thd>
					<loit:thd label="备注2:" tagType="validatebox" name="remarks2"></loit:thd>
				</tr>
				<tr>
					<loit:thd label="备注3:" tagType="validatebox" name="remarks3"></loit:thd>
					<input type="hidden" name="id"/>
				</tr>
				<tr>
	    			<loit:thd tcolspan="4" tstyle="text-align: center">
				    	<loit:linkbutton iconCls="icon-save" onclick="to_save()">保存</loit:linkbutton>
				    	<loit:linkbutton iconCls="icon-cancel"  onclick="$('#editWindowAppRelayBase').window('close');">取消</loit:linkbutton>
	    			</loit:thd>
	    		</tr>
			</loit:form>
		</div>
	</loit:window>
</loit:body>
</html>
<script language="javascript">
	function query() {
		$('#dgAppRelayBase').datagrid('query');
	}
	function clearForm() {
		$('#relayId').val(' ');
	}
	//双击打开编辑窗口
	function onDCGridRow() {
		to_edit();
	}
	//新增
	function to_add() {
		$("#editformAppRelayBase").form('clear');
		$('#editWindowAppRelayBase').window('setTitle', "增加");
		$('#editWindowAppRelayBase').window('open');
	}
	//修改
	function to_edit() {
		var row = $('#dgAppRelayBase').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var idValue = row.ID;
		appRelayBaseManager.get(idValue, function(data) {
			$('#editformAppRelayBase').form('load', data);
		});
		$('#editWindowAppRelayBase').window('setTitle', "修改");
		$('#editWindowAppRelayBase').window('open');
	}
	//保存
	function to_save() {
		if ($("#editformAppRelayBase").form('validate')) {
			var data = $("#editformAppRelayBase").form("toJSON");
			$.messager.progress();
			$('#editWindowAppRelayBase').window('close');
			appRelayBaseManager.save(data, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title : '系统消息',
					msg : '保存成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dgAppRelayBase').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除
	function to_del() {
		var row = $('#dgAppRelayBase').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
		    if (res) {
				$.messager.progress();
				appRelayBaseManager.removeByPk(row.ID, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title : '系统消息',
						msg : '删除成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#dgAppRelayBase').datagrid('reload');
				});
		    }
		});
	}
</script>
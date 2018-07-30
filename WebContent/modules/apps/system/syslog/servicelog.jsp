<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/base/include/header.jsp"%>
<script type="text/javascript">
//<!--
$(document).ready(function(){
});

function query(){
	$("#servlogGrid").getWlGrid().refresh($("#queryform").getWlForm().fieldToQueryJSON());
}

function clearlog()
{
	if(!confirm("确定要清空服务日志？"))return;
	$.wlPost("wlServiceLogManager", "deleAll", function(data){
		if(data.exception){
			return;
		}
		alert("清空成功");
		query();
	});
}
function clearform()
{
	$("#queryform").getWlForm().clear();
	$("#logTime1").val("");
	$("#logTime2").val("");
}
//-->
</script>
<wl:panel id="treePanel" title="查询条件">
	<wl:form id="queryform">
	<table style="margin-top: 10px;">
		<tr>
			<wl:formfield name="WlServiceLog.serverName" label="服务器地址" exattrs="operator='like'"></wl:formfield>
			<wl:formfield name="WlServiceLog.remoteAddress" label="客户端地址" exattrs="operator='like'"></wl:formfield>
			<wl:formfield name="WlServiceLog.serviceName" label="服务名" exattrs="operator='like'"></wl:formfield>
			<wl:formfield name="WlServiceLog.methodName" label="方法名" exattrs="operator='like'"></wl:formfield>
		
		</tr>
		<tr>
			<wl:formfield name="WlServiceLog.logTime" label="访问时间从" exattrs="operator='>'"  fieldtype="datetime" id="logTime1"></wl:formfield>
			<wl:formfield name="WlServiceLog.logTime" label="到" exattrs="operator='<'" fieldtype="datetime" id="logTime2"></wl:formfield>
		</tr>
	</table>
	</wl:form>
</wl:panel>

<div class="bt_div" style="margin-left:30">
	<wl:button value="查  询" onclick="query()"/>
	<wl:button value="置  空" onclick="clearform()"/>
	<wl:button value="清空服务日志" onclick="clearlog()"/>
</div>

<wl:panel id="userGridPanel" title="服务调用日志">
	<wl:grid id="servlogGrid" datamodel="WlServiceLogModel" width="100%" height="300" multiselect="false" orderby="logTime desc" >
		<wl:col fieldname="WlServiceLog.userId" fieldtype="selectCode.user" width="50"></wl:col>
		<wl:col fieldname="WlServiceLog.serverName" width="100"></wl:col>
		<wl:col fieldname="WlServiceLog.remoteAddress" width="100"></wl:col>
		<wl:col fieldname="WlServiceLog.serviceName" width="400"></wl:col>
		<wl:col fieldname="WlServiceLog.methodName"></wl:col>
		<wl:col fieldname="WlServiceLog.args"></wl:col>
		<wl:col fieldname="WlServiceLog.logTime"></wl:col>
		<wl:col fieldname="WlServiceLog.timeUsed"></wl:col>
		<wl:col fieldname="WlServiceLog.accessIndex"></wl:col>
	</wl:grid>
</wl:panel>
</body>
</html>
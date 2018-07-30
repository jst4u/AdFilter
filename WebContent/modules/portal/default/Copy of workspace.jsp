<%@page import="com.loit.apps.login.CurrentUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/base/include/header.jsp"%>
<script type="text/javascript" src="/loit/base/ui/loit/jquery.fullcalendar.js"></script>

<%
	String user = CurrentUser.currentUser().getUserId();
	String userId = "user_id:" + user;
	String userName = CurrentUser.currentUser().getUsername();
	String title = CurrentUser.currentUser().getFullname() + "的周报";
%>
<title>主页</title>
</head>
<body style="background-color: #F6FAFD;">
	<loit:panel fit="true" border="false" style="padding: 5px;">
		<loit:layout id="top" border="false" fit="true">
			<loit:layoutWest width="300" title="工时日期" collapsible="false">
				<div id="fullCalendar" class="easyui-fullCalendar" fit="true" border="false" firstDay="1" ></div>
			</loit:layoutWest>
			<loit:layoutCenter border="false">
				<loit:layout fit="true" border="false">
					<loit:layoutNorth height="266" title="本周个人统计" collapsible="false">
					<loit:form id="queryform" method="post">
							<input type="hidden" id="startTime" name="startTime" />
							<input type="hidden" id="endTime" name="endTime" />
							<input type="hidden" id="n" name="n" value="5"/>
						</loit:form>
						<iframe id="chart" name="chart" width="715" height="225"
							src="<%=contextPath%>/modules/apps/project/worktime/personnel1.jsp"
							border="0" frameborder="no" marginheight="0" marginwidth="0"
							leftmargin="0" topmargin="0" scrolling="no" noresize="noresize"></iframe>
					</loit:layoutNorth>
					<loit:layoutCenter id="mywt" height="225" title="我的工时"
						tools="#wtButton">
						<loit:form id="queryformTlPjWorktime" method="post">
							<input type="hidden" id="worktimeStartTime" name="startTime" />
							<input type="hidden" id="worktimeEndTime" name="endTime" />
						</loit:form>
						<loit:datagrid id="dgTlPjWorktime" queryform="#queryformTlPjWorktime"
							querysource="service:tlPjWorktimeManager.worktimeList" pageSize="6" pageList="[6,12,18,24,30]"
							rownumbers="true" singleSelect="true" pagination="true" onDblClickRow="onDblClickWT"
							fit="true" border="false" sortName="workDay" sortOrder="desc">
							<loit:column field="id" title="系统ID" hidden="true"></loit:column>
							<loit:column field="pjId" title="项目" codeType="pjIdName"
								sortable="true"></loit:column>
							<loit:column field="workDay" title="日期" sortable="true"></loit:column>
							<loit:column field="workTime" title="工时数量"></loit:column>
							<loit:column field="workDesc" title="工作描述"></loit:column>
							<loit:column field="performance" title="完成情况"></loit:column>
							<loit:column field="problem" title="遇到的问题"></loit:column>
							<loit:column field="opinions" title="审批意见"></loit:column>
							<loit:column field="taskName" title="当前环节" formatter="taskNameFormatter"></loit:column>
							<loit:column field="taskAssignee" title="受理人" codeType="userIdName"></loit:column>
							<loit:column field="0" title="操作" formatter="backFormatter"></loit:column>
						</loit:datagrid>
					</loit:layoutCenter>
				</loit:layout>
			</loit:layoutCenter>

			<loit:layoutEast width="330" border="false">
				<loit:layout fit="true" border="false">
					<loit:layoutNorth height="215" title="近期工时统计(小时)" collapsible="false">
						<loit:datagrid id="sumDataGrid" queryform="#queryform" 
						querysource="service:tlPjWorktimeManager.getNRecentWeeksList"
						 singleSelect="true" fit="true" border="false">
							<loit:column field="startTime" title="开始时间"></loit:column>
							<loit:column field="endTime" title="结束时间"></loit:column>
							<loit:column field="sum" title="总工时"></loit:column>
							<loit:column field="vacation" title="休假"></loit:column>
							<loit:column field="reality" title="实际"></loit:column>
							<loit:column field="take" title="可调休"></loit:column>
						</loit:datagrid>
					</loit:layoutNorth>
					<loit:layoutCenter title="我的周报" tools="#wrButton">
						<loit:form id="queryformReport" method="post">
							<input id="userId1" name="userId" type="hidden">
							<input id="time1" name="time" type="hidden">
							<input id="n1" name="n" type="hidden">
						</loit:form>
						<loit:datagrid id="ownReportList" queryform="#queryformReport"
							querysource="service:tlPjWeeklyreportManager.getOwnReportList"
							singleSelect="true" pagination="true" fit="true" onDblClickRow="onDblClickWR"
							rownumbers="true" border="false" sortName="startTime" pageSize="8" pageList="[8,16,24,32]"
							sortOrder="desc">
							<loit:column field="id" hidden="true"></loit:column>
							<loit:column field="userId" hidden="true"></loit:column>
							<loit:column field="title" title="周报标题" sortable="true"></loit:column>
							<loit:column field="startTime" title="周报日期" sortable="true"></loit:column>
							<loit:column field="problem" title="遇到的问题"></loit:column>
							<loit:column field="plan" title="下周工作计划"></loit:column>
						</loit:datagrid>
					</loit:layoutCenter>
				</loit:layout>
			</loit:layoutEast>
		</loit:layout>
	</loit:panel>
	<div id="wtButton">
		<loit:linkbutton tagClass="icon-add" onclick="addWT()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="editWT()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="delWT()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-reload" onclick="reloadWT()"></loit:linkbutton>
	</div>

	<div id="wrButton">
		<loit:linkbutton tagClass="icon-add" onclick="addWR()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-edit" onclick="editWR()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-remove" onclick="delWR()"></loit:linkbutton>
		<loit:linkbutton tagClass="icon-reload" onclick="reloadWR()"></loit:linkbutton>
	</div>
	
	
	<loit:window id="editWindowTlPjWorktime" title="Modal Window"
			modal="true" closed="true"
			style="width: 350px; height:300px; padding: 10px;">
			<loit:form id="editformTlPjWorktime" method="post" type="ft">
				<tr>
					<input type="hidden" name="id"/>
					<loit:thd label="项目:" tagType="combobox"
						validType="COMCHECK[\\'#pjId1\\']" id="pjId1" name="pjId"
						codeFilter="<%=userId%>" required="true" style="width:200px;"
						codeType="tpjIdName" panelHeight="150" valueField="id"
						editable="false"></loit:thd>
				</tr>
				<tr>
					<input type="hidden" name="userId"/>
					<th>日期：</th>
					<td><input type="text" class="easyui-my97" id="editWorkDay"
						name="workDay" /></td>
				</tr>
				<tr>
					<loit:thd label="工时数量:" tagType="validatebox" name="workTime"
						required="true" style="width:170px">小时</loit:thd>
				</tr>
				<tr>
					<th>工作内容描述:</th>
					<td><textarea class="easyui-validatebox validatebox-text"
							rows="2" name="workDesc" style="width: 200px"></textarea></td>
				</tr>
				<tr>
					<th>工作完成情况:</th>
					<td><textarea class="easyui-validatebox validatebox-text"
							rows="2" name="performance" style="width: 200px"></textarea></td>
				</tr>
				<tr>
					<th>遇到的问题:</th>
					<td><textarea class="easyui-validatebox validatebox-text"
							rows="2" name="problem" style="width: 200px"></textarea></td>
				</tr>
				<loit:thd tcolspan="4" tstyle="text-align: center">
					<loit:linkbutton iconCls="icon-save" onclick="saveWT()">暂存</loit:linkbutton>
					<loit:linkbutton iconCls="icon-ok" onclick="to_start()">提交</loit:linkbutton>
					<loit:linkbutton iconCls="icon-cancel"
						onclick="$('#editWindowTlPjWorktime').window('close');">取消</loit:linkbutton>
				</loit:thd>
			</loit:form>
		</loit:window>

		<loit:window id="modifyTlPjWorktime" title="Modal Window" modal="true"
			closed="true" style="width: 350px; height:300px; padding: 10px;">
			<loit:form id="modifyFormTlPjWorktime" method="post" type="ft">
				<tr>
					<input type="hidden" name="id"/>
					<loit:thd label="项目:" tagType="combobox"
						validType="COMCHECK[\\'#pjId2\\']" id="pjId2" name="pjId"
						codeFilter="<%=userId%>" required="true" style="width:200px;"
						codeType="tpjIdName" panelHeight="150" valueField="id"></loit:thd>
				</tr>
				<tr>
					<input type="hidden" name="userId"/>
					<th>日期：</th>
					<td><input type="text" class="easyui-my97" id="modifyWorkDay"
						name="workDay"></td>
				</tr>
				<tr>
					<loit:thd label="工时数量:" tagType="validatebox" name="workTime"
						required="true" style="width:170px">小时</loit:thd>
				</tr>
				<tr>
					<th>工作内容描述:</th>
					<td><textarea class="easyui-validatebox validatebox-text"
							rows="2" name="workDesc" style="width: 200px"></textarea></td>
				</tr>
				<tr>
					<th>工作完成情况:</th>
					<td><textarea class="easyui-validatebox validatebox-text"
							rows="2" name="performance" style="width: 200px"></textarea></td>
				</tr>
				<tr>
					<th>遇到的问题:</th>
					<td><textarea class="easyui-validatebox validatebox-text"
							rows="2" name="problem" style="width: 200px"></textarea></td>
				</tr>
				<tr>
					<loit:thd tcolspan="4" tstyle="text-align: center">
						<loit:linkbutton iconCls="icon-ok" onclick="to_startModify()">提交</loit:linkbutton>
						<loit:linkbutton iconCls="icon-cancel"
							onclick="$('#modifyTlPjWorktime').window('close');">取消</loit:linkbutton>
					</loit:thd>
				</tr>
			</loit:form>
		</loit:window>
	
	<loit:window id="addWeeklyreport" title="Modal Window"
		modal="true" closed="true"
		style="width: 500px; height: 425px; padding: 10px;">
		<div style="text-align: center;">
			<loit:form id="addformTlPjWeeklyreport" method="post" type="ft">
				<tr align="center">
					<input id="id" name="id" type="hidden" />
					<input id="isreply" name="isreply" type="hidden" />
					<td align="right">周报标题:</td>
					<td align="center" colspan="3"><input id="reportTitle"
						type="text" name="title" style="width: 300px;" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td>开始时间:</td>
					<td><input type="text" class="easyui-my97"
						id="weeklyreportStartTime" name="startTime" /></td>
					<td>结束时间:</td>
					<td><input type="text" id="weeklyreportEndTime" name="endTime"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<td>本周遇到问题:</td>
					<td colspan="3"><textarea
							class="easyui-validatebox validatebox-text" rows="2"
							name="problem" style="width: 300px"></textarea></td>
				</tr>
				<tr>
					<td>下周工作计划:</td>
					<td colspan="3"><textarea
							class="easyui-validatebox validatebox-text" rows="2" name="plan"
							style="width: 300px"></textarea></td>
				</tr>
			</loit:form>
			<div id="list1">
				<loit:datagrid id="addWorktimeList" iconCls="icon-ok"
					queryform="#addform" style="height:200px;"
					querysource="service:tlPjWeeklyreportManager.getNoSubmit"
					rownumbers="true" singleSelect="false" sortName="workDay" sortOrder="asc">
					<loit:column field="ck" checkbox="true"></loit:column>
					<loit:column field="id" hidden="true"></loit:column>
					<loit:column field="pjId" codeType="pjIdName" title="项目" sortable="true"></loit:column>
					<loit:column field="userId" codeType="userIdName" title="填报人"
						></loit:column>
					<loit:column field="workDay" title="日期" sortable="true"></loit:column>
					<loit:column field="workTime" title="工时数量(小时)" align="center"></loit:column>
					<loit:column field="workDesc" title="工作描述"></loit:column>
					<loit:column field="performance" title="工作完成情况"></loit:column>
					<loit:column field="problem" title="遇到的问题"></loit:column>
				</loit:datagrid>
			</div>
			<div id="list2">
			<loit:datagrid id="editWorktimeList" iconCls="icon-ok"
				queryform="#addform"  style="height:200px;"
				querysource="service:tlPjWeeklyreportManager.getEditList"
				rownumbers="true" singleSelect="false" sortName="workDay" sortOrder="asc">
					<loit:column field="ck" checkbox="true"></loit:column>
					<loit:column field="id" hidden="true"></loit:column>
					<loit:column field="pjId" codeType="pjIdName" title="项目" sortable="true"></loit:column>
					<loit:column field="userId" codeType="userIdName" title="填报人"></loit:column>
					<loit:column field="workDay" title="日期" sortable="true"></loit:column>
					<loit:column field="workTime" title="工时数量(小时)" align="center"></loit:column>
					<loit:column field="workDesc" title="工作描述"></loit:column>
					<loit:column field="performance" title="工作完成情况"></loit:column>
					<loit:column field="problem" title="工作中遇到的问题"></loit:column>
				</loit:datagrid>
			</div>
			<br>
			<loit:thd tcolspan="4" tstyle="text-align: center">
				<loit:linkbutton iconCls="icon-save" onclick="saveWR()">保存</loit:linkbutton>
				<loit:linkbutton iconCls="icon-cancel"
					onclick="$('#addWeeklyreport').window('close');">取消</loit:linkbutton>
			</loit:thd>
		</div>
	</loit:window>
<body>
</html>

<script type="text/javascript">
	var flag = false;
	//设置默认查询起始时间，默认为当前七天
	$(function() {
		$("#worktimeStartTime").val(getNowDate());
		$("#worktimeEndTime").val(getNowDate());
		$("#startTime").val(getWeekStartDate());
		$("#endTime").val(getWeekEndDate());
		$('#editWorkDay').my97({
			required : true,
			editable : true,
			maxDate : '%y-%M-%d'
		});
		$('#modifyWorkDay').my97({
			required : true,
			editable : true,
			maxDate : '%y-%M-%d'
		});
		//检查周报是否提交过
		checkReport();
	});
	//检查周报是否提交过
	function checkReport(){
		var userId = '';
		var time = $("#startTime").val();
		
		tlPjWeeklyreportManager.getReport(userId, time, 0, function(report) {
			if (report['startTime']) {
				flag = true;
			} else{
				flag = false;
			}
		});
	}

	//////////日历处理
	$('#fullCalendar').fullCalendar({
		onSelect : function(date, target) {
			//alert(date.getFullYear() + ":" + (date.getMonth() + 1)+ ":" + date.getDate());
			$("#worktimeStartTime").val(formatDate(date));
			$("#worktimeEndTime").val(formatDate(date));
			$("#startTime").val(getWeekStartDate(date));
			$("#endTime").val(getWeekEndDate(date));
			$("#dgTlPjWorktime").datagrid("query");
			query();
			checkReport();
		}
	});

	//////////近期统计处理
	function query() {
		var startTime = $('#queryform').form('getValue', 'startTime');
		var endTime = $('#queryform').form('getValue', 'endTime');
		var path = "<%=contextPath%>/modules/apps/project/worktime/personnel1.jsp?startTime=" + startTime+ "&endTime=" + endTime;
		$('#chart').attr("src",path); 
	}
	
	//////////工时处理
	//新增工时
	function addWT() {
		$("#editformTlPjWorktime").form('clear');
		$('#editWindowTlPjWorktime').window('setTitle', "增加");
		var data = {
				"workDay" : $("#worktimeStartTime").val(),
				"workTime" : 0
			};
		$('#editformTlPjWorktime').form('load', data);
		$('#editWindowTlPjWorktime').window('open');
	}

	//双击，打开工时编辑窗口
	function onDblClickWT() {
		editWT();
	}
	
	//修改工时
	function editWT() {
		var row = $('#dgTlPjWorktime').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		if (row.taskName == "个人调整") {
			var idValue = row.id;
			tlPjWorktimeManager.get(idValue, function(data) {
				$('#modifyFormTlPjWorktime').form('load', data);
				$('#modifyTlPjWorktime').window('setTitle', "个人调整");
				$('#modifyTlPjWorktime').window('open');
			});
			return;
		}
		if (null != row.processInstanceId && row.processInstanceId != "") {
			$.messager.alert("提示信息", "流程已启动，不能进行修改！", "info");
			return;
		}
		var idValue = row.id;
		tlPjWorktimeManager.get(idValue, function(data) {
			$('#editformTlPjWorktime').form('load', data);
			$('#editWindowTlPjWorktime').window('setTitle', "修改");
			$('#editWindowTlPjWorktime').window('open');
		});
	}

	//保存工时
	function saveWT() {
		if ($("#editformTlPjWorktime").form('validate')) {
			var param = $("#editformTlPjWorktime").form("toJSON");
			var number = /^-?\d+\.\d+$/;
			var num = /^([\d-+]*)$/;
			if (!number.test(param.workTime) && !num.test(param.workTime)) {
				$.messager.alert("提示信息", "工时数量请输入数字！", "info");
				return;
			}
			if (param.workTime > 24 || param.workTime <= 0) {
				$.messager.alert("提示信息", "工时数量不能小于等于0或大于24！", "info");
				return;
			}
			$.messager.progress();
			$('#editWindowTlPjWorktime').window('close');
			tlPjWorktimeManager.save(param, function(data) {
				$.messager.progress("close");
				$.messager.show({
					title : '系统消息',
					msg : '保存成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dgTlPjWorktime').datagrid('reload');
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}

	//删除工时
	function delWT() {
		var row = $('#dgTlPjWorktime').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		if ("Admin" == <%="'" + userName + "'"%> ) {//Admin可以强制删除
			$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
				if (res) {
					tlPjWorktimeManager.removeByPkAbsolute(row.id, function(
							data) {
						if (data == 'success') {
							$.messager.show({
								title : '系统消息',
								msg : '删除成功！',
								timeout : 2000,
								showType : 'slide'
							});
						} else {
							$.messager.alert("提示信息", "该工时已经填写周报不能删除！", "info");
						}
						$('#dgTlPjWorktime').datagrid('reload');
					});
				}
			});
			return;
		}
		if (null != row.processInstanceId && row.processInstanceId != "") {
			$.messager.alert("提示信息", "流程已启动，不能删除！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				tlPjWorktimeManager.removeByPk(row.id, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title : '系统消息',
						msg : '删除成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#dgTlPjWorktime').datagrid('reload');
				});
			}
		});
	}
	//刷新工时列表
	function reloadWT(){
		$('#dgTlPjWorktime').datagrid('reload');
	}
	
	//启动流程
	function to_start() {
		if ($("#editformTlPjWorktime").form('validate')) {
			var param = $("#editformTlPjWorktime").form("toJSON");
			var number = /^-?\d+\.\d+$/;
			var num = /^([\d-+]*)$/;
			if (!number.test(param.workTime) && !num.test(param.workTime)) {
				$.messager.alert("提示信息", "工时数量请输入数字！", "info");
				return;
			}
			if (param.workTime > 24 || param.workTime <= 0) {
				$.messager.alert("提示信息", "工时数量不能小于等于0或大于24！", "info");
				return;
			}
			$.messager.progress();

			tlPjWorktimeManager.startWorkflow(param, function(data) {
				$.messager.progress("close");
				if (data == null) {
					$.messager.alert("提示信息", "该项目此日工时已提交！", "info");
				} else {
					$('#editWindowTlPjWorktime').window('close');
					$.messager.show({
						title : '系统消息',
						msg : '提交成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#dgTlPjWorktime').datagrid('reload');
				}
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}

	function to_startModify() {
		if ($("#modifyFormTlPjWorktime").form('validate')) {
			var param = $("#modifyFormTlPjWorktime").form("toJSON");
			var number = /^-?\d+\.\d+$/;
			var num = /^([\d-+]*)$/;
			if (!number.test(param.workTime) && !num.test(param.workTime)) {
				$.messager.alert("提示信息", "工时数量请输入数字！", "info");
				return;
			}
			if (param.workTime > 24 || param.workTime <= 0) {
				$.messager.alert("提示信息", "工时数量不能小于等于0或大于24！", "info");
				return;
			}
			$.messager.progress();
			tlPjWorktimeManager.startModify(param, function(data) {
				$.messager.progress("close");
				if (data == null) {
					$.messager.alert("提示信息", "该项目此日工时已提交！", "info");
				} else {
					$('#modifyTlPjWorktime').window('close');
					$.messager.show({
						title : '系统消息',
						msg : '提交成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#dgTlPjWorktime').datagrid('reload');
				}
			});
		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}

	function taskNameFormatter(value, row, index) {
		if (null == row.processInstanceId || "" == row.processInstanceId) {
			return "起草";
		} else {
			if ((null == row.taskId || "" == row.taskId)
					&& (null == value || "" == value)) {
				return "流程已归档";
			} else {
				return "<a title='点击查看流程跟踪图' href=javascript:followWorkflow('"
						+ row.processInstanceId + "')>" + value + "</a>";
			}
		}
	}

	function followWorkflow(pid) {
		workflowManager.readResource(pid, function(data) {
			if (data == null) {
				$.messager.alert("提示信息", "请求的资源不存在！", "info");
				return;
			}
			$('#picWindow').window('setTitle', "图片资源");
			dwr.util.setValue('image', data);
			$('#picWindow').window('open');
			$('#picWindow').window('maximize');
		});
	}

	function backFormatter(value, row, index) {
		if (row.taskName == "项目经理审核") {
			return "<a href=javascript:backWorkflow('" + row.taskId
					+ "')>收回</a>";
		} else {
			return;
		}
	}

	function backWorkflow(taskId) {
		$.messager.progress();
		tlPjWorktimeManager.rollbackTask(taskId, function(data) {
			$.messager.progress("close");
			if (data == null) {
				$.messager.alert("提示信息", "收回失败！", "info");
			} else if (data == 0) {
				$.messager.alert("提示信息", "该任务无法收回！", "info");
			} else if (data == 1) {
				$.messager.show({
					title : '系统消息',
					msg : '收回成功！',
					timeout : 2000,
					showType : 'slide'
				});
				$('#dgTlPjWorktime').datagrid('reload');
			}
			return;
		});
	}
	
	
	//////////周报处理
	//提交周报
	function addWR(){
		if (flag) {
			$.messager.alert("提示信息", "您已提交过本周周报，不能重复提交！", "info");
			return;
		} 
		$("#addformTlPjWeeklyreport").form('clear');
		$('#reportTitle').val("<%=title %>" );
		$('#addWorktimeList').datagrid('reload');
		var data = {
				"startTime" : $("#startTime").val(),
				"endTime" : $("#endTime").val()
			};
		$('#weeklyreportStartTime').my97({isShowClear:false,isShowOK:false,isShowToday:false,required:true, editable:true,
			disabledDays:[2,3,4,5,6,0],maxDate:'%y-%M-%d',onpicked:function(){$('#weeklyreportEndTime').val(getWeekEndDate(this.value));}});
		$("#addformTlPjWeeklyreport").form('load',data);
		$('#list2').hide();
		$('#list1').show();
		$('#addWeeklyreport').window('setTitle', "提交本周周报");
		$('#addWeeklyreport').window('open');
	}
	
	//双击，打开周报编辑窗口
	function onDblClickWR() {
		editWR();
	}
	
	//修改周报
	function editWR(){
		var row = $('#ownReportList').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要修改的数据行！", "info");
			return;
		}
		var id = row.id;
		$("#addformTlPjWeeklyreport").form('clear');
		$('#editWorktimeList').datagrid('reload');
		$('#weeklyreportStartTime').my97({isShowClear:false,isShowOK:false,isShowToday:false,required:true, editable:true,
			disabledDays:[2,3,4,5,6,0],maxDate:'%y-%M-%d',onpicked:function(){$('#weeklyreportEndTime').val(getWeekEndDate(this.value));}});
		tlPjWeeklyreportManager.get(id, function(report) {
			$("#addformTlPjWeeklyreport").form('load', report);
			if('1'==report['isreply']){
				$.messager.alert("提示信息", "对不起，周报领导已批复不能修改！", "info");
			}else{
				$('#list1').hide();
				$('#list2').show();
				$('#addWeeklyreport').window('setTitle', "修改本周周报");
				$('#addWeeklyreport').window('open');
			}
		});
		
	}
	
	
	//保存周报
	function saveWR() {
		if ($("#addformTlPjWeeklyreport").form('validate')) {
			var data = $("#addformTlPjWeeklyreport").form("toJSON");
			var checkedItems = '';
			if ($('#list2').css("display") == 'none') {
				checkedItems = $('#addWorktimeList').datagrid('getChecked');
			} else {
				checkedItems = $('#editWorktimeList').datagrid('getChecked');
			}
			if (checkedItems.length == 0) {
				$.messager.alert("提示信息", "请选择工时信息！", "info");
				return;
			}
			var wtIds = '';
			var wrId = '';
			$.each(checkedItems, function(index, item) {
				wtIds += item.id + ";";
			});
			$.messager.progress();
			$('#addWeeklyreport').window('close');

			tlPjWeeklyreportManager.save(data, function(report) {
				wrId = report['id'];
				tlPjWeeklyreportManager.saveRelation(wrId, wtIds, function(ii) {
					$.messager.progress("close");
					$.messager.show({
						title : '系统消息',
						msg : '保存成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#ownReportList').datagrid('reload');
				});
			});
			$.messager.progress("close");

		} else {
			$.messager.alert("提示信息", "信息项填写错误，请修改后再保存！", "info");
			return;
		}
	}
	//删除周报
	function delWR() {
		var row = $('#ownReportList').datagrid("getSelected");
		if (!row) {
			$.messager.alert("提示信息", "请选择需要删除的数据行！", "info");
			return;
		}
		if (row.isreply == 1) {
			$.messager.alert("提示信息", "已批复周报不能删除！", "info");
			return;
		}
		$.messager.confirm("确认信息", "确定删除数据行？", function(res) {
			if (res) {
				$.messager.progress();
				tlPjWeeklyreportManager.removeByPk(row.id, function(data) {
					$.messager.progress("close");
					$.messager.show({
						title : '系统消息',
						msg : '删除成功！',
						timeout : 2000,
						showType : 'slide'
					});
					$('#ownReportList').datagrid('reload');
				});
			}
		});
	}

	//刷新周报列表
	function reloadWR() {
		$('#ownReportList').datagrid('reload');
	}

	
	
	//**************时间处理*****************//

	/**
	 * 获取当天日期
	 */
	var now = new Date(); //当前日期
	var nowDayOfWeek = now.getDay() - 1; //今天本周的第几天
	var nowDay = now.getDate(); //当前日
	var nowMonth = now.getMonth(); //当前月
	var nowYear = now.getYear(); //当前年
	nowYear += (nowYear < 2000) ? 1900 : 0; //

	//格局化日期：yyyy-MM-dd
	function formatDate(date) {
		var myyear = date.getFullYear();
		var mymonth = date.getMonth() + 1;
		var myweekday = date.getDate();

		if (mymonth < 10) {
			mymonth = "0" + mymonth;
		}
		if (myweekday < 10) {
			myweekday = "0" + myweekday;
		}
		return (myyear + "-" + mymonth + "-" + myweekday);
	}
	//获得当前日期
	function getNowDate() {
		var nowDate = new Date(nowYear, nowMonth, nowDay);
		return formatDate(nowDate);
	}
	//yyyy-MM-dd转换成yyyy/MM/dd
	function convertsDate(date) {
		var dates1 = date.split('-');
		var dates2 = '';
		for ( var i = 0; i < dates1.length; i++) {
			if (i < dates1.length - 1) {
				dates2 = dates2 + dates1[i] + '/';
			} else {
				dates2 = dates2 + dates1[i];
			}
		}
		return dates2;
	}
	//获得本周的开始日期  
	function getWeekStartDate(date) {
		var now = new Date();
		if (date) {
			now = new Date(date);
		}
		var nowDayOfWeek = now.getDay() - 1; //今天本周的第几天
		var nowDay = now.getDate(); //当前日
		var nowMonth = now.getMonth(); //当前月
		var nowYear = now.getYear(); //当前年
		nowYear += (nowYear < 2000) ? 1900 : 0; //
		var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
		return formatDate(weekStartDate);
	}
	//获得本周的结束日期  
	function getWeekEndDate(date) {
		var now = new Date();
		if (date) {
			now = new Date(date);
		}
		var nowDayOfWeek = now.getDay() - 1; //今天本周的第几天
		var nowDay = now.getDate(); //当前日
		var nowMonth = now.getMonth(); //当前月
		var nowYear = now.getYear(); //当前年
		nowYear += (nowYear < 2000) ? 1900 : 0; //
		var weekEndDate = new Date(nowYear, nowMonth, nowDay
				+ (6 - nowDayOfWeek));
		return formatDate(weekEndDate);
	}
</script>
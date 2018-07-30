$.fn.datagrid.defaults.loader = getLoader('datagrid');

$.fn.datagrid.parseOptions=function(_1db){
	var t=$(_1db);
	return $.extend({},$.fn.panel.parseOptions(_1db),$.parser.parseOptions(_1db,["url","querysource","queryform","toolbar","idField","sortName","sortOrder","pagePosition","resizeHandle",{fitColumns:"boolean",autoRowHeight:"boolean",striped:"boolean",nowrap:"boolean"},{rownumbers:"boolean",singleSelect:"boolean",checkOnSelect:"boolean",selectOnCheck:"boolean"},{pagination:"boolean",pageSize:"number",pageNumber:"number"},{remoteSort:"boolean",showHeader:"boolean",showFooter:"boolean"},{scrollbarSize:"number"}]),{pageList:(t.attr("pageList")?eval(t.attr("pageList")):undefined),loadMsg:(t.attr("loadMsg")!=undefined?t.attr("loadMsg"):undefined),rowStyler:(t.attr("rowStyler")?eval(t.attr("rowStyler")):undefined)});
	};
	
// 扩展easyui grid组件
// $("#gridid").datagrid('query','paramformid')
$.extend($.fn.datagrid.methods, {
	query : function(jq,param) {
		return jq.each(function() {
			var datagrid = $(this);
			var options = datagrid.datagrid('options');
			
			//1.获得查询条件表单
			var querysource =  options.querysource;
			if(!querysource || "" == querysource){
				alert("未设置查询数据源！querysource");
			}
			
			//form的id处理
			var queryform = options.queryform;
			if(queryform && queryform != ""){
				if(queryform.indexOf('#') != 0){
					queryform = '#'+queryform;
				}
			}
			
			//所有input，easyi中只有input，下拉列表也是input
			//TODO 多选处理
			var qFields = [];
			$(queryform + " input[name]").each(function() {
				var input = $(this);
				var fName=input.attr("name");
				var fValue=this.value;
				var fType ="";
				var opt = input.attr("operator");
				var fieldType;
				if(!fValue || "" == fValue){
					return;
				}
				//组合框类型的控件处理
				if (input.hasClass("combo-value")) {
					var comboInput = input.parent().prev();
					if (comboInput.hasClass("easyui-datebox") || comboInput.hasClass("easyui-datetimebox")) {
						fieldType = "Date";
					}
					opt = comboInput.attr("operator");
//					if ($comboInput.attr("multiple")) {
//						var values = [];
//						$("#" + param.paramForm + " input[name='" + name + "']").each(function() {
//							values.push(this.value);
//						});
//						queryFields.push({
//							fieldName : name,
//							fieldType : "String[]",
//							fieldStringValue : values.join(","),
//							operator : operator
//						});
//						handledMultiples.push(name);
//						return;
//					}
				}
				
				qFields.push({
					fieldName : fName,
					fieldType : fType,
					fieldStringValue : fValue,
					operator : opt
				});
			});
			//获取排序信息，设置排序事件响应
			
		
			//获得查询分页信息，设置分页组件函数调用查询功能
			var pager = datagrid.datagrid('getPager');
			$(pager).pagination({
                onSelectPage:function(pageNumber, pageSize){
                	datagrid.datagrid('options').pageSize = pageSize;
                	datagrid.datagrid('options').pageNumber = pageNumber;
                    datagrid.datagrid('query'); 
                }
             });
			var pageSize =datagrid.datagrid('options').pageSize;
			var pageNumber = datagrid.datagrid('options').pageNumber;
			
			//查询并处理结果
			var sortName = options.sortName;
			var sortOrder = options.sortOrder;
			var orderBy;
			if(sortName){
				orderBy = sortName+" "+sortOrder;
			}
			
			// fieldCodeTypes代码转换参数
			var fieldCodeTypes = {};

			$("#"+datagrid.attr("id") + " thead th").each(function() {
				var th = $(this);
				var codeType = th.attr("codeType");
				if(codeType){
					var field = $.parser.parseOptions(th,['field']);
					fieldCodeTypes[field.field] = codeType;
				}
			});
			
			//修正不分页时的页码数据，给一个大的pageSize
			var pagingInfo =  null;
			if(options.pagination){
				pagingInfo = {
						pageSize : pageSize,
						currentPage : pageNumber
				};
			}
			
			var queryInfo ={
					querySource:querysource,
					fieldCodeTypes :fieldCodeTypes,
					queryFields : qFields,
					orderBy:orderBy,//orderBy : (param.sort ? param.sort + " " + param.order : undefined),
					pagingInfo : pagingInfo
			};
			datagrid.datagrid('loading');
			
			commonQueryManager.queryDataTable(queryInfo,function(result){
				 //填充数据
				datagrid.datagrid('loadData', result.dataList);
				
				//分页设置，设置总数
				if(options.pagination){
					datagrid.datagrid('getPager').pagination({
						total:result.pagingInfo.totalRows
					});
				}else{
					
				}
				
				datagrid.datagrid('loaded');
			});
			
		});
	}
});
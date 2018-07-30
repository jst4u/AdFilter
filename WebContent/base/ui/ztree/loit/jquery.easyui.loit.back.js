function getLoader(pluginName){
	if(pluginName == "combotree"){
		pluginName="tree";
	}
	return function (param, success, error){
					var opts = $(this)[pluginName]('options');
					if (!opts.url){
						$(this)[pluginName]('query');
					} else if("none" == opts.url){
						return false;
					}else{
						$.ajax({
							type: opts.method,
							url: opts.url,
							data: param,
							dataType: 'json',
							success: function(data){
								success(data);
							},
							error: function(){
								error.apply(this, arguments);
							}
						});
					}
				};
}

$.fn.treegrid.defaults.loader = getLoader('treegrid');
$.fn.datagrid.defaults.loader = getLoader('datagrid');
$.fn.tree.defaults.loader = getLoader('tree');
$.fn.combobox.defaults.loader = getLoader('combobox');

$.fn.tree.parseOptions=function(_108){
	var t=$(_108);
	return $.extend({},$.parser.parseOptions(_108,["querysource","idField","textField","pidField","openLevel","url","method",{checkbox:"boolean",cascadeCheck:"boolean",onlyLeafCheck:"boolean"},{animate:"boolean",lines:"boolean",dnd:"boolean"}]));
};

//$.fn.tree.parseOptions=function(_108){
//	
//	return $.extend({},$.parser.parseOptions(_108,["url","method",{checkbox:"boolean",cascadeCheck:"boolean",onlyLeafCheck:"boolean"},{animate:"boolean",lines:"boolean",dnd:"boolean"}]));
//};

//$.fn.combotree.defaults.loader = getLoader('combotree');
//$.fn.combotree.defaults=$.extend({},$.fn.combo.defaults,$.fn.tree.defaults,{editable:false});
//alert($.fn.combotree.defaults.loader );
//扩展easyui form组件
//$("#formid").form('toJSON')
//$("#formid").form('getValue','valueName')
$.extend($.fn.form.methods, {
	toJSON : function(jq) {
		var arrayValue = $(jq[0]).serializeArray();
		var json = {};
		$.each(arrayValue, function() {
			var item = this;
			if (json[item["name"]]) {
				json[item["name"]] = json[item["name"]] + "," + item["value"];
			} else {
				json[item["name"]] = item["value"];
			}
		});
		return json;
	},
	getValue : function(jq, name) {
		var jsonValue = $(jq[0]).form("toJSON");
		return jsonValue[name];
	}
});

// 扩展easyui grid组件
// $("#gridid").datagrid('query','paramformid')
$.extend($.fn.datagrid.methods, {
	query : function(jq,param) {
		return jq.each(function() {
			var datagrid = $(this);
			
			//1.获得查询条件表单
			var querysource = datagrid.attr("querysource");
			if(!querysource || "" == querysource){
				alert("未设置查询数据源！querysource");
			}
			
			//form的id处理
			var queryform = datagrid.attr("queryform");
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
			var options = datagrid.datagrid("options");
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
			
			commonQueryManager.query(queryInfo,function(result){
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


//扩展easyui grid组件
//$("#gridid").treegrid('query','paramformid')
$.extend($.fn.treegrid.methods, {
	query : function(jq,startId) {
		return jq.each(function() {
			var treegrid = $(this);
			//1.获得查询条件表单
			var querysource = treegrid.attr("querysource");
			if(!querysource || "" == querysource){
				alert("未设置查询数据对象！querysource");
			}
			
			
			//form的id处理
			var queryform = treegrid.attr("queryform");
			if(queryform && queryform != ""){
				if(queryform.indexOf('#') != 0){
					queryform = '#'+queryform;
				}
			}
//			alert(1);
			//所有input，easyi中只有input，下拉列表也是input
			//TODO 多选处理 
			var qFields = [];
			$(queryform + " input[name]").each(function() {
				var input = $(this);
				var fName=input.attr("name");
				var fValue=this.value;
				var fType ="";
				var opt = input.attr("operator");
				
				qFields.push({
					fieldName : fName,
					fieldType : fType,
					fieldStringValue : fValue,
					operator : opt
				});
			});
//			alert(2);
			
			//获得查询分页信息，设置分页组件函数调用查询功能
			var pager = treegrid.treegrid('getPager');
			$(pager).pagination({
             onSelectPage:function(pageNumber, pageSize){
            	 treegrid.treegrid('options').pageSize = pageSize;
            	 treegrid.treegrid('options').pageNumber = pageNumber;
            	// treegrid.treegrid('query',startId); 
             }
          });
			
			var pageSize =treegrid.treegrid('options').pageSize;
			var pageNumber = treegrid.treegrid('options').pageNumber;
//			alert(3);
			//查询并处理结果
			var options = treegrid.treegrid("options");
//			alert(4);
			
			var sortName = options.sortName;
			var sortOrder = options.sortOrder;
			var orderBy;
			if(sortName){
				orderBy = sortName+" "+sortOrder;
			}
			
			
			
			// fieldCodeTypes代码转换参数
			var fieldCodeTypes = {};

			$("#"+treegrid.attr("id") + " thead th").each(function() {
				var th = $(this);
				var codeType = th.attr("codeType");
				if(codeType){
					var field = $.parser.parseOptions(th,['field']);
					fieldCodeTypes[field.field] = codeType;
				}
			});
			
			var pagingInfo = {
					pageSize : pageSize,
					currentPage : pageNumber
			};
			
			var queryInfo ={
					querySource:querysource,
					fieldCodeTypes : fieldCodeTypes,
					queryFields : qFields,
					orderBy:orderBy,//orderBy : (param.sort ? param.sort + " " + param.order : undefined),
					pagingInfo : pagingInfo
			};
//			alert(5);
			var idField =treegrid.treegrid('options').idField;
			var pidField = treegrid.attr("pidField");
			commonQueryManager.queryTreegrid(queryInfo,idField,pidField,startId,function(result){
				 //填充数据
				treegrid.treegrid('loadData', result.dataList);

				//分页设置，设置总数
				treegrid.treegrid('getPager').pagination({
					total:result.pagingInfo.totalRows
				});
				treegrid.treegrid('loaded');
			});
			
		});
	}
});

//扩展easyui tree组件
//$("#treeid").tree('query','paramformid')
$.extend($.fn.tree.methods, {
	query : function(jq,startId) {
		return jq.each(function() {
			
			var tree = $(this);
			var options = tree.tree("options");
			
			//1.获得查询条件表单
			var querysource = options.querysource;
			if(!querysource || "" == querysource){
				alert("未设置查询数据对象！querysource");
			}

			var qFields = [];
			//查询并处理结果
			
			// fieldCodeTypes代码转换参数
			var fieldCodeTypes = {};

//			$("#"+tree.attr("id") + " thead th").each(function() {
//				var th = $(this);
//				var codeType = th.attr("codeType");
//				if(codeType){
//					var field = $.parser.parseOptions(th,['field']);
//					fieldCodeTypes[field.field] = codeType;
//				}
//			});
//			
//			var pagingInfo = {
//					pageSize : pageSize,
//					currentPage : pageNumber
//			};
			
			var queryInfo ={
					querySource:querysource,
					fieldCodeTypes : fieldCodeTypes,
					queryFields : qFields,
					orderBy:null,//orderBy : (param.sort ? param.sort + " " + param.order : undefined),
					pagingInfo : null
			};
//			alert(5);
			var idField = options.idField;
			
			var textField =  options.textField;
			var pidField =  options.pidField;
			var openLevel = 0;
			if(options.openLevel){
				openLevel = parseInt(options.openLevel);
				if(!openLevel){
					openLevel=0;
				}
			}
			commonQueryManager.queryTree(queryInfo,idField,textField,pidField,startId,openLevel,function(result){
				 //填充数据
				tree.tree('loadData', result.dataList);
			});
			
		});
	}
});

//扩展easyui combobox组件
//$("#combobox").combobox('query')
$.extend($.fn.combobox.methods, {
	query : function(jq,param) {
		return jq.each(function() {
			var combobox = $(this);
			var codePlease = combobox.attr("codePlease");
			var codeType = combobox.attr("codeType");
			var codeFilter = combobox.attr("codeFilter");
			var orderBy = combobox.attr("orderBy");
			commonQueryManager.queryCode(codeType,orderBy,codeFilter,function(result){
				
				if(codePlease){
					var pla ={"id":"","text":codePlease};
					result.unshift(pla);
					combobox.combobox('loadData', result);
				}else{
					combobox.combobox('loadData', result);
				}
				
			});	
			
		});
	}
});

var Formatter = {

	    //EasyUI用DataGrid用日期格式化
		LocaleTime: function (value, rec, index) {
	        if (value == undefined || "" ==value ) {
	            return "";
	        }
	        var dateValue=new Date(value);
	        return dateValue.toLocaleTimeString();
	    },
	    LocaleDateTime: function (value, rec, index) {
	    	if (value == undefined || "" ==value ) {
	            return "";
	        }
	        var dateValue=new Date(value);
	        return dateValue.toLocaleString();
	    },

	    LocaleDate: function (value, rec, index) {
	        if (value == undefined || "" ==value ) {
	            return "";
	        }
	        var dateValue=new Date(value);

	        return dateValue.toLocaleDateString();
	    }
	};
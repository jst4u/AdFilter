$.fn.tree.defaults.loader = getLoader('tree');

$.fn.tree.parseOptions=function(_108){
	var t = $(_108);
	var options = {};
	var s = $.trim(t.attr('data-options'));
	var opt = $.extend({},$.parser.parseOptions(_108,["querysource","idField","textField","pidField","openLevel","url","method",{checkbox:"boolean",cascadeCheck:"boolean",onlyLeafCheck:"boolean"},{animate:"boolean",lines:"boolean",dnd:"boolean"}]));
	return opt;
};

//扩展easyui tree组件
$.extend($.fn.tree.methods, {
	//$("#treeid").tree('query','paramformid')
	query : function(jq,startId) {
		return jq.each(function() {
			var tree = $(this);
			
			var options = tree.tree("options");
			//1.获得查询条件表单
			var querysource = options.querysource;
			if(!querysource || "" == querysource){
				alert("未设置查询数据对象！querysource");
				return;
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
	},
	//$("#treeid").tree('getChoosed')
	//获取"勾选"和"半选"
	getChoosed : function(jq) {
		var res = [];
		jq.find(".tree-checkbox1").parent().children('.tree-title').each(function() {
			res.push($(this).parent().attr('node-id'));
		});
		jq.find(".tree-checkbox2").parent().children('.tree-title').each(function() {
			res.push($(this).parent().attr('node-id'));
		});
		return res;
	}
});

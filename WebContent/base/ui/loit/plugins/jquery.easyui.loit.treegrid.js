$.fn.treegrid.defaults.loader = getLoader('treegrid');

$.fn.treegrid.parseOptions=function(_b8){
	return $.extend({},$.fn.datagrid.parseOptions(_b8),$.parser.parseOptions(_b8,["startId","pidField","treeField",{animate:"boolean"}]));
	};
	
//扩展easyui grid组件
//$("#gridid").treegrid('query','paramformid')
$.extend($.fn.treegrid.methods, {
	query : function(jq,param) {
		return jq.each(function() {
			var treegrid = $(this);
			var options =  treegrid.treegrid('options');
			//1.获得查询条件表单
			var querysource = options.querysource;
			if(!querysource || "" == querysource){
				alert("未设置查询数据对象！querysource");
			}
			var startId = options.startId;
			
			//form的id处理
			var queryform = options.queryform;
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
            	 options.pageSize = pageSize;
            	 options.pageNumber = pageNumber;
            	// treegrid.treegrid('query',startId); 
             }
          });
			
			var pageSize =options.pageSize;
			var pageNumber = options.pageNumber;

			//查询并处理结果
			
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
			var idField =options.idField;
			var pidField = options.pidField;
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
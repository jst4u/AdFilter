$.fn.combobox.defaults.loader = getLoader('combobox');
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
			
			if("" == codeType){
				return;
			}
			
			commonQueryManager.queryCode(codeType,orderBy,codeFilter,function(result){
				
				if(codePlease){
					var pla ={"id":"","text":codePlease};
					result.unshift(pla);
				}
				combobox.combobox('loadData', result);
				
				
			});	
			
		});
	}
});
$.fn.combobox.defaults.loader = getLoader('combobox');
// 扩展easyui combobox组件
// $("#combobox").combobox('query')
$.extend($.fn.combobox.methods, {
	query : function(jq, param) {
		return jq.each(function() {
			var combobox = $(this);
			var codePlease = combobox.attr("codePlease");
			var codeType = combobox.attr("codeType");
			var codeFilter = combobox.attr("codeFilter");
			var qFields = null;
			if (codeFilter) {
				qFields = [];
				var cf = codeFilter.split(",");
				for ( var i = 0; i < cf.length; i++) {
					var res = cf[i].split(":");
					var fValue = res[1];// 直接参数
					if (fValue.indexOf('#') == 0) {// 级联ID
						fValue = $(fValue).combobox("getValue");
					}
					qFields.push({
						fieldName : res[0],
						fieldStringValue : fValue
					});
				}
			}

			var orderBy = combobox.attr("orderBy");
			if ("" == codeType) {
				return;
			}
			commonQueryManager.queryCode(codeType, orderBy, qFields, function(result) {
				if (codePlease) {
					var pla = {
						"id" : "",
						"text" : codePlease
					};
					result.unshift(pla);
				}
				combobox.combobox('loadData', result);
			});
		});
	}
});

//验证选择combobox正确的值
//validType="COMCHECK[\\'#pjId\\']" tagType="combobox" id="pjId"
$.extend($.fn.validatebox.defaults.rules, {
	COMCHECK: {
        validator: function (value, param) {
        	var val = $(param + "").combobox('getValue');
        	var data = $(param + "").combobox('getData');
        	for (var i=0; i<data.length; i++) {
        		if (data[i].id == val) {
        			return true;
        		}
        	}
        	return false;
        },
        message: '请选择正确的值'
    }
});

function getLoader(pluginName){
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
	    },
	    //yyyy-MM-dd hh:mm:ss
		DateTimeFormatter:function(value, rec, index) {
			var y = value.getFullYear();
			var M = value.getMonth() + 1;
			var d = value.getDate();
			var h = value.getHours();
			var m = value.getMinutes();
			var s = value.getSeconds();
			return y + '-' + (M < 10 ? '0' + M : M) + '-' + (d < 10 ? '0' + d : d)
					+ " " + (h < 10 ? '0' + h : h) + ":" + (m < 10 ? '0' + m : m)
					+ ":" + (s < 10 ? '0' + s : s);
		}
	};

function dwrToString(data) {
	return dwr.util.toDescriptiveString(data, 1);
}
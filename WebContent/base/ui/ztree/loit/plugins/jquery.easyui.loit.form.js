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

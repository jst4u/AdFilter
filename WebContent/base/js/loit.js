(function() {
	/********** DWR **********/
	dwr.util.useLoadingMessage();
	
	dwr.engine.setErrorHandler(function(message, ex) {
		dwr.engine._debug("Error: " + ex.name + ", " + ex.message, true);
		if (message == null || message == "") {
			$.messager.alert("警告", "系统错误，无法执行操作.", "error");
			$.messager.progress("close");
		} else if (message.indexOf("0x80040111") != -1) {
			// Ignore NS_ERROR_NOT_AVAILABLE if Mozilla is being narky
			dwr.engine._debug(message);
		} else {
			$.messager.alert("警告", message, "warning");
			$.messager.progress("close");
		}
	});
})();

GlobalSearchPortlet = {
	submitEnabled: false,
		
	checkFreeText: function(obj, namespace, emptyFreeText, keywords_defaults) {
		if (obj.value == '') {
			$("#" + namespace + "sbm").addClass('submitDisabled');
			$("#" + namespace + "sbm").attr("title", $("#" + namespace + "btnDisabledTitle").val());
			GlobalSearchPortlet.submitEnabled = false;

			obj.className = 'gray-text';
		} else {
			$("#" + namespace + "sbm").removeClass('submitDisabled');
			$("#" + namespace + "sbm").attr("title", '');
			GlobalSearchPortlet.submitEnabled = true;
		}
	},

	onkeyup: function(obj, namespace, emptyFreeText, keywords_defaults) {
		GlobalSearchPortlet.checkFreeText(obj, namespace, emptyFreeText, keywords_defaults);

		if (obj.value != '') {
			obj.className = 'normal-text';
		}
	},
	
	onblur: function(obj, namespace, emptyFreeText, keywords_defaults) {
		GlobalSearchPortlet.checkFreeText(obj, namespace, emptyFreeText, keywords_defaults);
		if (obj.value == '') {
			obj.value = keywords_defaults;
		}
	},
	
	submit: function(namespace) {
		if (!GlobalSearchPortlet.submitEnabled) {
			alert($("#" + namespace + "btnDisabledTitle").val());
		} else {
			$("#" + namespace + "fm").submit();
		}
	}
};
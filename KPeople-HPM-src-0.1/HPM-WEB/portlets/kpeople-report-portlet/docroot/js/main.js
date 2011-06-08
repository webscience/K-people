KPeople.ReportSearch = {
	checkRadio: function(namespace, radioObj) {
		var id = radioObj.id;
		
		if (id == namespace + 'process_radio') {
			$("#" + namespace + "type_select").val('');
			$("#" + namespace + "type_select").attr('disabled', 'disabled');
			$("#" + namespace + "process_select").removeAttr('disabled');
		} else {
			$("#" + namespace + "process_select").val('');
			$("#" + namespace + "process_select").attr('disabled', 'disabled');
			$("#" + namespace + "type_select").removeAttr('disabled');
		}
	}
	

		
		
		
}

KPeople = window.KPeople || {};

KPeople.ProcessSearch = {
	switchAdv: function(namespace) {
		$('#' + namespace + 'ricAvContainer').slideDown();
		$('#' + namespace + 'switchAdvBtn').hide();
		$('#' + namespace + 'switchSmplBtn').show();
		
		$('#' + namespace + 'cmd').val('ADVANCED_SEARCH_PROCESS');
	},	
	
	switchSmpl: function(namespace) {
		$('#' + namespace + 'ricAvContainer').slideUp();
		$('#' + namespace + 'switchAdvBtn').show();
		$('#' + namespace + 'switchSmplBtn').hide();
		
		$('#' + namespace + 'cmd').val('SIMPLE_SEARCH_PROCESS');
	},
	
	/**
	 * check del due date di avvio pattern
	 */
	checkDueDate: function(id) {
		var validformat=/^\d{2}\/\d{2}\/\d{4}$/; //Basic check for format validity
		var valore = $("#" + id).val();
	
		var formatoErrato = false;
			
		if (!validformat.test(valore)) {
			formatoErrato = true;
		} else {
			var dayfield=valore.split("/")[0];
			var monthfield=valore.split("/")[1];
			var yearfield=valore.split("/")[2];
			
			var dayobj = new Date(yearfield, monthfield-1, dayfield);
			if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) {
				formatoErrato = true;
			}
		}
		
		if (formatoErrato) {
			$("#" + id).val('');
		}
	}
};

var NewProcess = {
		
	/**
	 * check del due date di avvio pattern
	 */
	checkDueDate: function() {
		var validformat=/^\d{2}\/\d{2}\/\d{4}$/; //Basic check for format validity
		var valore = $("#dueDateString").val();
	
		var formatoErrato = false;
			
		if (!validformat.test(valore)) {
			formatoErrato = true;
		} else {
			var dayfield=valore.split("/")[0];
			var monthfield=valore.split("/")[1];
			var yearfield=valore.split("/")[2];
			
			var dayobj = new Date(yearfield, monthfield-1, dayfield);
			if ((dayobj.getMonth()+1!=monthfield)||(dayobj.getDate()!=dayfield)||(dayobj.getFullYear()!=yearfield)) {
				formatoErrato = true;
			}
		}
		
		if (formatoErrato) {
			$("#dueDateString").val('');
		}
	}
}
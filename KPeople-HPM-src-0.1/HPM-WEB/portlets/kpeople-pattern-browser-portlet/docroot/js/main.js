var NewPattern = {
	removeAttachment: function(id) {
		$("#attachment_" + id).remove();
	},
		
	addAttachment: function() {
		var date = new Date().valueOf();

		var newFile = 
			"<div id='attachment_" + date + "'>" +
			"	<input type=\"file\" name=\"" + createPatternRichiediContributoNs + "_pattern_newFile_" + date + "\">" +
			"	<span style=\"cursor: pointer;\"  onclick=\"NewPattern.removeAttachment('" + date + "');\"><img src=\"/kpeople-pattern-browser-portlet/images/trash.gif\"></span>" +
			"</div>";
		
		$("#filesContainer").append(newFile);
	},

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
	},
		
	/**
	 * Usato nella pagina di avvio pattern. Chiamato alla selezione di un valore
	 * dall'autocomplete
	 */
	checkSelectedItem: function(element) {
		var elTrovato = false;
		for (var i=0; i < projects.length; i++) {
			if (projects[i].label == element.value) {
				elTrovato = true;
			}
		}
		
	//	se non trovo l'elemento tra quelli ammissibili, cancello textbox e hidden
		if (!elTrovato) {
			if (element.id == 'patternProvider-lbl') {
				element.value = '';
				$("#patternProvider").val('');
			}
			
			if (element.id == 'patternUserCc-lbl') {
				element.value = '';
				$("#patternUserCc").val('');
			}
		}
	},
	
	/**
	 * inizializza gli autocomplete nella pagina 'nuovo pattern'
	 */
	initPatternAutocomplete: function(id) {
		$(id + "-lbl").autocomplete({
			minLength: 0,
			source: projects,
			focus: function( event, ui ) {
				$(id + "-lbl").val( ui.item.label );
				return false;
			},
			select: function( event, ui ) {
				$(id + "-lbl").val(ui.item.label);
				$(id).val(ui.item.value);
	
				return false;
			}
		})
		.data("autocomplete")._renderItem = function( ul, item ) {
			return $( "<li></li>" )
				.data( "item.autocomplete", item )
				.append( "<a>" + item.label + "</a>" )
				.appendTo( ul );
		};
	}
}
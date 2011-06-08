$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

KPeople = window.KPeople || {};

KPeople.Common = {
	load: function(divId, uri, formId) {
		var divContainer = jQuery("#" + divId);
		
		if (divContainer == null || divContainer == "undefined")
			return;
		
		var data = null;
		
		var form = jQuery("#" + formId);
		
		if (form != null && form != "undefined")
			 data = form.serializeObject();
		
		jQuery('#'+divId).html("<img src='/kpeople-page-theme/images/application/loading_indicator.gif'/>");
		
		divContainer.load(
			uri,
			data,
			function(response, status, xhr) {
				if (status == "error" || xhr.status==0) {
					var msg = "Loading error: ";
					jQuery('#'+divId).html(msg + xhr.status + " " + xhr.statusText);
				}
			}
		);
	}
};
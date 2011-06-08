var DocumentPortlet = {
	open: function() {
		$('.docContainerRpt').animate({
			height: "120px"
		  }, 1000 );
		
		$('.docContainerTop .arrowUp').show();
		$('.docContainerTop .arrowDown').hide();
	},

	close: function() {
		$('.docContainerRpt').animate({
			height: "0px"
		  }, 1000 );
		
		$('.docContainerTop .arrowUp').hide();
		$('.docContainerTop .arrowDown').show();
	}	
}
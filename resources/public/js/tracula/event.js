Tracula.Event = {
	/**
	 @param name [String]
	*/
	createEvent : function(name, detail) {
	  return new CustomEvent(
	    name,
	    {
	      detail: detail,
	      bubbles: true,
	      cancelable: true
	    }
  	);
	},
	sendEvent : function(name, detail) {
  	var evt = Tracula.createEvent(name, detail);
  	document.dispatchEvent(evt);
	}
}
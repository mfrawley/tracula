Tracula.History = {
	pushState : function(state, title, url) {
	  //window.location.hash = url;
	  history.pushState(state, title, url);
	}
}
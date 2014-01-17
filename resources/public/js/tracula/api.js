Tracula.Api = {
	config : {
		scheme : 'http',
		host : 'localhost',
		port : '8080',
		pathPrefix : '/api',
	},
	buildUrlForResource: function(resource) {
		var c = this.config;
		return c.scheme + '://' + c.host + ':' + c.port + c.pathPrefix + '/' + resource;
	},
	get : function(resource, success) {
		$.getJSON('http://localhost:8080/api/tickets/'+search, success);
	}
};
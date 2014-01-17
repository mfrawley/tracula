Tracula.Api = {
	config : {
		scheme : 'http',
		host : 'localhost',
		port : '8080',
		pathPrefix : '/api',
	},
	buildUrlForResource: function(res) {
		var c = this.config;
		return c.scheme + '://' + c.host + ':' + c.port + c.pathPrefix + '/' + res;
	},
	get : function(resource, success) {
		$.getJSON(this.buildUrlForResource(resource), success);
	}
};

Tracula.Api.Ticket = {
	get : function(id, callback) {
		Tracula.Api.get('tickets/'+id, function(data) {
      Tracula.Event.sendEvent('ticketLoaded', data);
      // history.pushState(data, data.summary, '/ticket/'+searchNum);
      Tracula.History.pushState(data, data.summary, '/ticket/'+id);
      if (callback) {
      	callback(data);
      }
    });
	}
}
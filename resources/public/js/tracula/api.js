Tracula.Api = {
	config : {
		scheme : 'http',
		host : 'localhost',
		port : '8080',
		pathPrefix : '/api',
    username : 'mark',
    password : 'O3xVfe14'
	},
	buildUrlForResource: function(res) {
		var c = this.config;
		return c.scheme + '://' + c.host + ':' + c.port + c.pathPrefix + '/' + res;
	},
	get : function(resource, success) {
    var c = this.config;
    $.ajax(this.buildUrlForResource(resource), {
      type : 'GET', 
      success : success,
      dataType : 'json',
      headers : {
        'Authorization' : 'Basic ' + utf8_to_b64(c.username + ':' + c.password)
      }
    });
	}
};

Tracula.Api.Ticket = {
	get : function(id, callback) {
		Tracula.Api.get('tickets/'+id, function(data) {
      Tracula.Event.sendEvent('ticketLoaded', data);
      // history.pushState(data, data.summary, '/ticket/'+searchNum);

      if (callback) {
      	callback(data);
      }
    });
	}
}
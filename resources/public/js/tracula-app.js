Tracula = Ember.Application.create();
Tracula.baseUrl = 'http://localhost:8080';

Tracula.Store = DS.Store.extend({
  revision: 12
});

Tracula.Router.map(function() {
  this.route("search");
});

Tracula.ApplicationRoute = Ember.Route.extend({
  setupController: function(controller) {
    // `controller` is the instance of ApplicationController
    controller.set('title', "Hello world!");
  }
});

Tracula.SearchRoute = Ember.Route.extend({
  model: function() {
    var url = Tracula.baseUrl + '/tickets/60988';
    return Ember.$.getJSON(url).then(function(data) {
      console.log(data);
    });
  }
});

Tracula.ApplicationController = Ember.Controller.extend({
	prevQuery : '',
	searchQuery : '',
  appName: "Tracula - It may suck but it won't bite",
	searchQueryDidChange : function(q) {
		if(this.searchQuery.length < 3) {
			console.log('same or too short');
			return;
		}

		console.log(this.searchQuery);
		this.prevQuery = this.searchQuery;
	}.observes('searchQuery').on('init')
});

Tracula.SearchController = Ember.Controller.extend({
	tickets: [{
		'id' : 2,
		'title' : 'sdfdsf',
		'status' : 'open',
		'assigned_to' : 'mark'
	}]
})
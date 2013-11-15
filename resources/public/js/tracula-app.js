Tracula = Ember.Application.create();

Tracula.Store = DS.Store.extend({
  revision: 12
});

Tracula.ApplicationRoute = Ember.Route.extend({
  setupController: function(controller) {
    // `controller` is the instance of ApplicationController
    controller.set('title', "Hello world!");
  }
});

Tracula.ApplicationController = Ember.Controller.extend({
	searchQuery : '',
  appName: "Tracula - It may suck but it won't bite",
	tickets: [{
		'id' : 2,
		'title' : 'sdfdsf',
		'status' : 'open',
		'assigned_to' : 'mark'
	}],
	searchQueryDidChange : function(q) {
		console.log(this.searchQuery);
	}.observes('searchQuery').on('init')
});

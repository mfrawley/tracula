Tracula = Ember.Application.create();
Tracula.wikiUrl = 'https://svn.jimdo-server.com/trac/wiki/';
Tracula.baseUrl = 'http://'+window.location.host;

Tracula.Store = DS.Store.extend({
    revision: 12
});

DS.RESTAdapter.reopen({
  namespace: 'api'
});

Tracula.Router.map(function() {
    // this.route("ticket");
    // this.resource('tickets');
    this.resource('ticket', function() {
        this.route('create', { path: '/create' });
        this.route('view', { path: '/:ticket_id' });
        
    });
    
});

Tracula.ApplicationRoute = Ember.Route.extend({
    setupController: function(controller) {
    }
});

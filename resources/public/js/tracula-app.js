Tracula = Ember.Application.create();
Tracula.wikiUrl = 'https://svn.jimdo-server.com/trac/wiki/';
Tracula.baseUrl = 'http://'+window.location.host;

Tracula.Store = DS.Store.extend({
    revision: 12
});

Tracula.Ticket = DS.Model.extend({
    id: DS.attr('integer'),
    owner: DS.attr('string'),
    summary: DS.attr('string'),
    isCompleted: DS.attr('boolean')
});

Tracula.Router.map(function() {
    // this.route("ticket");
    // this.resource('tickets');
    this.resource('ticket', { path: '/ticket/:ticket_id' });
});

Tracula.ApplicationRoute = Ember.Route.extend({
    setupController: function(controller) {
        // `controller` is the instance of ApplicationController
        controller.set('title', "Hello world!");
    }
});

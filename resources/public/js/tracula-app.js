Tracula = Ember.Application.create();
Tracula.wikiUrl = 'https://svn.jimdo-server.com/trac/wiki/';
Tracula.baseUrl = 'http://localhost:8080';

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

Tracula.ApplicationController = Ember.Controller.extend({
    prevQuery : '',
    searchQuery : '',
    appName: "Tracula - It may suck but it won't bite",
    searchQueryDidChange : function(q) {
        if(this.searchQuery == '') return;
        if(this.searchQuery.length < 3) {
            console.log('same or too short');
            return;
        }

        console.log(this.searchQuery);
        this.prevQuery = this.searchQuery;
        this.transitionToRoute('ticket', this.searchQuery);

    }.observes('searchQuery').on('init')
});

Tracula.TicketRoute = Ember.Route.extend({
    model: function(params) {
        var url = Tracula.baseUrl + '/tickets/'+params.ticket_id;
        return Ember.$.getJSON(url);
    },
    setupController: function(controller, model) {
        console.log(model);
        controller.set('ticket', model);
    }
});

Tracula.TicketController = Ember.Controller.extend({
    
    replaceWikiLinks : function (s) {
        return s.replace(/\[(.*?)\]/g, function (m, l) { // internal link or image
            var p = l.split(/\:/);
            var linkBase = p[0];
            var linkPage = p[1];

            return '<a href="' + Tracula.wikiUrl + linkPage + '">' + linkPage + '</a>';            
        });
    },
    description : function() {
        var ticket = this.get('ticket');
        var desc = ticket.attributes.description;
        desc = desc.replaceAll('\n', "<br>");
        desc = desc.replaceAll('{{{', "<font style=\"font-family:\'Courier\'\">");
        desc = desc.replaceAll('}}}', "</font>");
        desc = desc.replaceAll('=== ', "<h3>");
        desc = desc.replaceAll(' ===', "</h3>");
        desc = this.replaceWikiLinks(desc);
        
        return desc;
    }.property('ticket'),
    created : function() {
        var ticket = this.get('ticket');
        var created = ticket.time_created['__jsonclass__'][1];
        ticket.created = created;
        this.set('ticket', ticket);
        return created;
    }.property('ticket')
});
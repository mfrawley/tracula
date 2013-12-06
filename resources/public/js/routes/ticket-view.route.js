Tracula.TicketViewRoute = Ember.Route.extend({
    model: function(params) {
        var store = this.get('store');
        // var url = Tracula.baseUrl + '/api/tickets/'+params.ticket_id;
        // var res = Ember.$.getJSON(url);
        return store.find('ticket', params.ticket_id)
    },
    setupController: function(controller, model) {
        if (model.error) {
            var err = {'detail' : model.error, 'heading' : 'Not found'};
            controller.set('error', err)
            controller.set('ticket', null);
        } else {
            controller.set('ticket', model);
            controller.set('error', null);
        }
    }
});
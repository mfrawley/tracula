Tracula.TicketViewRoute = Ember.Route.extend({
    model: function(params) {
        var url = Tracula.baseUrl + '/tickets/'+params.ticket_id;
        return Ember.$.getJSON(url);
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
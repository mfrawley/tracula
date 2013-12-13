Tracula.TicketViewRoute = Ember.Route.extend({
    model: function(params) {
        // var store = this.get('store');
        var url = Tracula.baseUrl + '/api/tickets/'+params.ticket_id;
        return Ember.$.getJSON(url);
    },
    setupController: function(controller, model) {
        var model = Tracula.Ticket.create({item : model})

        if (model.error) {
            var err = {'detail' : model.item.error, 'heading' : 'Not found'};
            controller.set('error', err)
            controller.set('ticket', null);
        } else {
            controller.set('ticket', model);
            controller.set('error', null);
        }
    }
});
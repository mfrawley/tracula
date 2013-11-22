Tracula.TicketCreateController = Ember.Controller.extend({
    description: '',
    summary : '',
    actions: {
        createTicket : function(params) {
            var self = this;
            var url = Tracula.baseUrl + '/tickets';
            return Ember.$.post(url, {'description' : this.description, 'summary' : this.summary},
                function(data) {
                    console.log(data);
                    var ticket_id = data;
                    self.transitionToRoute('ticket.view', ticket_id);
                });
        }
    }
});
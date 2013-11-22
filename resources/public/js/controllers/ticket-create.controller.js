Tracula.TicketCreateController = Ember.Controller.extend({
    description: '',
    summary : '',
    actions: {
        createTicket : function(params) {
            var self = this;
            var url = Tracula.baseUrl + '/tickets';
            return Ember.$.post(url, {'description' : this.description, 'summary' : this.summary},
                function(ticket_id) {
                    if(isNaN(ticket_id)) {
                        throw "createTicket failed"
                    } else {
                        self.transitionToRoute('ticket.view', ticket_id);    
                    }
                    
                });
        }
    }
});
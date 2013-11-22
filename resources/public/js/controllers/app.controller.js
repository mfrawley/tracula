Tracula.ApplicationController = Ember.Controller.extend({
    prevQuery : '',
    searchQuery : '',
    appName: "Tracula",
    // searchQueryDidChange : function(q) {
    //     if(this.searchQuery == '') return;
    //     if(this.searchQuery.length < 3) {
    //         console.log('Search query same or too short');
    //         return;
    //     }

    //     console.log(this.searchQuery);
    //     this.prevQuery = this.searchQuery;
    //     this.transitionToRoute('ticket', this.searchQuery);

    // }.observes('searchQuery').on('init'),
    actions: {
        searchFormSubmit : function() {
            var q = this.searchQuery;
            if(isNaN(q)) {
                alert('Ticket numbers need to be numbers, strange huh?');
            } else {
                this.transitionToRoute('ticket.view', q);
            }
        },
        openCreateTicketAction : function() {
            this.transitionToRoute('ticket.create');
        }
    }
});
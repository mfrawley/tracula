Tracula.TicketController = Ember.Controller.extend({
    needs: ['application'],
    searchQuery: Ember.computed.alias('controllers.application.searchQuery'),
    actions: {
        contentEditableFieldDidChange : function(obj) {
            if(obj['id'] == 'summary') {
                console.log(obj['value']);
            }
        }
    },
    replaceWikiLinks : function (s) {
        return s.replace(/\[(.*?)\]/g, function (m, l) { // internal link or image
            var p = l.split(/\:/);
            var linkBase = p[0];
            var linkPage = p[1];

            return '<a href="' + Tracula.wikiUrl + linkPage + '">' + linkPage + '</a>';
        });
    },
    replaceBold : function(s) {
        return s.replace(/\'\'\'([\w\:\s\t]+)\'\'\'/ig, function(g, l) {
            return "<b>"+l+"</b>";
        });
    },
    description : function() {
        var ticket = this.get('ticket');
        var desc = ticket.attributes.description;
        desc = desc.replaceAll('\n', "<br>");
        // desc = desc.replaceAll('{{{', "<font style=\"font-family:\'Courier\'\">");
        // desc = desc.replaceAll('}}}', "</font>");
        // desc = desc.replaceAll('=== ', "<h3>");
        // desc = desc.replaceAll(' ===', "</h3>");
        // desc = desc.replaceAll('\\[\\[BR\\]\\]', "<br>");
        // desc = this.replaceBold(desc);
        // desc = this.replaceWikiLinks(desc);
        this.set('searchQuery', ticket.id);
        return desc;
    }.property('ticket'),
    created : function() {
        var ticket = this.get('ticket');
        var created = '';
        if (ticket && ticket.time_created) {
            var created = ticket.time_created['__jsonclass__'][1];
            ticket.created = created;
            this.set('ticket', ticket);
        }
        return created;
    }.property('ticket')
});
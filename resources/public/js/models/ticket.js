Tracula.Ticket = DS.Model.extend({
    id: DS.attr('integer'),
    owner: DS.attr('string'),
    summary: DS.attr('string'),
    isCompleted: DS.attr('boolean')
});
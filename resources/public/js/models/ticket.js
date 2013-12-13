Tracula.RestModel = Ember.Object.extend({
	baseUrl : Tracula.baseUrl,
	resource : '',
	save: function() {
		var item = this.get('item');
    Ember.$.ajax({
    	url : this.get('baseUrl') + '/'+resource+'/' + item.id,
    	type : 'PUT',
    	dataType : 'application/json',
    	data : item
    }).done(function(data) {
    	console.log(data);
    }).fail(function(req, textStatus, errorThrown) {
    	console.log(errorThrown);
    });
	}
});

Tracula.Ticket = Tracula.RestModel.extend({
	resource : 'tickets'

});
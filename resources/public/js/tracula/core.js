/** @jsx React.DOM */
//init routes
var routes = new routes();

routes.get("/ticket/:id", function(req) {
  var id = req.params.id;

  React.renderComponent(TicketView( {id: id, title:""} ), document.getElementById('container'));

  Tracula.Api.Ticket.get(id, function(data) {
  	console.log('data')
  });

});

routes.get("/", function(req) {
  console.log('home');
});
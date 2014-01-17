/** @jsx React.DOM */
//init routes
var routes = new routes();

routes.get("/ticket/:id", function(req) {
  var id = req.params.id;

  Tracula.Api.Ticket.get(id, function(data) {
  	console.log('data')
  });

});

routes.get("/", function(req) {
  console.log('home');
});
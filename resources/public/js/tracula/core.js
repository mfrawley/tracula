/** @jsx React.DOM */
//init routes
var routes = new routes();

routes.get("/ticket/:id", function(req) {
  var id = req.params.id;
  Tracula.Components.Ticket();

  Tracula.Api.Ticket.get(id, function(data) {
  	
  });

});

routes.get("/", function(req) {
  console.log('home');
  Tracula.Components.Home();
});
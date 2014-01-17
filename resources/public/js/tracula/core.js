/** @jsx React.DOM */
//init routes
var routes = new routes();

routes.get("/ticket/:id", function(req) {
  var id = req.params.id;
  console.log(id);
  React.renderComponent(TicketView( {id: id, title:""} ), document.getElementById('container'));
});

routes.get("/", function(req) {
  console.log('home');
});
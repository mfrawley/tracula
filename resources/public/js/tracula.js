/** @jsx React.DOM */
Tracula = {};

/**
 @param name [String]
*/
Tracula.createEvent = function(name, detail) {
  return new CustomEvent(
    name,
    {
      detail: detail,
      bubbles: true,
      cancelable: true
    }
  );
};

Tracula.sendEvent = function(name, detail) {
  var evt = Tracula.createEvent(name, detail);
  document.dispatchEvent(evt);
}

Tracula.pushState = function(url) {
  window.location.hash = url;
}

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
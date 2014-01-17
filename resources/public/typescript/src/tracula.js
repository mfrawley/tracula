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

React.renderComponent(<TicketView id="" title="" />, document.getElementById('container'));



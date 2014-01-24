/** @jsx React.DOM */

var Home  = React.createClass( {
  render : function() {
    return (
      <div>
      <h1>Welcome to Castle Tracula!</h1>
      <p className="lead">Tracula will hopefully make your bug tracker easier to use.</p>
      </div>
    );
  }
});

Tracula.Components.Home = function () {
  React.renderComponent(Home( {} ), document.getElementById('container'));
};
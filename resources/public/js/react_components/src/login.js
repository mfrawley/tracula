/** @jsx React.DOM */

var Login  = React.createClass( {
  render : function() {
    return (
      <div>
        <form role="form">
          <div className="form-group">
            <label for="username">Username</label>
            <input type="text" className="form-control" id="username" placeHolder="Enter Username" />
          </div>
          <div className="form-group">
            <label for="password">Password</label>
            <input type="password" className="form-control" id="password" placeHolder="Enter Password" />
          </div>
        </form>
      </div>
    );
  }
});

Tracula.Components.Login = function () {
  React.renderComponent(Login( {} ), document.getElementById('container'));
};
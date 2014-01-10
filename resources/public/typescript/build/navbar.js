/** @jsx React.DOM */

var NavBar  = React.createClass( {displayName: 'NavBar',
  getInitialState : function() {

  },
  render: function() {
    return (
      React.DOM.nav( {className:"navbar navbar-default", role:"navigation"}, 
        React.DOM.form( {name:"search", action:"/search", className:"form-inline", role:"form", id:"search_form"}, 
        React.DOM.div( {className:"form-group"}, 
          React.DOM.label( {className:"sr-only", htmlFor:"ticket_search"}, "Search Trac"),
          React.DOM.input( {className:"form-control input-lg", type:"search", placeholder:"Search", value:""} )
        ),
        React.DOM.button( {type:"submit", className:"btn btn-success", id:"search_btn"}, "Search"),
        React.DOM.button( {type:"submit", className:"btn btn-success", id:"create_ticket_btn"} , "Create")
        )
      )
      )
  }
});

React.renderComponent(NavBar(), document.getElementById('navbar'));
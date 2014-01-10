/** @jsx React.DOM */

var NavBar  = React.createClass( {
  getInitialState : function() {

  },
  render: function() {
    return (
      <nav className="navbar navbar-default" role="navigation">
        <form name="search" action="/search" className="form-inline" role="form" id="search_form">
        <div className="form-group">
          <label className="sr-only" htmlFor="ticket_search">Search Trac</label>
          <input className="form-control input-lg" type="search" placeholder="Search" value="" />
        </div>
        <button type="submit" className="btn btn-success" id="search_btn">Search</button>
        <button type="submit" className="btn btn-success" id="create_ticket_btn" >Create</button>
        </form>
      </nav>
      )
  }
});

React.renderComponent(NavBar(), document.getElementById('navbar'));
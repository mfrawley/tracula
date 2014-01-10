/** @jsx React.DOM */

var NavBar  = React.createClass( {
  handleForm : function(e) {
    e.preventDefault();
    var search = this.state.value;
    var searchNum = parseInt(search, 10);

    if(isNaN(searchNum)) {
      console.log('Numbers only at the moment, thanks');
    } else {
      $.get('http://localhost:8080/api/tickets/'+search, function(data) {
        console.log(data);
        this.setState({data: data});
      }.bind(this));
    }
  },
  handleSearchChange : function(e) {
    this.setState({value: e.target.value});
  },
  render: function() {
    return (
      <nav className="navbar navbar-default" role="navigation">
        <form name="search" action="/search" className="form-inline" role="form" id="search_form" onSubmit={this.handleForm}>
        <div className="form-group">
          <label className="sr-only" htmlFor="ticket_search">Search Trac</label>
          <input className="form-control input-lg" type="search" placeholder="Search" onChange={this.handleSearchChange} />
        </div>
        <button type="submit" className="btn btn-success" id="search_btn">Search</button>
        <button type="submit" className="btn btn-success" id="create_ticket_btn" >Create</button>
        </form>
      </nav>
      )
  }
});

React.renderComponent(NavBar(), document.getElementById('navbar'));
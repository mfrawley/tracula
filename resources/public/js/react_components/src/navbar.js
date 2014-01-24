/** @jsx React.DOM */
var NavBar  = React.createClass( {
  getInitialState : function() {
    return {
      search: ''
    }
  },
  componentDidMount : function() {
    var that = this;
    Tracula.Event.addListener('ticketLoaded', function(evt) {
      var data = evt.detail;
      that.setState({'search' : data.id});
    });
  },
  handleForm : function(e) {
    e.preventDefault();
    var search = this.state.search;
    var searchNum = parseInt(search, 10);

    if(isNaN(searchNum)) {
      console.log('Numbers only at the moment, thanks');
    } else {
      Tracula.Api.Ticket.get(searchNum, function(data) {
        Tracula.History.pushState(data, data.summary, '/ticket/'+searchNum);
      });
    }
  },
  handleSearchChange : function(e) {
    this.setState({search: e.target.value});
  },
  createTicketAction : function() {
    console.log('createTicketAction');
  },
  render: function() {
    return (
      <header className="navbar bs-docs-nav" role="banner">
      <div className="navbar-header">
      <a href="/" className="navbar-brand">Home</a>
      </div>
      <nav className="collapse navbar-collapse bs-navbar-collapse" role="navigation">
        
        <form name="search" action="/search" className="form-inline" role="form" id="search_form" onSubmit={this.handleForm}>
        <div className="form-group">
          <label className="sr-only" htmlFor="ticket_search">Search Trac</label>
          <input className="form-control input-lg" type="search" placeholder="Search" onChange={this.handleSearchChange} value={this.state.search} />
        </div>
        <button type="submit" className="btn btn-success" id="search_btn">Search</button>
        <button type="submit" className="btn btn-success form-inline" id="create_ticket_btn" onClick={this.createTicketAction}>Create</button>
        </form>
      </nav>
      </header>
      )
  }
});


React.renderComponent(NavBar(), document.getElementById('navbar'));
/** @jsx React.DOM */
// var dom = React.DOM;

var TicketViewTitle = React.createClass({
  getInitialState: function() {
    return {
      title: '',
      created : '',
      id : ''
    };
  },
  render: function () {
    var p = this.props;
    return (
    <div className="row">
      <div className="col-md-8">
        <div className="page-header">
          <h1 id="summary" contentEditable="true">{p.summary} <small>{p.id}</small></h1>
          <small>Created:{p.time_created}</small>
        </div>
      </div>
    </div>
    )
  }
 });

var TicketViewInfo = React.createClass( {

  getInitialState: function() {
    return {
      type: '',
      priority : '',
      status : '',
      owner : '',
      reporter : '',
      component : ''
    }
  },
  render: function() {
    var s = this.props;
    return (
      <div>
        <div className="row">
          <div className="col-md-1">Type: {s.type}</div>
          <div className="col-md-1">Priority: {s.priority}</div>
          <div className="col-md-1">Status: {s.status}</div>
          <div className="col-md-1">Owner: {s.owner}</div>
          <div className="col-md-1">Reported by: {s.reporter}</div>
          <div className="col-md-1">Component: {s.component}</div>
        </div>
        <div className="row">
          <label htmlFor="description">Description</label>
          <div className="col-md-12" id="description" contentEditable="false">
          {s.description}
          </div>
        </div>
      </div>
      );
  }
});

var TicketView  = React.createClass( {
  componentDidMount : function() {
    var that = this;
    document.addEventListener('ticketLoaded', function(evt) {
      var data = evt.detail;
      console.log('data');
      console.log(data);
      that.setState(data);
    });
  },
  render: function() {
    if(this.state) {
      s = this.state;
      return React.DOM.div(null,
        TicketViewTitle(s),
        TicketViewInfo(s)
      );
    } else {
      return (<div></div>)
    }
  }
});

//render even without data
React.renderComponent(TicketView( {id: '', title:""} ), document.getElementById('container'));
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
    var s = this.state;
    return (
    <div className="row">
      <div className="col-md-8">
        <div className="page-header">
          <h1 id="summary" contentEditable="true">{this.props.title} <small>{this.props.id}</small></h1>
          <small>Created:{s.created}</small>
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
  componentWillMount : function() {
    
    if(this.state.data) {
      console.log(this.state.data);
    } else {
      console.log('no data');
    }
  },
  render: function() {
    var s = this.props;
    return (
      <div className="row">
        <div className="col-md-1">Type: {s.type}</div>
        <div className="col-md-1">Priority: {s.priority}</div>
        <div className="col-md-1">Status: {s.status}</div>
        <div className="col-md-1">Owner: {s.owner}</div>
        <div className="col-md-1">Reported by: {s.reporter}</div>
        <div className="col-md-1">Component: {s.component}</div>
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
    var s = {};
    if(this.state) {
       s = this.state;
    }
    return React.DOM.div(null, 
        TicketViewTitle(s),
        TicketViewInfo(s)
      );
  }
});
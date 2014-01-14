/** @jsx React.DOM */
// var dom = React.DOM;

var TicketViewTitle = React.createClass({
  getInitialState: function() {
    return {
      title: 'Sample title',
      created : 'sometime in the past',
      id : '23434'
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
  componentDidMount : function() {
    console.log('componentDidMount');
    document.addEventListener('ticketLoaded', function(data) {
      console.log(data);
      this.setState(data);
    });
  },
  render: function() {
    var s = this.state;
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
  render: function() {
    return (
      <div>
        <TicketViewTitle id={this.props.id} title={this.props.title}/>
        <TicketViewInfo />
      </div>
      )
  }
});
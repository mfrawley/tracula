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
          <h1 id="summary" contentEditable="true"> <small>{s.id}</small>{s.title}</h1>
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
        <TicketViewTitle />
        <TicketViewInfo />
      </div>
      )
  }
});

React.renderComponent(TicketView(), document.getElementById('container'));
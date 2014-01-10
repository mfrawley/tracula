/** @jsx React.DOM */
// var dom = React.DOM;

var TicketViewTitle = React.createClass({displayName: 'TicketViewTitle',
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
		React.DOM.div( {className:"row"}, 
      React.DOM.div( {className:"col-md-8"}, 
        React.DOM.div( {className:"page-header"}, 
          React.DOM.h1( {id:"summary", contentEditable:"true"},  React.DOM.small(null, s.id),s.title),
          React.DOM.small(null, "Created:",s.created)
        )
      )
    )
 		)
	}
 });

var TicketViewInfo = React.createClass( {displayName: 'TicketViewInfo',

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
      React.DOM.div( {className:"row"}, 
        React.DOM.div( {className:"col-md-1"}, "Type: ", s.type),
        React.DOM.div( {className:"col-md-1"}, "Priority: ", s.priority),
        React.DOM.div( {className:"col-md-1"}, "Status: ", s.status),
        React.DOM.div( {className:"col-md-1"}, "Owner: ", s.owner),
        React.DOM.div( {className:"col-md-1"}, "Reported by: ", s.reporter),
        React.DOM.div( {className:"col-md-1"}, "Component: ", s.component)
      )
      );
  }
});

var TicketView  = React.createClass( {displayName: 'TicketView',
  render: function() {
    return (
      React.DOM.div(null, 
        TicketViewTitle(null ),
        TicketViewInfo(null )
      )
      )
  }
});

React.renderComponent(TicketView(), document.getElementById('container'));
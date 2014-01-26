/** @jsx d */
// var dom = d;

var TicketTitle = React.createClass({
  render: function () {
    var p = this.props, d = React.DOM, div = d.div, small = d.small;
    return (
      div( {className:"row ticket-title"}, 
        div( {className:"col-md-9"}, 
          div( {className:"page-header"}, 
            d.h1( {id:"summary"}, d.span( {contentEditable:"true", id:"summary_field"}, p.summary), d.small(null, p.id)),
            d.small(null, "Created:",p.time_created)
          )
        ),
        div( {className:"col-md-3"}, 
          TicketActions( {updated:p.updated, ticketReverted:p.ticketReverted} )
        )
      )
    )
  }
 });
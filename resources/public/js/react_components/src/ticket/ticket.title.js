/** @jsx React.DOM */
// var dom = React.DOM;

var TicketTitle = React.createClass({
  render: function () {
    var p = this.props;
    return (
    <div className="row ticket-title">
      <div className="col-md-9">
        <div className="page-header">
          <h1 id="summary"><span contentEditable="true" id="summary_field">{p.summary}</span> <small>{p.id}</small></h1>
          <small>Created:{p.time_created}</small>
        </div>
      </div>
      <div className="col-md-3">
        <TicketActions updated={p.updated} ticketReverted={p.ticketReverted} />
      </div>
    </div>
    )
  }
 });
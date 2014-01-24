/** @jsx React.DOM */
var TicketInfo = React.createClass( {

  getInitialState: function() {
    return {
    }
  },
  render: function() {
    var p = this.props;
    if (!p) {
      return (
        <div></div>
      );
    } else {
      return (
      <div>
        <div className="row">
          <div className="col-md-2">Type:</div>
          <div className="col-md-1">Priority:</div>
          <div className="col-md-1">Status:</div>
          <div className="col-md-2">Owner:</div>
          <div className="col-md-2">Reported by:</div>
          <div className="col-md-2">Component:</div>
        </div>

        <div className="row">
          <div className="col-md-2">{p.type}</div>
          <div className="col-md-1">{p.priority}</div>
          <div className="col-md-1">{p.status}</div>
          <div className="col-md-2">{p.owner}</div>
          <div className="col-md-2">{p.reporter}</div>
          <div className="col-md-2">{p.component}</div>
        </div>
        
        <div className="row">
          <label htmlFor="description">Description</label>
          <pre className="col-md-12" id="description" contentEditable="true" onInput={p.ticketChanged}>
            {p.description}
          </pre>
        </div>
      </div>
      );  
    }
    
  }
});
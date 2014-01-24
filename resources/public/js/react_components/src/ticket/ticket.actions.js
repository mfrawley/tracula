/** @jsx React.DOM */
var TicketActions = React.createClass( {
  cancelAction : function(e) {
    console.log('cancelAction');
    this.props.ticketReverted();
  },
  render : function() {
    if(this.props.updated) {
      return (
        <div >
        <button type="button" className="btn btn-primary" onClick={this.cancelAction}>Cancel</button>
        <button type="button" className="btn btn-primary">Save</button>
        </div>
      )
    } else {
      return (
      <div >
      </div>
      )  
    }
    
  }
});
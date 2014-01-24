/** @jsx React.DOM */
var TicketActions = React.createClass( {
  cancelAction : function() {
    this.setState({updated : false});
  },
  render : function() {
    if(this.props.updated) {
      return (
        <div className="row" >
        <button type="button" class="btn btn-primary" onClick={this.cancelAction}>Cancel</button>
        <button type="button" class="btn btn-primary">Save</button>
        </div>
      )
    } else {
      return (
      <div className="row">
      </div>
      )  
    }
    
  }
});
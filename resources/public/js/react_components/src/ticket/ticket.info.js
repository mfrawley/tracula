/** @jsx React.DOM */
var TicketInfo = React.createClass( {

  getInitialState: function() {
    return {
      updated : false
    }
  },
  inputChanged: function(e) {
    console.log('inputChanged');
    console.log(e);
    if (!e.metaKey) {
      return this.setState({updated: true});
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
      <TicketActions updated={this.state.updated} />
        <div className="row">
          <div className="col-md-1">Type: {p.type}</div>
          <div className="col-md-1">Priority: {p.priority}</div>
          <div className="col-md-1">Status: {p.status}</div>
          <div className="col-md-1">Owner: {p.owner}</div>
          <div className="col-md-2">Reported by: {p.reporter}</div>
          <div className="col-md-2">Component: {p.component}</div>
        </div>
        
        <div className="row">
          <label htmlFor="description">Description</label>
          <pre className="col-md-12" id="description" contentEditable="true" onInput={this.inputChanged}>
            {p.description}
          </pre>
        </div>
      </div>
      );  
    }
    
  }
});
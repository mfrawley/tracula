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
    var p = this.props;
    return (
    <div className="row">
      <div className="col-md-12">
        <div className="page-header">
          <h1 id="summary" contentEditable="true">{p.summary} <small>{p.id}</small></h1>
          <small>Created:{p.time_created}</small>
        </div>
      </div>
    </div>
    )
  }
 });

var TicketViewActions = React.createClass( {
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

var TicketViewInfo = React.createClass( {

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
      <TicketViewActions updated={this.state.updated} />
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

var TicketView  = React.createClass( {
  componentDidMount : function() {
    var that = this;
    Tracula.Event.addListener('ticketLoaded', function(evt) {
      var data = evt.detail;
      console.log('data');
      console.log(data);
      that.setState(data);
    });
  },
  render: function() {
    if(this.state) {
      s = this.state;
      return React.DOM.div(null,
        TicketViewTitle(s),
        TicketViewInfo(s)
      );
    } else {
      return (<div></div>)
    }
  }
});

//render even without data
React.renderComponent(TicketView( {id: '', title:""} ), document.getElementById('container'));
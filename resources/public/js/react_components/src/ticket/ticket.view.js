/** @jsx React.DOM */
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
        TicketTitle(s),
        TicketInfo(s)
      );
    } else {
      return (<div></div>)
    }
  }
});
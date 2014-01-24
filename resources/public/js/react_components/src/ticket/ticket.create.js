/** @jsx React.DOM */
var TicketCreate = React.createClass({
  handleFormSubmit : function() {
    console.log('handleFormSubmit');
  },
  render : function() {
    return (
      <div>
      <h1>Create Ticket</h1>
        <form name="create_ticket" id="create_ticket_form" onSubmit={this.handleFormSubmit}>
          <div className="form-group">
            <label for="summary">Title/Summary</label>
            <input defaultValue="" name="summary" className="form-control"  id="summary" />
          </div>
          <div className="form-group">
            <label for="description">Description</label>
            <textarea name="description" rows="3" className="form-control" />
          </div>
          <button type="submit" className="btn btn-primary">Save</button>
        </form>
      </div>
      )
  }
});
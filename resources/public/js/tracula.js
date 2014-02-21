///<reference path="tracula-ns.d.ts" />
//init routes
var routes = new routes();

routes.get("/ticket/create", function (req) {
    Tracula.Components.CreateTicket();
});

routes.get("/ticket/:id", function (req) {
    var id = req.params.id;
    Tracula.Components.Ticket();

    Tracula.Api.Ticket.get(id, function (data) {
    });
});

routes.get("/", function (req) {
	if(Tracula.Session.loggedIn()) {
		Tracula.Components.Home();
	} else {
		window.location = '/login';
	}

});

routes.get("/login", function (req) {
    Tracula.Components.Login();
});

///<reference path="tracula-ns.d.ts" />
//init routes
var routes = new routes();

routes.get("/ticket/:id", function (req) {
    var id = req.params.id;
    Tracula.Components.Ticket();

    Tracula.Api.Ticket.get(id, function (data) {
    });
});

routes.get("/", function (req) {
    Tracula.Components.Home();
});

routes.get("/login", function (req) {
    Tracula.Components.Login();
});
//# sourceMappingURL=tracula.js.map

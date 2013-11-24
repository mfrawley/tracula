(ns tracula.core
	(:use [tracula.config])
	(:use [tracula.utils])
	)

(require '[ring.adapter.jetty :as jetty])
(require '[ring.util.response :as resp])
(require '[compojure.core :as compcore])
(require '[compojure.route :as route])
(require '[compojure.handler :as handler])

; (def url "https://svn.jimdo-server.com/trac/login/jsonrpc")
; (def user-creds ["mark" "O3xVfe14"])

; ;make things easier for testing
; (def example-ticket 60988)

(defn list-methods []
	(api-req "system.listMethods" []))

(defn get-method-help [method]
	(api-req "system.methodHelp" [method]))

(defn hashify-changelog-entry [changelog-arr]
	(let [[dtime, author, field, oldvalue, newvalue, permanent ] changelog-arr]
	{:time dtime :author author :field field :oldvalue oldvalue :newvalue newvalue :permanent permanent}))

;;ticket methods
(defn get-ticket [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) {:id (res 0) :time_created (res 1) :time_changed (res 2) :attributes (res 3)}
			{:error "Ticket not found."})))

(defn get-ticket-actions [ticketno]
	(api-req "ticket.getActions" [ticketno]))

(defn get-ticket-changelog [ticketno]
	 (map hashify-changelog-entry (api-req "ticket.changeLog" [ticketno])))

(defn update-ticket [ticketno commentstr action notify]
	(let [notify false action "done" attrs {:_ts (get-current-timestamp-str) :action "done"}]
	(api-req "ticket.update" [ticketno commentstr attrs])))

(defn create-ticket [summary description]
	(let [notify false attributes {}]
	(api-req "ticket.create" [summary description attributes notify])))

(defn delete-ticket [ticketno]
	(api-req "ticket.delete" [ticketno]))

(defn get-ticket-fields []
	(api-req "ticket.getTicketFields" []))

(defn get-recent []
	(api-req "ticket.getRecentChanges" [(format-ts-for-json (get-current-timestamp-str))]))


; too new
; (defn query-tickets [query]
; 	(api-req " ticket.query" ["status!=closed"]))

(defn rest-create-ticket [params]
	(jsonify (create-ticket (params :summary) (params :description))))

(defn rest-echo [params]
	(jsonify params))

(compcore/defroutes approutes
	;;;;;;;;;;;;;;;;;;;;;;;;
	;todo, put API docs here

	;;;;;;;;;;;;;;;;;;;;;;
	; help/utility methods
	(compcore/POST "/api/echo" {params :params} (rest-echo params))
	(compcore/GET "/api/methods" [] (jsonify (list-methods)))
	(compcore/GET "/api/recent" [] (jsonify (get-recent)))
	(compcore/GET "/api/help/:method" [method] (get-method-help method))
	;;;;;;;;;;;;;;
	;ticket routes
	(compcore/GET "/api/tickets/fields" [] (jsonify (get-ticket-fields)))
	(compcore/GET "/api/tickets/:id" [id] (jsonify (get-ticket (read-string id))))
	(compcore/GET "/api/tickets/:id/actions" [id] (jsonify (get-ticket-actions (read-string id))))
	(compcore/GET "/api/tickets/:id/changelog" [id] (jsonify (get-ticket-changelog (read-string id))))

	(compcore/POST "/api/tickets" {params :params} (rest-create-ticket params))
	(compcore/DELETE "/api/tickets/:id" [id] (jsonify (delete-ticket (read-string id))))

	; (compcore/PUT "/tickets/:id" [id] (jsonify (update-ticket (read-string id) commentstr action notify)))
	(compcore/GET "/" [] (resp/resource-response "index.html" {:root "public"}))
	(route/resources "/static" {:root "public"})
	(route/not-found "Not Found")
 )

(defn -main []
	(defonce server (jetty/run-jetty (handler/site approutes) {:port 8080 :join? false}))
	)

(defn restart-server []
	(.stop server)
	(.start server))

(defn stop-server []
	(.stop server))

(defn start-server []
	(.start server))
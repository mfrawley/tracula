(ns tracula.core)
(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])
(require '[clj-time.core :as cltime])
(require '[clj-time.format :as tformat])
(require 'ring.adapter.jetty)
(require '[compojure.core :as compcore])
(require '[compojure.handler :as handler])

; (:require [compojure.route :as route]
;           [compojure.handler :as handler]))

; (use 'ring.middleware.params)

(def url "https://svn.jimdo-server.com/trac/login/jsonrpc")
(def user-creds ["mark" "O3xVfe14"])

;make things easier for testing
(def example-ticket 60988)

(defn parse-result [body]
	(let [err (get body "error") result (get body "result")]
	(cond
		err err
		:else result)))

(defn parse-body-from-response [response]
	(let [body (response :body) ]
		(json/read-str body)
	))

(defn parse-response [response]
	(parse-result (parse-body-from-response response)))

(defn api-req [method params]
	(let [req-body (json/write-str {:method method :params params}) ]
	(println req-body)

	(parse-response (client/post url
	  {:basic-auth user-creds
	   :body req-body
	   :content-type :json
	   :socket-timeout 1000  ;; in milliseconds
	   :conn-timeout 1000    ;; in milliseconds
	   :accept :json} ))))

(defn get-current-timestamp-str []
	(tformat/unparse (tformat/formatters :date-hour-minute-second) (cltime/now))
	)
(defn format-ts-for-json [datetime-str]
	{"__jsonclass__" ["datetime" datetime-str] })

(defn jsonify [value] (json/write-str value))

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
  (compcore/GET "/" [] "<h1>Hello World</h1>")
  ;;;;;;;;;;;;;;;;;;;;;;
  ; help/utility methods
  (compcore/POST "/echo" {params :params} (rest-echo params))
  (compcore/GET "/methods" [] (jsonify (list-methods)))
  (compcore/GET "/recent" [] (jsonify (get-recent)))
  (compcore/GET "/help/:method" [method] (get-method-help method))
  ;;;;;;;;;;;;;;
  ;ticket routes
  (compcore/GET "/tickets/fields" [] (jsonify (get-ticket-fields)))
  (compcore/GET "/tickets/:id" [id] (jsonify (get-ticket (read-string id))))
  (compcore/GET "/tickets/:id/actions" [id] (jsonify (get-ticket-actions (read-string id))))

  (compcore/POST "/tickets" {params :params} (rest-create-ticket params))
    (compcore/DELETE "/tickets/:id" [id] (jsonify (delete-ticket (read-string id))))


  ; (compcore/PUT "/tickets/:id" [id] (jsonify (update-ticket (read-string id) commentstr action notify)))
  )

(defn -main []
	(defonce server (ring.adapter.jetty/run-jetty (handler/site approutes) {:port 8080 :join? false}))
	)
(ns tracula.core)
(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])

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

	(parse-response (client/post url
	  {:basic-auth user-creds
	   :body req-body
	   :content-type :json
	   :socket-timeout 1000  ;; in milliseconds
	   :conn-timeout 1000    ;; in milliseconds
	   :accept :json} ))))

(defn list-methods []
	(api-req "system.listMethods" []))

(defn get-method-help [method]
	(api-req "system.methodHelp" [method]))

(defn get-ticket [ticketno]
	(let [[id, time_created, time_changed, attributes] (api-req "ticket.get" [ticketno])]
	{:id id :time_created time_created :time_changed time_changed :attributes attributes}))

(defn update-ticket [ticketno commentstr attrs notify]
	(api-req "ticket.update" [ticketno])
	)

(defn get-ticket-actions [ticketno]
	(api-req "ticket.getActions" [ticketno]))

(defn -main []
	(println (get-ticket example-ticket))
	)


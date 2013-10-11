(ns tracula.core)
(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])

(def url "https://svn.jimdo-server.com/trac/login/jsonrpc")

(def user-creds ["mark" "O3xVfe14"])

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

(defn get-ticket [ticketno]
	(api-req "ticket.get" [ticketno]))

(defn get-ticket-actions [ticketno]
	(api-req "ticket.getActions" [ticketno]))

(defn -main []
	(println (list-methods))
	)


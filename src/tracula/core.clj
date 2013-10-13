(ns tracula.core)
(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])
(require '[clj-time.core :as cltime])
(require '[clj-time.format :as tformat])

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

(defn get-ticket-actions [ticketno]
	(api-req "ticket.getActions" [ticketno]))

(defn hashify-changelog-entry [changelog-arr]
	(let [[dtime, author, field, oldvalue, newvalue, permanent ] changelog-arr]
	{:time dtime :author author :field field :oldvalue oldvalue :newvalue newvalue :permanent permanent}))

(defn get-ticket-changelog [ticketno]
	 (map hashify-changelog-entry (api-req "ticket.changeLog" [ticketno])))

(defn get-ticket [ticketno]
	(let [[id, time_created, time_changed, attributes] (api-req "ticket.get" [ticketno])]
	{:id id :time_created time_created :time_changed time_changed :attributes attributes}))

(defn update-ticket [ticketno commentstr action notify]
	(let [notify false action "done" attrs {:_ts (get-current-timestamp-str) :action "done"}]
	(api-req "ticket.update" [ticketno commentstr attrs])))

(defn create-ticket [summary description attributes]
	(let [notify false]
	(api-req "ticket.create" [summary description attributes notify])))

(defn get-current-timestamp-str []
	(tformat/unparse (tformat/formatters :date-hour-minute-second) (cltime/now))
	)



(defn -main []
	(println (get-ticket example-ticket))
	)


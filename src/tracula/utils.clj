(ns tracula.utils)
(require '[tracula.config :as config])
(require '[clojure.data.json :as json])
(require '[clj-http.client :as client])
(require '[clj-time.format :as tformat])
(require '[clj-time.core :as cltime])

(defn format-ts-for-json [datetime-str]
	{"__jsonclass__" ["datetime" datetime-str] })

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

	(parse-response (client/post config/url
	  {:basic-auth config/user-creds
	   :body req-body
	   :content-type :json
	   :socket-timeout config/socket-timeout  ;; in milliseconds
	   :conn-timeout config/conn-timeout    ;; in milliseconds
	   :accept :json} ))))

(defn get-current-timestamp-str []
	(tformat/unparse (tformat/formatters :date-hour-minute-second) (cltime/now)))

(defn json-datetime-to-str [dt-map]
	((dt-map "__jsonclass__") 1))

(defn jsonify [value] (json/write-str value))
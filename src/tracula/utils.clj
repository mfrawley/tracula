(ns tracula.utils)
(require '[tracula.config :as config])
(require '[clojure.data.json :as json])
(require '[clj-http.client :as client])
(require '[clj-time.format :as tformat])
(require '[clj-time.core :as cltime])


(defn camelize [^String var-name]
  (clojure.string/replace var-name #"_(\w)"
                          #(clojure.string/upper-case (second %1))))

(defn camelize-keys [hash-map]
	(into {}  (for [[k v] hash-map] [(camelize k) v])))

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

(defn jsonify [value]
	(camelize-keys (json/write-str value)))

(defn hashify-changelog-entry [changelog-arr]
	(let [[dtime, author, field, oldvalue, newvalue, permanent] changelog-arr]
	{:time dtime :author author :field field :oldvalue oldvalue :newvalue newvalue :permanent permanent}))

(defn ticket-result-to-hash [res]
	{:id (res 0) :time_created (json-datetime-to-str (res 1)) :time_changed (json-datetime-to-str (res 2)) :attributes (res 3)})

(defn ticket-attributes [ticket-hash]
	(:attributes ticket-hash))

(defn flatten-ticket-result [res]
	(let [flat {:id (res 0) :time_created (json-datetime-to-str (res 1)) :time_changed (json-datetime-to-str (res 2)) }]
		(conj flat (res 3))))

(defn parse-action-input-fields [fields]
	{:name (fields 0) :value (fields 1) :options (fields 2)})

(defn parse-actions [fields]
	(let [res {:action (fields 0) :label (fields 1) :hints (fields 2)}]
		(if (fields 3) (conj res {:input-fields (map parse-action-input-fields (fields 3))})) ))


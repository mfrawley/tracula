(ns tracula.core)
(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])

(def url "https://svn.jimdo-server.com/trac/login/jsonrpc")

(def user-creds ["mark" "O3xVfe14"])

(defn api-req [method params]
	(let [body (json/write-str {:method method :params params})]

	(println body)

	(client/post url
  {:basic-auth user-creds
   :body body
   :content-type :json
   :socket-timeout 1000  ;; in milliseconds
   :conn-timeout 1000    ;; in milliseconds
   :accept :json})

	))

(defn -main []
	(println (api-req "ticket.get" [60988])))


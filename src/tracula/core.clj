(ns tracula.core
	(:use [tracula.routes]))

(require '[tracula.config :as config])
(require '[ring.adapter.jetty :as jetty])
(require '[compojure.handler :as handler])

; too new
; (defn query-tickets [query]
; 	(api-req " ticket.query" ["status!=closed"]))


(defn -main []
	(defonce server (jetty/run-jetty (handler/site approutes) {
		:port config/port
		:join? false
		; :ssl? true
		; :keystore "keystore/server.key"
		; :key-password "kludger"
		; :client-auth :want
		}))
	)

(defn restart-server []
	(.stop server)
	(.start server))

(defn stop-server []
	(.stop server))

(defn start-server []
	(.start server))
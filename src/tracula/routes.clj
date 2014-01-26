(ns tracula.routes
	(:use [tracula.utils])
	(:use [tracula.calls]))

(require '[compojure.core :as compcore])
(require '[ring.util.response :as resp])
(require '[compojure.route :as route])
(require '[tracula.config :as config])

; All API calls are quite similar and need a common structure to keep
; things sane
(defmacro wrap-api [headers call & args]
	`(let [auth-header# (get-auth ~headers)]
		(jsonify (~call auth-header# ~@args))))

;static routes
(defmacro index-page []
	`(resp/resource-response "index.html" {:root "public"}))

(compcore/defroutes approutes
	;;;;;;;;;;;;;;;;;;;;;;;;
	;todo, put API docs here

	;;;;;;;;;;;;;;;;;;;;;;
	; help/utility methods
	; (compcore/POST "/api/echo" {params :params} (rest-echo params))
	(compcore/GET "/api/methods" {headers :headers} 
		(wrap-api headers list-methods))

	(compcore/GET "/api/recent" {headers :headers} 
		(wrap-api headers get-recent))

	(compcore/GET "/api/help/:method" {params :params headers :headers} 
		(wrap-api headers get-method-help (params :method)))

	;;;;;;;;;;;;;;
	;ticket routes
	(compcore/GET "/api/tickets/fields" {headers :headers} 
		(wrap-api headers get-ticket-fields))

	(compcore/GET "/api/tickets/components" {headers :headers} 
		(wrap-api headers get-components))

	(compcore/GET "/api/tickets/:id" {params :params headers :headers}
		(wrap-api headers get-ticket (get-id params)))

	(compcore/GET "/api/tickets/:id/raw" {params :params headers :headers} 
		(wrap-api headers get-ticket (get-id params)))
	
	(compcore/GET "/api/tickets/:id/actions" {params :params headers :headers} 
		(wrap-api headers get-ticket-actions (get-id params)))

	(compcore/GET "/api/tickets/:id/changelog" {params :params headers :headers}
		(wrap-api headers get-ticket-changelog (get-id params)))

	(compcore/POST "/api/tickets" {params :params headers :headers} 
		(wrap-api headers create-ticket (params :summary) (params :description)))

	(compcore/DELETE "/api/tickets/:id" {params :params headers :headers} 
	 (wrap-api headers delete-ticket (get-id)))

	; (compcore/PUT "/tickets/:id" [id] (jsonify (update-ticket (read-string id) commentstr action notify)))

	(compcore/GET "/" [] (index-page))
	(compcore/GET "/ticket/:id" [] (index-page))
	(compcore/GET "/login" [] (index-page))

	(route/resources "/static" {:root "public"})
	(route/not-found "Not Found")

 )
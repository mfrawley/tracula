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
	(let [auth-header (get-auth headers)] 
		`(jsonify (~call ~auth-header ~@args))))

(compcore/defroutes approutes
	;;;;;;;;;;;;;;;;;;;;;;;;
	;todo, put API docs here

	;;;;;;;;;;;;;;;;;;;;;;
	; help/utility methods
	; (compcore/POST "/api/echo" {params :params} (rest-echo params))
	(compcore/GET "/api/methods" {headers :headers} (wrap-api headers list-methods))
	(compcore/GET "/api/recent" {headers :headers} (wrap-api headers get-recent))

	(compcore/GET "/api/help/:method" {params :params headers :headers} 
		(wrap-api headers get-method-help (params :method)))
	;;;;;;;;;;;;;;
	;ticket routes
	(compcore/GET "/api/tickets/fields" {headers :headers} (wrap-api headers get-ticket-fields))
	(compcore/GET "/api/tickets/components" {headers :headers} (wrap-api headers get-components))

	(compcore/GET "/api/tickets/:id" {params :params headers :headers}
		(let [auth-header (get-auth headers) id (get-id params)]
			(jsonify (get-ticket auth-header id))))

	(compcore/GET "/api/tickets/:id/raw" [id] (jsonify (get-ticket (read-string id))))
	(compcore/GET "/api/tickets/:id/actions" [id] (jsonify (get-ticket-actions (read-string id))))
	(compcore/GET "/api/tickets/:id/changelog" [id] (jsonify (get-ticket-changelog (read-string id))))

	(compcore/POST "/api/tickets" {params :params} (jsonify (create-ticket (params :summary) (params :description))))
	(compcore/DELETE "/api/tickets/:id" [id] (jsonify (delete-ticket (read-string id))))

	; (compcore/PUT "/tickets/:id" [id] (jsonify (update-ticket (read-string id) commentstr action notify)))

	(compcore/GET "/" [] (resp/resource-response "index.html" {:root "public"}))
	(compcore/GET "/ticket/:id" [] (resp/resource-response "index.html" {:root "public"}))

	(route/resources "/static" {:root "public"})
	(route/not-found "Not Found")

 )
(ns tracula.routes
	(:use [tracula.utils])
	(:use [tracula.calls]))

(require '[compojure.core :as compcore])
(require '[ring.util.response :as resp])
(require '[compojure.route :as route])
(compcore/defroutes approutes
	;;;;;;;;;;;;;;;;;;;;;;;;
	;todo, put API docs here

	;;;;;;;;;;;;;;;;;;;;;;
	; help/utility methods
	; (compcore/POST "/api/echo" {params :params} (rest-echo params))
	(compcore/GET "/api/methods" [] (jsonify (list-methods)))
	(compcore/GET "/api/recent" [] (jsonify (get-recent)))
	(compcore/GET "/api/help/:method" [method] (get-method-help method))
	;;;;;;;;;;;;;;
	;ticket routes
	(compcore/GET "/api/tickets/fields" [] (jsonify (get-ticket-fields)))
	(compcore/GET "/api/tickets/components" [] (jsonify (get-components)))
	(compcore/GET "/api/tickets/:id" [id] (jsonify (get-ticket (read-string id))))
	(compcore/GET "/api/tickets/:id/raw" [id] (jsonify (get-ticket (read-string id))))
	(compcore/GET "/api/tickets/:id/actions" [id] (jsonify (get-ticket-actions (read-string id))))
	(compcore/GET "/api/tickets/:id/changelog" [id] (jsonify (get-ticket-changelog (read-string id))))

	(compcore/POST "/api/tickets" {params :params} (jsonify (create-ticket (params :summary) (params :description))))
	(compcore/DELETE "/api/tickets/:id" [id] (jsonify (delete-ticket (read-string id))))

	; (compcore/PUT "/tickets/:id" [id] (jsonify (update-ticket (read-string id) commentstr action notify)))
	(compcore/GET "/" [] (resp/resource-response "index.html" {:root "public"}))
	(route/resources "/static" {:root "public"})
	(route/not-found "Not Found")
 )
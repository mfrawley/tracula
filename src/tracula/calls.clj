(ns tracula.calls
	(:use [tracula.utils]))

;read only
;System lists
(defn get-ticket-fields [auth-header]
	(api-req auth-header "ticket.getTicketFields" []))

(defn get-components [auth-header]
	(api-req auth-header "ticket.component.getAll" []))

(defn get-milestones [auth-header]
	(api-req auth-header "ticket.milestone.getAll" []))

(defn get-priorities [auth-header]
	(api-req auth-header "ticket.priority.getAll" []))

(defn get-statuses [auth-header]
	(api-req auth-header "ticket.status.getAll" []))

(defn get-ticket-types [auth-header]
	(api-req auth-header "ticket.type.getAll" []))

(defn get-severity-names [auth-header]
	(api-req auth-header "ticket.severity.getAll" []))

(defn list-methods [auth-header]
	(api-req auth-header "system.listMethods" []))

(defn get-method-help [auth-header method]
	(api-req auth-header "system.methodHelp" [method]))

(defn get-recent [auth-header]
	(api-req auth-header "ticket.getRecentChanges" [(format-ts-for-json (get-current-timestamp-str))]))

;;ticket methods
(defn get-ticket-hash [auth-header ticketno]
	(let [res (api-req auth-header "ticket.get" [ticketno])]
	 	(if (res 0) (ticket-result-to-hash res)
			{:error "Ticket not found."})))

(defn get-ticket-raw [auth-header ticketno]
	(let [res (api-req auth-header "ticket.get" [ticketno])]
	 	(if (res 0) res
			{:error "Ticket not found."})))

(defn get-ticket [auth-header ticketno]
	(let [res (api-req auth-header "ticket.get" [ticketno])]
	 	(if (res 0) (flatten-ticket-result res)
			{:error "Ticket not found."})))

(defn get-ticket-actions-raw [auth-header ticketno]
	(api-req auth-header "ticket.getActions" [ticketno]))

(defn get-ticket-actions [auth-header ticketno]
	(let [res (get-ticket-actions-raw auth-header ticketno)]
	(if res (map parse-actions res)))	)

(defn get-ticket-changelog [auth-header ticketno]
	 (map hashify-changelog-entry (api-req auth-header "ticket.changeLog" [ticketno])))

(defn get-ticket-attributes [auth-header ticketno]
	(ticket-attributes (get-ticket-hash auth-header ticketno)))

; Destructive calls
(defn update-ticket [auth-header ticketno commentstr action]
	(let [notify false attrs {:_ts (get-current-timestamp-str) :action action}]
	(api-req auth-header "ticket.update" [ticketno commentstr attrs])))

;Generic method to update ticket attributes
(defn update-ticket-attrs [auth-header ticketno commentstr attrs]
	(let [attrs (conj (get-ticket-attributes ticketno) attrs)]
	(api-req auth-header "ticket.update" [ticketno commentstr attrs])))

(defn update-ticket-attr [auth-header ticketno attr-hash]
	(let [
		commentstr (str "Updated attributes " attr-hash)
		attrs (conj (get-ticket-attributes auth-header ticketno) attr-hash)]

	(api-req auth-header "ticket.update" [ticketno commentstr attrs])))

;convenience setters for individual attrs
(defn add-ticket-comment [auth-header ticketno commentstr]
	(update-ticket-attrs auth-header ticketno commentstr {}))

(defn update-ticket-summary [auth-header ticketno summary]
	(update-ticket-attrs auth-header ticketno (str "Updated Summary to " summary) {:summary summary}))

(defn update-ticket-description [auth-header ticketno description]
	(update-ticket-attrs auth-header ticketno (str "Updated Description to " description) {:description description}))

(defn update-ticket-component [auth-header ticketno component]
	(update-ticket-attrs auth-header ticketno (str "Updated Component to " component) {:component component}))

(defn update-ticket-milestone [auth-header ticketno milestone]
	(update-ticket-attrs auth-header ticketno (str "Updated Milestone to " milestone) {:milestone milestone}))

(defn update-ticket-priority [auth-header ticketno priority]
	(update-ticket-attrs auth-header ticketno (str "Updated Priority to " priority) {:priority priority}))

(defn update-ticket-keywords [auth-header ticketno keywords]
	(update-ticket-attrs auth-header ticketno (str "Updated Keywords to " keywords) {:keywords keywords}))

(defn update-ticket-owner [auth-header ticketno owner]
	(update-ticket-attr auth-header ticketno {:owner owner :action "reassign"}))

(defn update-ticket-resolution [auth-header ticketno resolution]
	(update-ticket-attr auth-header ticketno {:action resolution :resolution resolution}))

(defn create-ticket [auth-header summary description]
	(let [notify false attributes {}]
	(api-req auth-header "ticket.create" [summary description attributes notify])))

(defn delete-ticket [auth-header ticketno]
	(api-req auth-header "ticket.delete" [ticketno]))

; too new
; (defn query-tickets [query]
; 	(api-req " ticket.query" ["status!=closed"]))
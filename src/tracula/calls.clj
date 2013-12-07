(ns tracula.calls
	(:use [tracula.utils]))

;read only
;System lists
(defn get-ticket-fields []
	(api-req "ticket.getTicketFields" []))

(defn get-components []
	(api-req "ticket.component.getAll" []))

(defn get-milestones []
	(api-req "ticket.milestone.getAll" []))

(defn get-priorities []
	(api-req "ticket.priority.getAll" []))

(defn get-statuses []
	(api-req "ticket.status.getAll" []))

(defn get-ticket-types []
	(api-req "ticket.type.getAll" []))

(defn get-severity-names []
	(api-req "ticket.severity.getAll" []))

(defn list-methods []
	(api-req "system.listMethods" []))

(defn get-method-help [method]
	(api-req "system.methodHelp" [method]))

(defn get-recent []
	(api-req "ticket.getRecentChanges" [(format-ts-for-json (get-current-timestamp-str))]))

;;ticket methods
(defn get-ticket-hash [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) (ticket-result-to-hash res)
			{:error "Ticket not found."})))

(defn get-ticket-raw [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) res
			{:error "Ticket not found."})))

(defn get-ticket [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) {"ticket" (flatten-ticket-result res)}
			{:error "Ticket not found."})))

(defn get-ticket-actions-raw [ticketno]
	(api-req "ticket.getActions" [ticketno]))

(defn get-ticket-actions [ticketno]
	(let [res (get-ticket-actions-raw ticketno)]
	(if res (map parse-actions res)))	)

(defn get-ticket-changelog [ticketno]
	 (map hashify-changelog-entry (api-req "ticket.changeLog" [ticketno])))

(defn get-ticket-attributes [ticketno]
	(ticket-attributes (get-ticket-hash ticketno)))

; Destructive calls
(defn update-ticket [ticketno commentstr action]
	(let [notify false attrs {:_ts (get-current-timestamp-str) :action action}]
	(api-req "ticket.update" [ticketno commentstr attrs])))

;Generic method to update ticket attributes
(defn update-ticket-attrs [ticketno commentstr attrs]
	(let [attrs (conj (get-ticket-attributes ticketno) attrs)]
	(api-req "ticket.update" [ticketno commentstr attrs])))

(defn update-ticket-attr [ticketno attr-hash]
	(let [
		commentstr (str "Updated attributes " attr-hash) 
		attrs (conj (get-ticket-attributes ticketno) attr-hash)]
	
	(api-req "ticket.update" [ticketno commentstr attrs])))

;convenience setters for individual attrs
(defn add-ticket-comment [ticketno commentstr]
	(update-ticket-attrs ticketno commentstr {}))

(defn update-ticket-summary [ticketno summary]
	(update-ticket-attrs ticketno (str "Updated Summary to " summary) {:summary summary}))

(defn update-ticket-description [ticketno description]
	(update-ticket-attrs ticketno (str "Updated Description to " description) {:description description}))

(defn update-ticket-component [ticketno component]
	(update-ticket-attrs ticketno (str "Updated Component to " component) {:component component}))

(defn update-ticket-milestone [ticketno milestone]
	(update-ticket-attrs ticketno (str "Updated Milestone to " milestone) {:milestone milestone}))

(defn update-ticket-priority [ticketno priority]
	(update-ticket-attrs ticketno (str "Updated Priority to " priority) {:priority priority}))

(defn update-ticket-keywords [ticketno keywords]
	(update-ticket-attrs ticketno (str "Updated Keywords to " keywords) {:keywords keywords}))

(defn update-ticket-owner [ticketno owner]
	(update-ticket-attr ticketno {:owner owner :action "reassign"}))

(defn update-ticket-resolution [ticketno resolution]
	(update-ticket-attr ticketno {:action resolution :resolution resolution}))

(defn create-ticket [summary description]
	(let [notify false attributes {}]
	(api-req "ticket.create" [summary description attributes notify])))

(defn delete-ticket [ticketno]
	(api-req "ticket.delete" [ticketno]))

; too new
; (defn query-tickets [query]
; 	(api-req " ticket.query" ["status!=closed"]))
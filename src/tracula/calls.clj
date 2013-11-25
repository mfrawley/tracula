(ns tracula.calls
	(:use [tracula.utils]))

(defn list-methods []
	(api-req "system.listMethods" []))

(defn get-method-help [method]
	(api-req "system.methodHelp" [method]))

(defn hashify-changelog-entry [changelog-arr]
	(let [[dtime, author, field, oldvalue, newvalue, permanent ] changelog-arr]
	{:time dtime :author author :field field :oldvalue oldvalue :newvalue newvalue :permanent permanent}))

(defn json-datetime-to-str [dt-map]
	((dt-map "__jsonclass__") 1))

(defn flatten-result [res]
	(let [flat {:id (res 0) :time_created (json-datetime-to-str (res 1)) :time_changed (json-datetime-to-str (res 2)) }]
		(conj flat (res 3))))

;;ticket methods
(defn get-ticket [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) (flatten-result res)
			{:error "Ticket not found."})))

(defn get-ticket-actions [ticketno]
	(api-req "ticket.getActions" [ticketno]))

(defn get-ticket-changelog [ticketno]
	 (map hashify-changelog-entry (api-req "ticket.changeLog" [ticketno])))

(defn update-ticket [ticketno commentstr action notify]
	(let [notify false action "done" attrs {:_ts (get-current-timestamp-str) :action "done"}]
	(api-req "ticket.update" [ticketno commentstr attrs])))

(defn create-ticket [summary description]
	(let [notify false attributes {}]
	(api-req "ticket.create" [summary description attributes notify])))

(defn delete-ticket [ticketno]
	(api-req "ticket.delete" [ticketno]))

(defn get-ticket-fields []
	(api-req "ticket.getTicketFields" []))

(defn get-recent []
	(api-req "ticket.getRecentChanges" [(format-ts-for-json (get-current-timestamp-str))]))

; too new
; (defn query-tickets [query]
; 	(api-req " ticket.query" ["status!=closed"]))
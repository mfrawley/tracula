(ns tracula.calls
	(:use [tracula.utils]))

(defn hashify-changelog-entry [changelog-arr]
	(let [[dtime, author, field, oldvalue, newvalue, permanent] changelog-arr]
	{:time dtime :author author :field field :oldvalue oldvalue :newvalue newvalue :permanent permanent}))

(defn ticket-result-to-hash [res]
	{:id (res 0) :time_created (json-datetime-to-str (res 1)) :time_changed (json-datetime-to-str (res 2)) :attributes (res 3)})

(defn flatten-ticket-result [res]
	(let [flat {:id (res 0) :time_created (json-datetime-to-str (res 1)) :time_changed (json-datetime-to-str (res 2)) }]
		(conj flat (res 3))))

(defn list-methods []
	(api-req "system.listMethods" []))

(defn get-method-help [method]
	(api-req "system.methodHelp" [method]))

;;ticket methods
(defn get-ticket-hash [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) (ticket-result-to-hash res)
			{:error "Ticket not found."})))

(defn get-ticket [ticketno]
	(let [res (api-req "ticket.get" [ticketno])]
	 	(if (res 0) (flatten-ticket-result res)
			{:error "Ticket not found."})))

(defn parse-action-input-fields [fields]
	{:name (fields 0) :value (fields 1) :options (fields 2)})

(defn parse-actions [fields]
	(let [res {:action (fields 0) :label (fields 1) :hints (fields 2)}]
		(if (fields 3) (conj res {:input-fields (map parse-action-input-fields (fields 3))})) ))

(defn get-ticket-actions [ticketno]
	(let [res (api-req "ticket.getActions" [ticketno])]
	(if res (map parse-actions res))))

(defn get-ticket-changelog [ticketno]
	 (map hashify-changelog-entry (api-req "ticket.changeLog" [ticketno])))

(defn update-ticket [ticketno commentstr action]
	(let [notify false attrs {:_ts (get-current-timestamp-str) :action action}]
	(api-req "ticket.update" [ticketno commentstr attrs])))

(defn resolve-ticket [ticketno commentstr resolution]
	(let [attrs (conj (get-ticket-hash ticketno) {:action resolution})]
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
(ns tracula.client
	(:require
		[tracula.react :as re]
		[tracula.utils :as utils]
		[tracula.components.navbar :as navbar]))
))

(def app-state
  #js {:search "foolio"})

(def TicketViewHeaders
  (let [fields ["type" "priority" "status" "owner" "reporter" "component"]]
    (re/component (fn [] (this-as this
        (re/dom.div #js {:className "row"}
        (re/sdiv "col-md-1" (str "Type:" ) )
        (re/sdiv "col-md-1" (str "Priority:" ) )
        (re/sdiv "col-md-1" (str "Status:" ) )
        (re/sdiv "col-md-1" (str "Owner:" ) )
        (re/sdiv "col-md-1" (str "Reported by:" ) )
        (re/sdiv "col-md-1" (str "Component:" ) )))))))

(def TicketViewTitle
  (re/component (fn [] (this-as this
    (re/sdiv "row"
      (re/sdiv "col-md-8"
        (re/sdiv "page-header"
          (re/dom.h1 #js {:contentEditable "true"} "sample title"
            (re/dom.small nil "Created: 2014-23-23")))))))))

(def TicketViewDesc
	(re/component (fn [] (this-as this
		(let [description "some desc\n and another line"]
		(re/dom.hr nil nil)
    (re/dom.div #js {:className "row"}
      (re/dom.label #js {:for "description"} "Description")
      (re/dom.div #js {:className "col-md-12" :id "description" :contentEditable "true"} description)))))))

(def TicketView
  (re/component (fn [] (this-as this
    (re/dom.div nil
    (TicketViewTitle)
    (TicketViewHeaders)
    (TicketViewDesc)
    )))))

(render-component
  (navbar/NavBar app-state)
 	(re/get-id "navbar"))

(render-component
  (TicketView app-state)
 	(re/get-id "container"))

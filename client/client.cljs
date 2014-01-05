(ns tracula.client)
  (:require-macros [tracula.coremacros :as mac])
; (:require   [om.core :as om :include-macros true]
  ;             [om.dom :as dom :include-macros true])

(defn log [str]
  (.log js/console str))

;local helpers
(def dom js/React.DOM)

; Create the ReactJS object (which contains a standard js literal obj with a :render key)
(defn component [renderFn] 
  (js/React.createClass #js {:render renderFn}))

(def app-state 
  #js {:search "foolio"})

(defn sdiv [className body]
  "Simple div with just a className and a body"
  (dom.div #js {:className className} body))

(defn submit-button [bname bclass bid]
  (dom.button #js {:type "submit" :className bclass :id bid} bname))

(def NavBar 
  (component 
    (fn []
    (this-as this
      (let [search (.. this -props -search)]
      (dom.nav #js {:className "navbar navbar-default" :role "navigation"}
        (dom.form #js {:name "search" :action "/search" :className "form-inline" :role "form" :id "search_form" }
          (dom.div #js {:className "form-group"}
            (dom.label #js {:className "sr-only" :for "ticket_search"} "Search trac")
            (dom.input #js {:className "form-control input-lg" :type "search" :placeHolder "Search Trac" :value search} ))
          (submit-button "Search" "btn btn-success" "search_btn")
          (submit-button "Create" "btn primary" "create-ticket")
          )))))))


(def TicketViewHeaders
  (let [fields ["type" "priority" "status" "owner" "reporter" "component"]]
    (component (fn [] (this-as this
        (dom.div #js {:className "row"}
        (sdiv "col-md-1" (str "Type:" ) )
        (sdiv "col-md-1" (str "Priority:" ) )
        (sdiv "col-md-1" (str "Status:" ) )
        (sdiv "col-md-1" (str "Owner:" ) )
        (sdiv "col-md-1" (str "Reported by:" ) )
        (sdiv "col-md-1" (str "Component:" ) )))))))

(def TicketViewTitle
  (component (fn [] (this-as this 
    (sdiv "row"
      (sdiv "col-md-8"
        (sdiv "page-header"
          (dom.h1 #js {:contentEditable "true"} "sample title"
            (dom.small nil "Created: 2014-23-23")))))))))

(def TicketView
  (component (fn [] (this-as this 
    (dom.div nil
    (TicketViewTitle)
    (TicketViewHeaders)
    )))))

(js/React.renderComponent
  (NavBar app-state)
 (.getElementById js/document "navbar"))

(js/React.renderComponent
  (TicketView app-state)
 (.getElementById js/document "container")) 

(ns tracula.components.navbar
	(:require [tracula.react :as re]))

(def NavBar
  (re/component
    (fn []
    (this-as this
      (let [search (.. this -props -search)]
      (re/dom.nav #js {:className "navbar navbar-default" :role "navigation"}
        (re/dom.form #js {:name "search" :action "/search" :className "form-inline" :role "form" :id "search_form" }
          (re/dom.div #js {:className "form-group"}
            (re/dom.label #js {:className "sr-only" :for "ticket_search"} "Search trac")
            (re/dom.input #js {:className "form-control input-lg" :type "search" :placeHolder "Search Trac" :value search} ))
          (re/submit-button "Search" "btn btn-success" "search_btn")
          (re/submit-button "Create" "btn primary" "create-ticket"))))))))
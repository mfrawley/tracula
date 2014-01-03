(ns tracula.client)
  
(defn log [str]
  (.log js/console str))

;local helpers
(def dom js/React.DOM)

(def app-state {
  :loaded true
  })

; Create the ReactJS object (which contains a standard js literal obj with a :render key)
(defn component [renderFn] 
  (js/React.createClass #js {:render renderFn}))

(def NavBar
  (component (fn []
    (this-as this
      (dom.nav #js {:className "navbar navbar-default" :role "navigation"}
        (dom.form #js {:name "search" :action "/search" :className "form-inline" :role "form" :id "search_form" }
          (dom.div #js {:className "form-group"}
            (dom.label #js {:className "sr-only" :for "ticket_search"} "Search trac")
            (dom.input #js {:className "form-control input-lg" :type "search" :placeHolder "Search Trac"} )
            )
          (dom.button #js {:type "submit" :className "btn btn-success" :id "search_btn"} "Search")
          (dom.button #js {:type "submit" :className "btn primary" :id "create-ticket"} "Create")
          ))))))

(js/React.renderComponent
  (NavBar #js {})
 ; (CommentBox #js {:comments comments-data})
 (.getElementById js/document "container")) 
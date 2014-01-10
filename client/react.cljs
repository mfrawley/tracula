(ns tracula.react)

;local helpers
(def dom js/React.DOM)

(def render-component js/React.renderComponent)

(defn get-id [id]
	(.getElementById js/document id))

; Create the ReactJS object (which contains a standard js literal obj with a :render key)
(defn component [renderFn]
  (js/React.createClass #js {:render renderFn}))

(defn sdiv [className body]
  "Simple div with just a className and a body"
  (dom.div #js {:className className} body))

(defn submit-button [bname bclass bid]
  (dom.button #js {:type "submit" :className bclass :id bid} bname))
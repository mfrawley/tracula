(ns hello-cljs.core
  (:use [domina :only [by-id log]]
        [domina.css :only [sel]]
        [domina.events :only [listen!]]))


(listen! (by-id "main") :click 
  (fn [evt] (log "button clicked!")))

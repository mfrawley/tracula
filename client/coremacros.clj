(ns tracula.coremacros
  (:refer-clojure :exclude [map meta time]))

(defmacro render-fn [body] `(fn [] (this-as this) ~body))

(defmacro component [obj] 
  `(js/React.createClass (js-obj ~obj)))

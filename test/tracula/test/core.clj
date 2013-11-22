(ns tracula.test.core
  (:use [tracula.core])
  (:use [clojure.test]))

(defn has-keys [expected-keys obj]
	(is expected-keys (keys obj)))

(testing "API - basic tests"
	(deftest test-get-ticket
  	(let [ticket (get-ticket 61450)]
  		(is (type ticket) clojure.lang.PersistentArrayMap)
  		(has-keys [:id :time_created :time_changed :attributes] ticket))))

(ns tracula.test.utils
	(:use [tracula.core])
  	(:use [tracula.utils])
  	(:use [clojure.test]))

(testing "API - test utils"
	(deftest test-format-ts-for-json
	(is (contains? (format-ts-for-json (get-current-timestamp-str)) "__jsonclass__"))))

	(deftest test-get-current-timestamp-str
		(is (string? (get-current-timestamp-str))))
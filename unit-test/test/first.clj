(ns unit-test.test
  (:require [clojure.test :refer [is testing with-test deftest run-tests]]))

(deftest first-test
  (testing "simple"
    (is (= "hola" "hola"))
  )
)

(run-tests)
  

(ns palindrome 
  (:require [clojure.test :refer :all]))

(defn check [i n s]
  (if (= (get s i) (get s (- n i 1)))
    true
    false
  ))

(defn palindrome [s]
  (let [n (count s)]
    (if (= n 0)
      false
      (let [end (quot n 2)]
        (loop [i 0 ispal true] 
          (if (or (>= i end) (= ispal false) )
            ispal
            (recur (inc i) ( check i n s))))))))

(deftest test-empty
  (is (= false (palindrome ""))))

(deftest test-simple-palindrome
  (is (palindrome "sas")))

(deftest test-one-char
  (is (palindrome "S")))

(deftest test-basic-case
  (is (palindrome "anitalavalatina")))

(run-tests)

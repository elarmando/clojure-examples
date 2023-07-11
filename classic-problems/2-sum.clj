(ns two-sum 
  (:require [clojure.test :refer [is testing with-test deftest run-tests]]))

;;given an unsorted integer array, find a pair with the given sum in it.
;;example array [1 2 3 4] and target 5, the pair is [2 3]

(defn sum-is-equal [x y array sum]
  (let [n1 (get array x) n2 (get array y)]
    (if (= (+ n1 n2) sum)
      true
      false
      )))

(defn find-pair [x array target]
  (let [len (count array)]
    (loop [y (inc x)]
      (if (< y len)
        (if (sum-is-equal x y array target)
          [x y]
          (recur (inc y))
          )))))

(defn find-pair-sum [array target]
  (let [len (count array)]
    (loop [x 0]
      (if (< x len)
        (let [pair (find-pair x array target)]
          (if (some? pair)
            pair
            (recur (inc x))
          ))))))

(deftest two-sum
  (testing "nil for empty array"
    (is (= nil (find-pair-sum [] 0))))

  (testing "basic case"
    (is (= [1 2] (find-pair-sum [3 1 2] 3))))
)

(run-tests)


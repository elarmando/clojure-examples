;;cansum(number, list) should return true if one can get 'number' by suming a combination of the numbers in list
;;For example, cansum(5, [1 2 3]) should return true because we can sum 2 + 3 = 5

(defn cansum [current, array]
  (cond 
    (= current 0) true
    (< current 0) false
    :else (let [children (for [n array] (cansum (- current n) array))]
        (some true? children))))


(println (cansum 0 [1 2]));;true
(println (cansum 1 [1 2]));;true
(println (cansum 3 [2 2]));;false
(println (cansum 8 [3 4 5]));;false



;;given an unsorted integer array, find a pair with the given sum in it.
;;example array [1 2 3 4] and target 5, the pair is [2 3]

(defn foreach_pair [n f]
  (loop [x 0 y 1]
    (when (and (< x n) (< y n) )
      (let [goal (f x y)]
        (if (some? goal)
          goal
          (if (and (>= y (dec n)))
            (recur (+ x 1) (+ x 2))
            (recur x (+ y 1))
            ))))))

(defn successfn [x y, array, target]
  (let [num1 (get array x) num2 (get array y)]
    (if (= (+ num1 num2) target)
      [num1 num2]
      nil
    )))

(defn two-sum [array target]
  (let [n (count array)]
    (let [res (foreach_pair n #(successfn %1 %2 array target))]
      (if (some? res)
        (println (str "solution " res)) 
        (println "no solution found")
      ))))

(two-sum [1 2 3 5] 7)


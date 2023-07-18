;;given an unsorted integer array, find a pair with the given sum in it.
;;example array [1 2 3 4] and target 5, the pair is [2 3]

(defn get-solution [x y array target]
  (let [n1 (get array x) n2 (get array y)]
    (if (= (+ n1 n2) target)
      [n1 n2]
      nil
      )))

(defn two-sum [array target]
  (let [n (count array)]
    (loop [x 0 y 1]
      (when (and (< x n) (< y n))
        (let [solution (get-solution x y array target)]
          (if (some? solution)
            solution ;;return solution
            (if (>= y (- n 1)) ;;else keep iterations
              (recur (inc x) (+ x 2))
              (recur x (inc y)))
            ))))))

(println (two-sum [1 2 3 4 5 6 7] 10))


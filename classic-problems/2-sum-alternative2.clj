<<<<<<< HEAD

;;given an unsorted integer array, find a pair with the given sum in it.
;;example array [1 2 3 4] and target 5, the pair is [2 3]

(defn success [i j target array]
  (let [n1 (get i array) n2 (get j array)]
    (if (= (+ n1 n2) target)
      [n1 n2]
      nil
  )

(defn two-sum [target array]
  (let [n (count array)]
    (for [i (range n) j (range i n)]
      (let [goal (success i j target array)]
        (if (some? goal)
          goal
      )))))
=======
(defn two-sum [target array]
  (let [n (count array)]
    (for [i (range n) j (range i n)]
      (if (= target (+ (get array i) (get array j)))
        [(get array i) (get array j)]))))

(defn solve [target array]
  (filter some? (two-sum target array)))

(println (solve 5 [1 2 3]))
>>>>>>> 80bd4c0bf98e316a49f31e3cb89654bd60c7fd3d

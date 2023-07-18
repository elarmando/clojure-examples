
(defn success [i j target array]
  (let [n1 (get array i) n2 (get array j)]
    (if (= target (+ n1 n2))
      [n1 n2]
      nil)))

(defn two-sum [target array]
  (let [n (count array)]
    (for [i (range n) j (range i n)]
      (let [goal (success i j target array)]
        (if (some? goal)
          goal
          )))))

(defn solve [target array]
  (filter some? (two-sum target array)))

(println (solve 5 [1 2 3]))

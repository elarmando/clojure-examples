(defn two-sum [target array]
  (let [n (count array)]
    (for [i (range n) j (range i n)]
      (if (= target (+ (get array i) (get array j)))
        [(get array i) (get array j)]))))

(defn solve [target array]
  (filter some? (two-sum target array)))

(println (solve 5 [1 2 3]))

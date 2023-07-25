
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

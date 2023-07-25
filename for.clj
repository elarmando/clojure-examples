
(defn my-average [list] 
    (/ (reduce + list) (count list))
)

(defn my-for[]
    (for [x (range 6)] 
        (* x x)
    )
)

(defn simple-loop []
    (loop [x 0]
        (when (< x 10)
            (println x)
            (recur (+ x 1))
        )
    )
)

;;multiple variables in loop
(defn multiple-loop[]
    (loop [x 0 flag true]
        (when (< x 10)
            (println (str x flag))
            (recur (+ x 1) (not flag))
        )
    )
)

(defn multi-var-for []
  (for [i (range 10) j (range 10)]
    [i j]
  )
)

(defn stair-for [n]
  (for [i (range n) j (range i n)]
    [i j]
  )
)

;;(multiple-loop)
;;(println (my-for))
(println (stair-for 5))

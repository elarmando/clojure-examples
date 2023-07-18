
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

(defn matrix-loop [n]
  (loop [x 0 y 0]
    (when (< x n)
      (println (str x "-" y))

      (if (= y (- n 1))
        (recur (inc x) 0)
        (recur x (inc y))))))

(defn matrix-loop2 [n]
  (loop [i 0] 
    (when (< i (* n n))
      (let [x (quot i n) y (mod i n)]
        (println (str x "-" y)))
      (recur (inc i)))))

;;(multiple-loop)
;;(println (my-for))

(matrix-loop2 10)

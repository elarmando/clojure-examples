
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


(println (my-for))
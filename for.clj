
(defn my-average [list] 
    (/ (reduce + list) (count list))
)

(defn my-for[]
    (for [x (range 6)] 
        (* x x)
    )
)


(println (my-for))
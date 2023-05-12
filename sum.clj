(defn create-list []
    (for [x (range 10)]
        x
    )
)

(defn sum [list] 
    (
        reduce + list
    )
)

(println (sum(create-list)))
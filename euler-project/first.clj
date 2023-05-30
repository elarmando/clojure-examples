(defn sum [n]
    (if (or (= (rem n 3) 0) (= (rem n 5) 0))
        n
        0
    )
)

(defn sum-list [size]
    (for [n (range 1 size)]
        (sum n)
    )
)

(defn problem1 [n]
    (reduce + (sum-list n))
)

(println (problem1 1000))
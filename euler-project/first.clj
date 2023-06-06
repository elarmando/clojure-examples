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


(println (reduce + (for [i (range 1 1000)] (if (or (= (rem i 3) 0) (= (rem i 5))) i 0))))

;;(println (problem1 1000))
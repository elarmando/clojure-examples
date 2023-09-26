(defn min-reducer [a1 a2]
  (if (= a1 nil)
    a2
    (if (< a1 a2)
      a1
      a2)))

(defn find-min [array]
  (reduce min-reducer nil array))  

(println (find-min [1]))
(println (find-min []))
(println (find-min [ 4 92 2 8]))


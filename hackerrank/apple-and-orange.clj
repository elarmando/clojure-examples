(defn inside [v r1 r2]
  (and (>= v r1) (<= v r2)))

(defn count_fruit [s t dist fruit]
 (let [fruit_position (map #(+ dist %1) fruit)]
   (count (filter #(inside %1 s t) fruit_position))))


(defn count_fruits [s t a b apples oranges]
  (println (count_fruit s t a apples))
  (println (count_fruit s t b oranges)))


(count_fruits 7 10 4 12 [2 3 -4] [3 -2 -4])

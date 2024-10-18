(defn get-max [v]
 (apply max v))

(defn solve [v]
  (let [l (count v) mx (get-max v)]
    (loop [i 0 cnt 0]
      (if (< i l)
        (if (= (get v i) mx)
         (recur (inc i) (inc cnt))
        ;;else
          (recur (inc i) cnt)) 
      ;;else
        cnt))))

(println (solve [4 4 1 3]))


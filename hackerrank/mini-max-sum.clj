
(defn sum [v skipIndex]
  (let [n (count v)]
    (loop [i 0 sum 0]
      (if (< i n)
        (if (= i skipIndex) 
          (recur (inc i) sum)
        ;;else
          (let [num (get v i)]
            (recur (inc i) (+ sum num))))
      ;;else
        sum ))))

(defn find_sums [v]
  (let [l (count v)]
    (for [i (range l)]
      (sum v i))))

(defn solve [v]
  (let [sums (find_sums v)
        nmin (apply min sums)
        nmax (apply max sums)]
    (println nmin nmax)))


(solve [1 3 5 7 9])

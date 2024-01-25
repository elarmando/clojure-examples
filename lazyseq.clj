(defn create-nums 
"creates numbers"
  ([]
   (create-nums 1))
  ([n]
   (lazy-seq (cons n (create-nums (inc n))))))


(println (take 10 (create-nums)))


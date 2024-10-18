

(defn oneifequal [mx x]
  (if (= mx x) 1 0))

(defn create-zero-one-sq [mx v]
  (map (fn [x] (oneifequal mx x)) v))

(defn solve [v]
  (let [mx (apply max v)
        zero-one-sq (create-zero-one-sq mx v)]
    (reduce + zero-one-sq)))


(println (solve [4 4 1 3]))

;;(println (create-zero-one-sq 2 [1 2 1]))
     

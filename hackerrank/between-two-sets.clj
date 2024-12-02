(defn is_factor [a b]
 (= (rem b a) 0))

(defn find_factors [n]
  (filter (fn [x] (is_factor x n)) (range 2 (+ n 1))))

(defn find_all_factors [n v1]
  (let [sq (filter (fn [x] (is_factor x n)) v1)]
        sq))

(defn are_all_factors [n v1]
  (let [sq (find_all_factors n v1)
        len_sq (count sq)
        len (count v1)]
        (= len_sq len)))

(defn find_arr_factors [v1 v2]
  (filter (fn [x] (are_all_factors x v1)) v2))


(defn solve [v1 v2]
  (let [m (apply min v2)
        factors (find_factors m)
        factors2 (find_arr_factors v1 factors)]
    (count factors2)))

(println "isfactor 4 16" (is_factor 4 16))
(println "isfactor 16 4" (is_factor 16 4))
(println "isfactor 5 16" (is_factor 5 16))
(println "find_factors 10" (find_factors 10))
(println "find_factors 15" (find_factors 15))

(println "find_all_factors " (find_all_factors 16 [1 2 3 4 5]))
(println "are_all_factors " (are_all_factors 16 [2 4 3]))
(println "find are factors " (find_arr_factors [2 4 5] [8 16 32]))
(println "solve " (solve [2 4] [ 16 32 96]))

(defn add-count [element count_table]
  (let [count_element (get count_table element)]
   (if (= count_element nil)
    (assoc count_table element 1) 
    (assoc count_table element (inc count_element)))))

(defn is-duplicated [element count_table]
  (let [count_element (get count_table element)]
    (if (> count_element 1) true false)))

(defn contains_duplicate [array]
  (let [n (count array)]
    (loop [i 0 duplicated false count_table {}]
      (if (or (>= i n) duplicated)
        duplicated
        (let [element (get array i) 
              new_table (add-count element count_table)
              new_duplicated (is-duplicated element new_table)
              ]
         (recur (inc i) new_duplicated new_table))))))

(println (contains_duplicate []));;false
(println (contains_duplicate []));;false
(println (contains_duplicate [1 2]));;false
(println (contains_duplicate [1 2 1]));;true
(println (contains_duplicate [1 1]));;true
(println (contains_duplicate [2 1 2]));;true

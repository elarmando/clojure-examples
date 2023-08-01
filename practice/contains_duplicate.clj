;;Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

(defn reducer [success pair]
  (if success
    true
    (if (= (get pair 0) (get pair 1 )) true false)))

(defn gen-seq [n array]
  (for [i (range n) j (range (inc i) n)]
    (let [n1 (get array i) n2 (get array j)]
      [n1 n2]
      )))


(defn contains-duplicate [array]
  (let [n (count array)]
    (if (< n 2)
      false
      (let [pairs (gen-seq n array)]
        (reduce reducer false pairs)))))

(println (contains-duplicate [3]))
(println (contains-duplicate []))
(println (contains-duplicate [1 1 3]))
(println (contains-duplicate [1 2 3]))
(println (contains-duplicate [1 2 1]))




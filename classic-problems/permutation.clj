
(defn newper [index final ipermutation array]
  (for [i (range index final)]
    (let [new-element (get array i)]
      (conj ipermutation new-element))))

(defn concat-arr [a1 a2]
  (into [] (concat a1 a2)))

(defn calc-permutation [x ipermutation array]
  (let 
    [size (count array)
     new-array (into [] (newper x size ipermutation array))
     new-array-size (count new-array)]
    (loop [i 0 result new-array]
      (if (>= i new-array-size)
        result
        (let [next-perm (get new-array i)
              children (calc-permutation (+ i x 1) next-perm array)
              current-res (concat-arr result children)]
          (recur (inc i) current-res))))))

(defn permutation [arr]
  (calc-permutation 0 [] arr)) 

(println (permutation []))
(println (permutation [1 2]))
(println (permutation [1 2 3]))
(println (permutation [1 2 3 4]))

;;(println (append 0 2 [3 4] [1 2]))

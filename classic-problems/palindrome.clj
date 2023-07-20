(defn check [i n s]
  (if (= (get s i) (get s (- n i 1)))
    true
    false
  ))

(defn palindrome [s]
  (let [n (count s) end (quot n 2)]
    (if (= n 0)
      false
      (loop [i 0 ispal true] 
        (if (or (>= i end) (= ispal false) )
          ispal
          (recur (inc i) ( check i n s)))))))

(println (palindrome "s"))
(println (palindrome "sa"))
(println (palindrome "sas"))
(println (palindrome "ssass"))
(println (palindrome "")

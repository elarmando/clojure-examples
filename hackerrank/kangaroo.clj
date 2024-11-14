(defn can_catch [x1 v1 x2 v2]
  (if (and (< x1 x2) (> v1 v2)) 
    true
    (if (and (= x1 x2) (= v1 v2))
      true
      false)))

(defn is_integer [n]
  (== (- n (Math/floor n)) 0))

(defn kangaroo [x1 v1 x2 v2]
  (if (or (can_catch x1 v1 x2 v2) (can_catch x2 v2 x1 v1))
    (let [n1 (- x2 x1) n2 (- v1 v2)]
      (if (or (= n2 0) (is_integer (/ n1 n2)))
        "YES"
        "NO"))
    "NO"))
;  (let [n1 (- x2 x1) n2 (- v1 v2)]
;    (if (= n2 0) 
;      "NO" 
;      "YES")))
     ; (if (>= (/ n1 n2) 0) "YES" "NO"))))

(println (kangaroo 0 3 4 2))
(println (kangaroo 0 2 5 3))



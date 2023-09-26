(defn is-divisible [i n]
  (= (mod n i) 0))

(defn list-divisible [n]
  (filter #(is-divisible % n) (range 1 n)))

(defn is-prime [n]
  (if (<= (count (list-divisible n)) 1) true false))

(println "1" (is-prime 1))
(println "2" (is-prime 2))
(println "3" (is-prime 3))
(println "4" (is-prime 4))

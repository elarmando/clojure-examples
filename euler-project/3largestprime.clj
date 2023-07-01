(defn get-divisor [n]
  (loop [i 2]
    (if (= (mod n i) 0)
      i
      (recur (inc i))
    )
  )
)

(defn get-prime-factors [n]
  (loop [num n, primes []]
    (if (= num 1)
        primes
        (let [divisor (get-divisor num)]
          (recur (quot num divisor) (conj primes divisor))
        )
    )
  )
)

(defn solve [n]
  (apply max (get-prime-factors n))
  )
;;(print (get-prime-factors 13195))
;;(print (solve 13195))
(print (solve 600851475143))

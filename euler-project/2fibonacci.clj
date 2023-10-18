;;By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

(def limit 4000000)

(defn fibsum []
 (loop [num1 0 num2 1 sum 0]
    (let [next-num (+ num1 num2) 
          new-sum (if (even? next-num) (+ sum next-num) sum)]
      (if (> next-num limit)
        sum
        (do 
          ;;(println next-num " sum " new-sum)
          (recur num2 next-num new-sum))))))

(println "result" (fibsum))

(defn fib [n] (
    cond (or (== n 0) (== n 1)) n 
         :else (+ (fib (- n 1)) (fib ( - n 2)))
))

(defn result-str [n value]
    (str "f(" n ")=" value )
)

(defn print-fib-table [n](
    dotimes [x n] 
    (
        println (result-str x (fib x))
    )
))

(print-fib-table 10)
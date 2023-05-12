;;takes 5 numbers from console and then calculates the average
(defn parse-int [s]
  (Integer/parseInt (re-find #"\A-?\d+" s))
)

(defn process_input [n]
    (println (str "(" n ")" "Enter number:"))
    (parse-int (read-line))
)

(defn read_numbers [n] 
    (for [x (range n)]                           
            (process_input (+ x 1));;read line from console
    )
)

(defn sum_numbers [list]
    (apply + list)
)

(defn average-number [list]
    (let [sum (sum_numbers list)
          count (count list)]
          (double (/ sum count))
    )
)

(defn main_func []
    (println "This program reads 5 numbers and calculates the average: ")
    (println 
        (str "Average: " (average-number (read_numbers 5)))
    )
)

(main_func)


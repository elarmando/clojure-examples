(defn sum [n]
  (let [list (range n)]
   (/ (reduce + list) (count list))
   )
)

(print (sum 5))

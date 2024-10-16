(defn sum [arr]
  (let [n1 (get arr 0) n2 (get arr 1)]
    (+ n1 n2)))

(defn main []
  (print (sum [5 5])));

(main)


(defn square [x] (* x x))
(defn my-map [] (map square [ 1 2 3]))

(println (my-map))
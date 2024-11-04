(defn next-5-mult [n]
  (let [m (mod n 5)]
    (+ n (- 5 m))))

(defn grade-next [n]
  (let [nxt (next-5-mult n)
        diff (- nxt n)]
      (if (< diff 3)
        nxt
        n)))

(defn grading [grade]
  (if (< grade 38)
    grade
    ;;else
    (grade-next grade)))

(defn grading-students [arr]
  (for [n arr]
    (grading n)))

(println (grading-students [73 67 38 33]))

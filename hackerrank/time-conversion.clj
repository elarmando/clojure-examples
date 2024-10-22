
(defn solvepm [hour minsec]
  (let [h (Integer/parseInt hour)
        newh (+ h 12)]
    (if (= h 12)
      (str h minsec)
      (str newh minsec))))

(defn solveam [hour minsec]
  (if ( = hour "12")
    (str "00" minsec)
    (str hour minsec)))

(defn solve [s]
  (let [hour (subs s 0 2) 
        length (count s)
        minsec (subs s 2 (- length 2))
        ampm (subs s (- length 2) length)]
    (if (= ampm "PM")
      (solvepm hour minsec)
      (solveam hour minsec))))

(println (solve "01:01:00PM"))
(println (solve "10:01:00AM"))
(println (solve "12:00:00AM"))
(println (solve "12:01:00PM"))

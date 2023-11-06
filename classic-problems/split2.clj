(defn find_char [str char]
    (loop [index 0 indexFound -1]
        (if (and (< index (count str)) (= indexFound -1))
            (if (= (get str index) char)
                (recur (inc index) index)
                (recur (inc index) -1))
            indexFound)))


(defn split_ [str char res]
    (let [index (find_char str char)
          strsize (count str)]
        (cond 
            (= index -1) (conj res str)
            (or (= strsize 1) (= strsize 0)) []
            :else 
                (let [ newstr (subs str 0 index)
                       nextsub (subs str (inc index))
                       newres (conj res newstr)]
                       (split_ nextsub char newres)))))


(defn split [str char]
    (split_ str char []))
        
(println (find_char "hola " \a))
(println (find_char "hola " \i))
(println (split "123|456" \|))
(println (split "|" \|))
(println (split "" \|))

        
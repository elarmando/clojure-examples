(defn matches [str index char]
    (let [current (get str index)
            compareChar (get char 0)]
            (if (= current compareChar) true false)))

(defn appendSubstring [array str init final]
    (if (< init final)
        (conj array (subs str init final))
        array))

(defn split [str char]
    (loop [index 0 init 0 result []]
        (if (< index (count str))
            (if (or (matches str index char))
                (recur (inc index) (inc index) (appendSubstring result str init index))
                (recur (inc index) init result)
            )
            (appendSubstring result str init (count str)))))

(println (split "123|456|78" "|"))
(println (split "123" "|"))
(println (split "" "|"))
(println (split "a|" "|"))
(def init_board ["O" " " "X" " " "O" " " " " "X" "O"])

(defn str_line_board [board index](
    str (get board index) " | " (get board (+ index 1)) " | " (get board (+ index 2)) 
))

(defn draw [board] (
        (println (str_line_board board 0))
        (println "------")
        (println (str_line_board board 3))
        (println "------")
        (println (str_line_board board 6))
    )
)

(defn main [](
    draw init_board
))

(main)
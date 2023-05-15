(def empty_cell " ")

(def init_board [empty_cell empty_cell empty_cell empty_cell empty_cell empty_cell empty_cell empty_cell empty_cell])

(defn get_board_pos [col row]
    (+ (* row 3) col)
)
(defn get_symbol [board col row]
    (get board (get_board_pos col row)) ;;=index = (row * 3) + col
)
(defn move [board col row new_symbol]
    (let [symbol (get_symbol board col row)]
        (if (clojure.string/blank? symbol)
            (assoc board (get_board_pos col row) new_symbol)
            board
        )
    )
)

(defn str_line_board [board index](
    str (get board index) " | " (get board (+ index 1)) " | " (get board (+ index 2)) 
))

(defn draw [board] (
        (println (str_line_board board 0))
        (println "--------")
        (println (str_line_board board 3))
        (println "--------")
        (println (str_line_board board 6))
    )
)


(defn main [](
    (draw (move init_board 2 2 "X"))
))

(main)
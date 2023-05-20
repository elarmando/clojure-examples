(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

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
            (assoc board (get_board_pos col row) new_symbol);;
            board
        )
    )
)

(defn make_move [move-list board new_symbol]
    (let [col (get move-list 0) row (get move-list 1)]
        (move board col row new_symbol) 
    )
)

(defn str_line_board [board index](
    str (get board index) " | " (get board (+ index 1)) " | " (get board (+ index 2)) 
))

(defn draw [board] 
        (do 
            (println (str_line_board board 0))
            (println "--------")
            (println (str_line_board board 3))
            (println "--------")
            (println (str_line_board board 6))
        )
)

(defn read-move []
    (let [col (parse-int (read-line)) row (parse-int (read-line))]
        [col row] 
    )
)

(defn next-symbol [symbol]
    (if (= symbol "X")
        "O"
        "X"
    )
)

(defn game-over [board] 
    false
)

(defn game-loop [] 
        (loop [board init_board player-symbol "X"]
            (when (not (game-over board)) 
                (draw board)
                (recur (make_move (read-move) board player-symbol) (next-symbol player-symbol)) 
            )
        )
)

(defn main [](
    (game-loop)
))

(main)


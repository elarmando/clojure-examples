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

(defn any_empty [s1 s2 s3]
    (if (or (= s1 "") (= s2 "") (= s3 "") (= s1 " ") (= s2 " ") (= s3 " ")) true false)
)

(defn same_symbol [board col1 row1 col2 row2 col3 row3]
    (let [s1 (get_symbol board col1 row1) s2 (get_symbol board col2 row2) s3 (get_symbol board col3 row3)]
        (if (any_empty s1 s2 s3) false (= s1 s2 s3)
        )
    )
)

(defn check_horizontal [board]
    (or (same_symbol board 0 0 1 0 2 0) (same_symbol board 0 1 1 1 2 1) (same_symbol board 0 2 1 2 2 2))
)

(defn game-over [board] 
    (check_horizontal board)
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
    game-loop
))

;;(main)

(defn test_horizontal[]
    (let [newBoard (make_move [0 0] init_board "X")]
        (let [otherBoard (make_move [1 0] newBoard "X")]
            (let [anotherBoard (make_move [2 0] otherBoard "X")]
                (if (check_horizontal anotherBoard) "success" "fail")
            )
        )
    )
)

(println (test_horizontal))


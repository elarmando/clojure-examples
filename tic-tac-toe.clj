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

(defn read-one-move [msg]
    (do
        (print msg)
        (flush)
        (let [move (parse-int (read-line))]
            move
        )
    )
)

(defn read-move []
    (let [col (read-one-move "Type your column: ") row (read-one-move "Type your row: ")]
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

(defn check_vertical [board]
    (or (same_symbol board 0 0 0 1 0 2) (same_symbol board 1 0 1 1 1 2) (same_symbol board 2 0 2 1 2 2))
)

(defn check_diagonal [board]
    (or (same_symbol board 0 0 1 1 2 2) (same_symbol board 2 0 1 1 0 2))
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

(defn test_horizontal[row]
    (let [newBoard (make_move [0 row] init_board "X")]
        (let [otherBoard (make_move [1 row] newBoard "X")]
            (let [anotherBoard (make_move [2 row] otherBoard "X")]
                (if (check_horizontal anotherBoard) "success" "fail")
            )
        )
    )
)

(defn test_vertical []
    (let [board1 (make_move [0 0] init_board "X")]
        (let [board2 (make_move [0 1] board1 "X")]
            (let [board3 (make_move [0 2] board2 "X")]
                ;;(draw board3)
                (if (check_vertical board3) "success" "fail")
            )
        )
    )
)

(defn test_diagonal []
    (let [board (make_move [0 0] init_board "X")]
        (let [board2 (make_move [1 1] board "X")]
            (let [board3 (make_move [2 2] board2 "X")]
                (if (check_diagonal board3) "success" "fail")
            )
        )
    )
)

;;(println (test_horizontal 0))
;;(println (test_horizontal 1))
;;(println (test_horizontal 2))
;;(println (test_diagonal))


(main)

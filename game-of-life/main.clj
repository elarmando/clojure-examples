(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D))

(def rows 50)
(def cols 150)
(def size 20)
(def screen-size-x (+ (* cols size) 20))
(def screen-size-y (+ (* rows size) 40))

(defn to-vector [s]
  (into [] s))

(defn rand-live []
  (let [random (rand-int 100)]
    (= (mod random 2) 0)))

(defn get-neigbors-index []
  [[-1 -1] [-1 0] [-1 1]  [0 -1][0 1] [1 -1][1 0][1 1]])

(defn get-world-index [y x ncols]
  (+ (* y ncols) x))

(defn is-in-grid? [y x nrows ncols]
  (if (and (>= y 0) (>= x 0) (< y nrows) (< x ncols)) true false))

(defn get-neighborhood [y x world]
 (let [nrows (:rows world) 
        ncols (:cols world) 
        values (:values world)]
    (for [neighbor-array (get-neigbors-index)]
      (let [ny (+ y (get neighbor-array 0))
            nx (+ x (get neighbor-array 1))]
        [ny nx]))))

(defn get-neighbors [y x world]
  (let [nrows (:rows world) 
        ncols (:cols world) 
        values (:values world)]
    (filter #(is-in-grid? (get %1 0) (get %1 1) nrows ncols) 
            (get-neighborhood y x world))))

(defn get-neighbors-cells [y x world]
  (let [values (:values world) cols (:cols world)]
    (for [nbs-indexes (get-neighbors y x world)]
      (let [ny (get nbs-indexes 0)
            nx (get nbs-indexes 1)
            world-pos (get-world-index ny nx cols)]
        (get values world-pos)))))

(defn is-alive? [cell]
  (if (= (get cell 2) true) true false))

(defn should-live [y x world]
  (let [neighs (get-neighbors-cells y x world)
        count-neighs (count neighs)
        live-neighs (count (filter is-alive? neighs))
        death-neighs (- count-neighs live-neighs)
        cols (:cols world)
        cells (:values world)
        cell (get cells (get-world-index y x cols))
        is-cell-alive (get cell 2)]
    (if is-cell-alive
      (cond ;;is alive rules
            (< live-neighs 2)                         false
            (or (= live-neighs 2) (= live-neighs 3))  true
            :else                                     false)
      (cond ;;is dead rules
            (= live-neighs 3)                         true
            :else                                     false))))

(defn evolve-values [nrows ncols world]
  (to-vector 
    (for [i (range 0 nrows) j (range 0 ncols)]
      [i j (should-live i j world)])))

(defn evolve [world]
  (let [nr (:rows world) 
        nc (:cols world)
        new-values (evolve-values nr nc world)]
    {:rows nr :cols nc :values new-values}))

(defn create-values [nrows ncols]
  (to-vector
    (for [i (range 0 nrows) j (range 0 ncols)]
      [i j (rand-live)])))

(defn create-values-from-array [cols rows array]
  (let [size (count array) expected-size (* cols rows)]
    (if (not= size expected-size)
      (throw (Exception. "not expected size array"))
      (to-vector 
        (for [i (range 0 rows) j (range 0 cols)]
          (let [index (+ (* i cols) j)]
            [i j (get array index)]))))))

(defn create-world [num-rows num-cols]
  {:rows num-rows :cols num-cols :values (create-values num-rows num-cols)})

(defn draw [g world]
  (let [values (:values world)]
    (.clearRect g 0 0 screen-size-x screen-size-y)
    (doseq [coord values]
      (let [r (get coord 0) 
            c (get coord 1) 
            should-live (get coord 2)
            px (* c size)
            py (* r size)]
        (if should-live 
          (do 
            (.drawOval g py px size size)
            (.fillOval g py px size size))))))
  )

(defn main []
  (let [world (atom (create-world rows cols))
        frame (JFrame.) 
        panel (proxy [JPanel] [] (paintComponent [g] (draw g @world)))]
    (doto frame
      (.setDefaultCloseOperation
        javax.swing.WindowConstants/EXIT_ON_CLOSE)
      (.add panel)
      (.setSize screen-size-x screen-size-y)
      (.setTitle "Grid")
      (.setVisible true))
    (loop []
      (swap! world evolve)
      (.repaint panel)
      (. Thread (sleep 150))
      (recur))))

(main)

;(let [world (create-world 3 3)
      ;new-values (create-values-from-array 3 3 [false false true true false true false false false])
      ;new-world (assoc world :values new-values)
      ;evolved-world (should-live 1 1 new-world)
      ;]
  ;(println evolved-world))



(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D))

(def rows 50)
(def cols 50)
(def size 20)
(def screen-size-x (+ (* cols size) 20))
(def screen-size-y (+ (* rows size) 40))

(defn rand-live []
  (let [random (rand-int 100)]
    (= (mod random 2) 0)))

(defn get-neigbors-index []
  [[-1 -1] [-1 0] [-1 1]  [0 -1][0 1] [1 -1][1 0][1 1]])

(defn get-world-index [y x ncols]
  (+ (* y ncols) x))

(defn get-neighbors [y x world]
  (let [nrows (:rows world) 
        ncols (:cols world) 
        values (:values world)]
    (for [neighbor-array (get-neigbors-index)]
      (let [ny (+ y (get neighbor-array 0))
            nx (+ x (get neighbor-array 1))]
        (if (and (>= ny 0) (>= nx 0) (< ny nrows) (< nx ncols))
              (get values 0))))))
          ;;(get values (get-world-index ny nx ncols))))))))

(defn is-alive? [cell]
  (if (= (get cell 2) true) true false))

(defn should-live [y x world]
  (let [neighs (get-neighbors y x world)
        count-neighs (count neighs)
        live-neighs (count (filter is-alive? neighs))
        death-neighs (- count-neighs live-neighs)
        is-cell-alive (get world (get-world-index y x))]
    (if is-cell-alive
      (cond ;;is alive rules
            (< live-neighs 2)                         false
            (or (= live-neighs 2) (= live-neighs 3))  true
            :else                                     false)
      (cond ;;is dead rules
            (= live-neighs 3)                         true
            :else                                     false))))

(defn evolve [world]
  (let [nr (:rows world) 
        nc (:cols world)] 
    (for [i (range 0 nr) j (range 0 nc)]
      [i j (should-live i j world)])))

(defn create-values [nrows ncols]
  (for [i (range 0 nrows) j (range 0 ncols)]
    [i j (rand-live)]))

(defn create-world [num-rows num-cols]
  {:rows num-rows :cols num-cols :values (create-values num-rows num-cols)})

(defn draw [g world]
  (.clearRect g 0 0 screen-size-x screen-size-y)
  (doseq [coord world]
    (let [r (get coord 0) 
          c (get coord 1) 
          should-live (get coord 2)
          px (* c size)
          py (* r size)]
      (if should-live 
        (do 
          (.drawOval g py px size size)
          (.fillOval g py px size size))))))

(defn main []
  (let [world (atom (create-world))
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
      (. Thread (sleep 1000))
      (recur))))


;;(println (create-world 2 2))
;;(println (get-world-index 2 1 5) "should be " 11)
;;(println (get-world-index 0 2 5) "should be " 11)


;;(let [world (create-world 3 3)]
;;  (println world)
;;  (let [ngs (get-neighbors 1 1 world)]
;;    (println "neighbors " ngs)))

(println 
  (for [i (range 10)]
    (if (= (mod i 2) 0)
      i)))




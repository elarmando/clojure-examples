(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D)
        (java.awt.event KeyAdapter)
        (java.awt.event KeyEvent))

(def speedx 10)
(def speedy 10)
(def screen-size-x 600)
(def screen-size-y 600)
(def size 10)

(defn to-vector [l]
  (into [] l))

(defn init-world []
  {:snake [
          {:x (+ 100 (* 3 speedy)) :y 100}
          {:x (+ 100 (* 2 speedy)) :y 100}
          {:x (+ 100 speedy) :y 100} 
          {:x 100 :y 100 }] 
   :food [{:x 10 :y 10} {:x 10 :y 50} {:x 10 :y 80} {:x 500 :y 500}]
   :direction {:x 1 :y 0}})

(defn translate-circle [circle deltax deltay]
  (let [x (get circle :x) y (get circle :y)]
    {:x (+ (* speedx deltax) x) :y (+ (* speedy deltay) y)}))

(defn translate-circles [circles deltax deltay]
  (map #(translate-circle % deltax deltay) circles))

(defn translate [world deltax deltay]
  (let [
        snake (get world :snake)
        new-snake (translate-circles snake deltax deltay)]
    (assoc world :snake new-snake)))

(defn change_dir [world deltax deltay]
  (let [new-world (assoc world :direction {:x deltax :y deltay})]
    new-world))

(defn go-right [world]
  (change_dir world 1 0))

(defn go-left [world]
  (change_dir world -1 0))

(defn go-up [world]
  (change_dir world 0 -1))

(defn go-down [world]
  (change_dir world 0 1))

(defn get-tail-position [world]
  (let [snake (get world :snake)
        size (count snake)
        tail (get snake (- size 1))]
        {:x (get tail :x) :y (get tail :y)}))

(defn same-position [dot food-dot]
  (let [dotx (get dot :x)
        doty (get dot :y)
        foodx (get food-dot :x)
        foody (get food-dot :y)]
          (and (= dotx foodx) (= doty foody))))

(defn get-collapsed-food [dot food-array]
  (to-vector (filter #(same-position dot %) food-array)))

(defn remove-food [food-item food]
  (to-vector (remove #(same-position % food-item) food)))

(defn add-dot [snake new-dot]
  (conj snake new-dot)
)

(defn eat-food [world new-dot-position]
  (let [snake (get world :snake)
        food (get world :food)
        head (get snake 0)
        collapsed-food (get-collapsed-food head food)
        ]
        (if (> (count collapsed-food) 0)
          (let [food-to-eat (get collapsed-food 0)
                new-food (remove-food food-to-eat food)
                new-snake (conj snake new-dot-position)]
                (assoc world :snake new-snake :food new-food))
          ;;else 
            world)))

(defn create-random-food-item []
  (let [n-posx (quot screen-size-x 10)
        n-posy (quot screen-size-x 10)
        new-posx (* (rand-int n-posx) 10)
        new-posy (* (rand-int n-posy) 10)]
      {:x new-posx :y new-posy}))

(defn create-food-if-needed [world]
  (let [food (get world :food)
        food_count (count food)]
    (if (< food_count 1)
     (let [new-food-item (create-random-food-item)
           new-food (conj food new-food-item)]
        (assoc world :food new-food))
    ;;else
      world))) 

(defn create-new-dot [i snake new-snake direction]
  (if (or (= i 0) (= 1 (count snake)) ) ;;first element or only one element
    (let [old-dot (get snake 0)
          x (get direction :x)
          y (get direction :y)
          new-dot (translate-circle old-dot x y)]
          new-dot)
  ;;else
    (let [next-dot (get snake (dec i))
          next-x (get next-dot :x)
          next-y (get next-dot :y)]
      {:x next-x :y next-y }
    )
  )
)

(defn dot-collapsed [i j snake]
 (let [dot1 (get snake i)
      dot2  (get snake j)]
      (same-position dot1 dot2)))

(defn count-collapsed [snake]
  (let [size (count snake)
        collapsed (for [i (range size) j (range (+ i 1) size)] (dot-collapsed i j snake))]
        (count (filter #(= % true) collapsed))))

(defn is-game-over [world]
  (let [snake (get world :snake)
       size (count snake)]
       (if (> (count-collapsed snake) 0) true false)))

(defn move [world]
  (let [dir (get world :direction)
       snake (get world :snake)
       size (count snake)]
       (loop [i 0 new-snake []]
          (if (>= i size )
            (assoc world :snake new-snake)
          ;;else
            (let [new-dot (create-new-dot i snake new-snake dir)]
              (recur (inc i) (conj new-snake new-dot)))))))

(defn evolve [world]
  (let [tail-pos (get-tail-position world)
        new-world (move world)
        new-world2 (eat-food new-world tail-pos)
        new-world3 (create-food-if-needed new-world2)]
       new-world3))

(defn draw [world g]
 (do
  (println world)
   (let [snake (get world :snake) 
         bounds (.getClipBounds g)
         width (.getWidth bounds)
         height (.getHeight bounds)
         food (get world :food)]
    (.clearRect g 0 0 width height)
    (doseq [dot snake]
      (let [x (get dot :x)
            y (get dot :y)]
      (.drawOval g x y size size)
      (.fillOval g x y size size)))
    (doseq [dot-food food]
      (let [x (get dot-food :x)
            y (get dot-food :y)]
        (.drawRect g x y size size) 
        (.fillRect g x y size size))))))

(defn getKeyPressed [e]
  (let [code (.getKeyCode e)]
    (cond
      (= java.awt.event.KeyEvent/VK_UP code) "up"
      (= java.awt.event.KeyEvent/VK_DOWN code) "down"
      (= java.awt.event.KeyEvent/VK_RIGHT code) "right"
      (= java.awt.event.KeyEvent/VK_LEFT code) "left"
      :else "none")))

(defn onKeyStroke [world e]
  (let [move (getKeyPressed e)]
   (cond
     (= move "up") (go-up world)
     (= move "down") (go-down world)
     (= move "right") (go-right world)
     (= move "left") (go-left world)
     :else world)))

(defn main []
  (let [world (atom (init-world))
        frame (JFrame.)
        panel (proxy [JPanel] [] (paintComponent [g] (draw @world g)))]
    (doto frame
      (.setDefaultCloseOperation
        javax.swing.WindowConstants/EXIT_ON_CLOSE)
      (.add panel)
      (.setSize screen-size-x screen-size-y)
      (.setVisible true))
    (doto panel
      (.addKeyListener
        (proxy [KeyAdapter] []
          (keyPressed [e]
              (swap! world  (fn [w] (onKeyStroke w e)))))))
    (.repaint panel)
    (.requestFocus panel)
      (loop [i 0 game-over false] 
        (if game-over 
          (println "game-over")
          ;;else
          (do
            (swap! world evolve) 
            (.repaint panel)
            (. Thread (sleep 250))
            (recur (inc i) (is-game-over @world)))))))

(main)


;;(println (same-position {:x 0 :y 0} {:x 0 :y 1}))
;;(println (same-position {:x 0 :y 1} {:x 0 :y 1}))
;;(println (get-collapsed-food {:x 0 :y 0} [{:x 0 :y 1} {:x 0 :y 1}]))
;;(println (remove-food {:x 0 :y 0} [{:x 0 :y 0}]))

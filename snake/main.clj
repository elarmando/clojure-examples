(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D)
        (java.awt.event KeyAdapter)
        (java.awt.event KeyEvent))

(def speedx 15)
(def speedy 15)
(def size 10)

(defn to-vector [l]
  (into [] l))

(defn init-world []
  {:snake [{:x 100 :y 100 } 
            {:x (+ 100 speedy) :y 100} 
            {:x (+ 100 (* 2 speedy)) :y 100}
            {:x (+ 100 (* 3 speedy)) :y 100}] 
   :food [{:x 10 :y 10} {:x 500 :y 500}]
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

(defn evolve [world]
  (move world))

(defn main []
  (let [world (atom (init-world))
        frame (JFrame.)
        panel (proxy [JPanel] [] (paintComponent [g] (draw @world g)))]
    (doto frame
      (.setDefaultCloseOperation
        javax.swing.WindowConstants/EXIT_ON_CLOSE)
      (.add panel)
      (.setSize 600 600)
      (.setVisible true))
    (doto panel
      (.addKeyListener
        (proxy [KeyAdapter] []
          (keyPressed [e]
              (swap! world  (fn [w] (onKeyStroke w e)))))))
    (.repaint panel)
    (.requestFocus panel)
      (loop [i 0] 
        (do
        (swap! world evolve) 
        (.repaint panel)
        (. Thread (sleep 300))
        (recur (inc i))))))

(main)

;;(let [world (init-world)]
 ;; (println world)
  ;;(println (translate world)))

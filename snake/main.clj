(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D)
        (java.awt.event KeyAdapter)
        (java.awt.event KeyEvent))

(def speedx 5)
(def speedy 5)

(defn init-world []
 {:x 100 :y 100} )

(defn translate [world deltax deltay]
  (let [x (get world :x) y (get world :y)]
    {:x (+ (* speedx deltax) x) :y (+ (* speedy deltay) y)}))

(defn go-right [world]
  (translate world 1 0))

(defn go-left [world]
  (translate world -1 0))

(defn go-up [world]
  (translate world 0 -1))

(defn go-down [world]
  (translate world 0 1))

(defn draw [world g]
 (do
   (let [x (get world :x) 
         y (get world :y)
         bounds (.getClipBounds g)
         width (.getWidth bounds)
         height (.getHeight bounds)]
   (.clearRect g 0 0 width height)
   (.drawOval g x y 50 50)
   (.fillOval g x y 50 50))))

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
      (.setSize 600 600)
      (.setVisible true))
    (doto panel
      (.addKeyListener
        (proxy [KeyAdapter] []
          (keyPressed [e]
            (let [new-world "hol"]
              (swap! world  (fn [w] (onKeyStroke w e)))
              (.repaint panel))))))
    (.repaint panel)
    (.requestFocus panel)))

(main)

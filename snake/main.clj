(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D)
        (java.awt.event KeyAdapter)
        (java.awt.event KeyEvent))

(defn draw [g]
 (do
   (.drawOval g 300 300 100 100)
   (.fillOval g 300 300 100 100)))


(defn getKeyPressed [e]
  (let [code (.getKeyCode e)]
    (cond
      (= java.awt.event.KeyEvent/VK_UP code) "up"
      (= java.awt.event.KeyEvent/VK_DOWN code) "down"
      (= java.awt.event.KeyEvent/VK_RIGHT code) "right"
      (= java.awt.event.KeyEvent/VK_LEFT code) "left"
      :else "none")))

(defn onKeyStroke [e]
  (println (getKeyPressed e))) 

(defn main []
  (let [frame (JFrame.)
        panel (proxy [JPanel] [] (paintComponent [g] (draw g)))]
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
            (onKeyStroke e))))
          (.requestFocus))
    (.repaint panel)
    ))

(main)

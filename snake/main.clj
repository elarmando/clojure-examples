(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D))

(defn draw [g]
 (do
   (.drawOval g 300 300 100 100)
   (.fillOval g 300 300 100 100)))

(defn main []
  (let [frame (JFrame.)
        panel (proxy [JPanel] [] (paintComponent [g] (draw g)))]
    (doto frame
      (.setDefaultCloseOperation
        javax.swing.WindowConstants/EXIT_ON_CLOSE)
      (.add panel)
      (.setSize 600 600)
      (.setVisible true))
    (.repaint panel)
      
    ))

(main)

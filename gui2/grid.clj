(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D))

(def rows 640)
(def cols 640)

(defn create-panel []
  (proxy [JPanel] []
    (paintComponent [g]
      (.drawString g "Hola" 0 16))))

(defn main []
  (let [frame (JFrame.) panel (create-panel)]
    (doto frame
      (.setDefaultCloseOperation
        javax.swing.WindowConstants/EXIT_ON_CLOSE)
      (.add panel)
      (.setSize rows cols)
      (.setTitle "Grid")
      (.setVisible true))))

(main)

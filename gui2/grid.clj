(import (javax.swing JFrame)
        (javax.swing JPanel)
        (java.awt Graphics)
        (java.awt Graphics2D))

(def rows 640)
(def cols 640)
(def size 20)

(defn create-coordinates []
  (for [i (range 0 rows size) j (range 0 cols size)]
    [i j]))

(defn create-panel []
  (proxy [JPanel] []
    (paintComponent [g]
      (doseq [coord (create-coordinates)]
        (let [r (get coord 0) c (get coord 1) oval-size (quot size 2)]
          (.drawOval g r c oval-size oval-size)
          (.fillOval g r c oval-size oval-size))))))

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

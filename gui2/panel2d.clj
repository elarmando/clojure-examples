(import 
  (javax.swing JFrame)
  (javax.swing JPanel)
  (java.awt Graphics)
  (java.awt Graphics2D))
 
(defn create-panel []
  (proxy [JPanel] []
    (paintComponent [g]
      (.drawString g "Hello, Java 2D World!" 0 16))))
 
(def panel
  (create-panel))
 
(def frame
  (JFrame.))
 
(doto frame
  (.setDefaultCloseOperation
    javax.swing.WindowConstants/EXIT_ON_CLOSE)
  (.add panel)
  (.setSize 640 480)
  (.setTitle "Hello, World")
  (.setVisible true))

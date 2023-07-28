(ns frame (:import [javax.swing SwingUtilities JFrame]))

(defn create-gui []
  (let [my-frame (JFrame. "My Frame")]
    (.setDefaultCloseOperation my-frame JFrame/EXIT_ON_CLOSE)
    (.setSize my-frame 300 300)
    (.setVisible my-frame true)))


(SwingUtilities/invokeLater create-gui)


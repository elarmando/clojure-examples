(ns my-stuff.core
  (:gen-class)
  (:require [my-stuff.api.util :refer :all]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (println (sum 2 2)))

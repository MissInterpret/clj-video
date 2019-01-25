(ns clj-video.operator
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.spec.operator :as spec]))


(defn create [metadata qualified-impl-fn-name])

  ; returns a map with metadata implementation
  ; that matches the operator spec


(ns clj-video.container
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.spec.container :as spec]))

(defn make-container [data format metadata])

(s/fdef make-container 
  :args (s/cat :data any? :format any? :metadata any?)
  :ret ::spec/container)

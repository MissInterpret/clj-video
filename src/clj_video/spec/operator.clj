(ns clj-video.spec.operator
  (:gen-class)
  (:require [clojure.spec.alpha :as s]))

(s/def ::metadata map?)
(s/def ::impl map?)

(s/def ::operator (s/keys :req [::metadata ::impl]))
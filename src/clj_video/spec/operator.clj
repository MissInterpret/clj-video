(ns clj-video.spec.operator
  (:gen-class)
  (:require [clojure.spec.alpha :as s]))

(s/def ::metadata map?)
(s/def ::impl map?)

; Impl keys:  ns 

(s/def ::operator (s/keys :req [::metadata ::impl]))
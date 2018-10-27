(ns clj-video.spec.container
  (:gen-class)
  (:require [clojure.spec.alpha :as s]))

(s/def ::data (s/coll-of map?))
(s/def ::format map?)
(s/def ::metadata map?)

(s/def ::container (s/keys :req [::data ::format ::metadata]))

(ns clj-video.spec.operator
  (:gen-class)
  (:require [clojure.spec.alpha :as s]))

(s/def ::input (s/coll-of map?))
(s/def ::metadata map?)
(s/def ::impl any?)

(s/def ::operator (s/keys :req [::input ::metadata ::impl]))
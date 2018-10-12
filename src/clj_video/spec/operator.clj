(ns clj-video.spec.operator
  (:require [clojure.spec.alpha :as s]))

(s/def ::operation (s/keys :req [::impl ::meta]))


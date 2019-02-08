(ns clj-video.container
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.load :as l]))

(defn wrap-metadata [metadata info-fn data-fn]
  (with-meta metadata {`clj-video.protocol.container/info   info-fn 
                       `clj-video.protocol.container/data   data-fn}))

(defn create [{:keys [provider-ns] :as metadata}]
  (let [i (l/load-fn provider-ns 'info)
        d (l/load-fn provider-ns 'data)]
    (wrap-metadata metadata i d)))

(defn create-native [{:keys [native] :as metadata}]
  (let [classname (:classname native)
        instance (l/load-class classname (:init-args native))
        i (l/method classname 'info instance)
        d (l/method classname 'data instance)]
    (wrap-metadata metadata i d)))

; Check metadata for protocol fn's
(defn container? [c])
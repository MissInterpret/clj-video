(ns clj-video.operator
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.load :as l]))

(defn wrap-metadata [metadata support-fn process-fn]
  (with-meta metadata {`clj-video.protocol.operator/supports? support-fn
                       `clj-video.protocol.operator/process process-fn}))

(defn create [{:keys [provider-ns] :as metadata}]
  (let [s (l/load-fn provider-ns 'supports?)
        p (l/load-fn provider-ns 'process)]
    (wrap-metadata metadata s p)))
  
(defn create-native [{:keys [native] :as metadata}]
  (let [classname (:classname native)
        instance (l/load-class classname (:init-args native))
        s (l/method classname 'supports? instance)
        p (l/method classname 'process instance)]
    (wrap-metadata metadata s p)))

; Check metadata for protocol fn binding
(defn operator? [o])


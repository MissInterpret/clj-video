(ns clj-video.container
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.load :as l]))

(def info-val `clj-video.protocol.container/info)
(def data-val `clj-video.protocol.container/data)

(defn container? [c]
  (let [metadata (meta c)] 
    (and (contains? metadata info-val) (contains? metadata data-val))))

(defn wrap-metadata [metadata info-fn data-fn]
  (with-meta metadata {info-val info-fn 
                       data-val data-fn}))

(defn create [{:keys [provider-ns] :as metadata}]
  (let [i (l/load-fn provider-ns 'info)
        d (l/load-fn provider-ns 'data)]
    (wrap-metadata metadata i d)))

(defn create-native [{:keys [native] :as metadata}]
  (let [classname (:classname native)
        instance (l/class classname (:init-args native))
        i (l/method classname 'info instance)
        d (l/method classname 'data instance)]
    (wrap-metadata metadata i d)))

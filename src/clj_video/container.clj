(ns clj-video.container
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.load :as l]))

(def info-val `clj-video.protocol.container/info)
(def data-val `clj-video.protocol.container/data)

(defn container? [c]
  (let [metadata (meta c)] 
    (and (contains? metadata info-val) (contains? metadata data-val))))


(defn validating-info [info-fn container]
  {:pre [(container? container)]}
  (info-fn container))

(defn validating-data [data-fn container range]
  {:pre [(container? container)]}
  (data-fn container range))

(defn wrap-metadata [metadata info-fn data-fn]
  (with-meta metadata {info-val (partial validating-info info-fn) 
                       data-val (partial validating-data data-fn)}))

(defn create [{:keys [provider-ns] :as metadata}]
  (let [i (l/load-fn provider-ns 'info-fn)
        d (l/load-fn provider-ns 'data-fn)]
    (wrap-metadata metadata i d)))

(defn create-native [{:keys [native] :as metadata}]
  (let [classname (:classname native)
        instance (l/class classname (:init-args native))
        i (l/method classname 'info-fn instance)
        d (l/method classname 'data-fn instance)]
    (wrap-metadata metadata i d)))

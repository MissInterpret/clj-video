(ns clj-video.operator
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.load :as l]
            [clj-video.container :as c]))

(def supports-val `clj-video.protocol.operator/supports?)
(def process-val  `clj-video.protocol.operator/process!)

(defn operator? [o]
  (let [metadata (meta o)]
    (and (contains? metadata supports-val) (contains? metadata process-val))))

(defn validating-supports? [supports-fn operator container]
  {:pre [(and (operator? operator) (c/container? container))]
   :post [(boolean? %)]}
  (supports-fn operator container))

(defn validating-process! [process-fn operator containers]
  {:pre [(and (operator? operator) (every? #(c/container? %) containers))]}
  (process-fn operator containers))
 
(defn wrap-metadata [metadata support-fn process-fn]
  (with-meta metadata {supports-val (partial validating-supports? support-fn)
                       process-val (partial validating-process! process-fn)}))

(defn create [{:keys [provider-ns] :as metadata}]
  (let [s (l/load-fn provider-ns 'supports-fn)
        p (l/load-fn provider-ns 'process-fn)]
    (wrap-metadata metadata s p)))
  
(defn create-native [{:keys [native] :as metadata}]
  (let [classname (:classname native)
        instance (l/class classname (:init-args native))
        s (l/method classname 'supports-fn instance)
        p (l/method classname 'process-fn instance)]
    (wrap-metadata metadata s p)))



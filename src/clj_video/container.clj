(ns clj-video.container
  (:gen-class)
  (:require [clojure.spec.alpha :as s]
            [clj-video.load :as l]
            [clj-video.protocol.container :as p]
            [clj-video.spec.container :as spec]))

(defn wrap-metadata [metadata format-fn info-fn data-fn]
  (with-meta {:metadata metadata} {`clj-video.protocol.container/format format-fn
                                   `clj-video.protocol.container/info   info-fn 
                                   `clj-video.protocol.container/data   data-fn}))


(defn create [metadata provider-ns]
  (let [f (l/load-fn provider-ns 'format)
        i (l/load-fn provider-ns 'info)
        d (l/load-fn provider-ns 'data)]
    (wrap-metadata metadata f i d)))

(defn create-native [metadata classname init-args]
  (let [instance (l/load-class classname init-args)
        f (l/method classname 'format instance)
        i (l/method classname 'info instance)
        d (l/method classname 'data instance)]
    (wrap-metadata metadata f i d)))

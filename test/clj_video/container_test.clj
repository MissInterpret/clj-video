(ns clj-video.container-test
  (:require [clojure.test :refer :all]
            [clj-video.protocol.container :as p]
            [clj-video.container :as c]))

(defn info-fn [this]
  {:info true})

(defn data-fn [this range]
  {:data range})

(defn create-container []
  (let [create-metadata {:provider-ns 'clj-video.container-test}]
    (c/create create-metadata)))

(deftest create
  (let [c (create-container)]
    (is (c/container? c))
    (is (:info (p/info c)))
    (is (= 5 (:data (p/data c 5))))))

(deftest create-native
  (let [create-metadata {:native {:classname "come.fully.qualified.TestClassname" :init-args {}}}
        c (c/create-native create-metadata)]
    (is false)))



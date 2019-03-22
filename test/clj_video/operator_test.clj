(ns clj-video.operator-test
  (:require [clojure.test :refer :all]
            [clj-video.container-test :as c]
            [clj-video.protocol.operator :as p]
            [clj-video.operator :as o]))

(defn supports-fn [this container]
  true)

(defn process-fn [this containers]
  {:processed true})

(deftest create
  (testing "Pre test failure"
    (let [create-metadata {:provider-ns 'clj-video.operator-test}
          o (o/create create-metadata)] 
      (is (o/operator? o))
      (is (p/supports? o (c/create-container)))
      (is (:processed (p/process! o [(c/create-container)]))))))

(deftest create-native
  (is false))

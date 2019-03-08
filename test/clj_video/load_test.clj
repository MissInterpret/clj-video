(ns clj-video.load-test
  (:require [clojure.test :refer :all]
            [clj-video.load :as load]))

(defn test-fn [t]
  (keyword t))

(deftest load-fn-test
  (let [ns-symbol 'clj-video.load-test
        var-symbol 'test-fn
        fn-var (load/load-fn ns-symbol var-symbol)]
    (is (= :test (fn-var "test")))))

(deftest class-method-test
  (testing "Load class"
    (is false))
  (testing "Call method"
    (is false)))


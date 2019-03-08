(ns clj-video.load)

(defn load-fn [ns-symbol var-symbol]
  (require ns-symbol)
  (-> ns-symbol
      (find-ns)
      (ns-resolve var-symbol)
      (var-get)))


;; Native ----------------------------------------------
;;

(defn class [classname init-args])

(defn method [classname method-symbol instance]
  "Returns a function which will proxy to a method
   on the given instance. "
  ; TODO: Check that this syntax is correct for a variable-arity pass-through
  (fn [& args]
    (let [method ()]
      #(method args))))

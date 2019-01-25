(ns clj-video.load)

(defn load-fn [ns-symbol var-symbol]
  (require ns-symbol)
  (-> ns-symbol
      (find-ns)
      (ns-resolve var-symbol)
      (var-get)))


;; Native ----------------------------------------------
;;
;; Design thought
;;
;; All native methods will take one argument that is 
;; a map. There can be helper functions for native
;; manipulation of args. (say in C/C++)
;;

(defn load-class [classname init-args])

(defn method [classname method-symbol instance]
  "Returns a function which will proxy to a method
   on the given instance. "
  ; TODO: Check that this syntax is correct for a variable-arity pass-through
  (fn [& args]
    (let [method ()]
      #(method args))))

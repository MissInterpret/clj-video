(ns clj-video.protocol.operator)

(defprotocol Operator
  :extend-via-metadata true
  (supports? [this container])
  (process! [this containers]))

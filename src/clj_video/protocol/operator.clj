(ns clj-video.protocol.operator)


(defprotocol Operator
  :extend-via-metadata true
  (process [instance containers]))


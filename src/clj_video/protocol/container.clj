(ns clj-video.protocol.container)

(defprotocol Container
  :extend-via-metadata true
  (info [this])
  (data [this range]))

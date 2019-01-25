(ns clj-video.protocol.container)

(defprotocol Container
  :extend-via-metadata true
  (format [this])
  (info [this])
  (data [this range]))

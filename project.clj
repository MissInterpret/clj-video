(defproject clj-video "0.1.0-SNAPSHOT"
  :plugins [[lein-tools-deps "0.4.1"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files ["deps.edn"]}
  :profiles {:uberjar {:aot :all}})

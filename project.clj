(defproject clj-video "0.1.0-SNAPSHOT"
  :plugins [[lein-tools-deps "0.4.1"]]
  :middleware [lein-tools-deps.plugin/resolve-dependencies-with-deps-edn]
  :lein-tools-deps/config {:config-files ["deps.edn"]}
  :repl-options {:init-ns user
                 :catch clj-stacktrace.core/pst+}
  :source-paths ["src"]
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev"]
                   :dependencies [[clj-stacktrace "0.2.8"]]}})

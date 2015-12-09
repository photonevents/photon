(defproject tranchis/photon "0.9.2"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :repositories [["muoncore" "http://dl.bintray.com/muoncore/muon-java"]
                 ["reactor" "http://repo.spring.io/libs-release"]]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [http-kit "2.1.19"]
                 [ring "1.4.0"
                  :exclusions [org.clojure/tools.reader]]
                 [ring/ring-json "0.4.0"
                  :exclusions [org.clojure/tools.reader]]
                 [tranchis/photon-db "0.9.31"]
                 [org.clojure/tools.logging "0.3.1"]
                 [tranchis/core.async "0.3.0-20151110.100912-1"]
                 [org.marianoguerra/clj-rhino "0.2.2"
                  :exclusions [org.mozilla/rhino]]
                 [cheshire "5.5.0"]
                 [clj-time "0.11.0"]
                 [compojure "1.4.0"
                  :exclusions [org.clojure/tools.reader
                               commons-codec]]
                 [serializable-fn "1.1.4"]
                 [tranchis/photon-config "0.9.31"]
                 [io.muoncore/muon-clojure "6.0.3"]
                 [prismatic/schema "1.0.3"]
                 [metosin/ring-http-response "0.6.5"
                  :exclusions [org.clojure/tools.reader]]
                 [tranchis/compojure-api "0.24.1"
                  :exclusions [org.clojure/core.memoize
                               com.cognitect/transit-clj
                               com.cognitect/transit-java
                               org.clojure/tools.reader
                               org.clojure/test.check
                               org.clojure/core.cache
                               org.clojure/data.priority-map]]
                 [dire "0.5.3"]
                 [jarohen/chord "0.6.0"
                  :exclusions [commons-codec
                               org.clojure/core.async
                               com.cognitect/transit-clj
                               com.cognitect/transit-cljs
                               com.cognitect/transit-java
                               org.clojure/tools.reader]]
                 [org.slf4j/slf4j-log4j12 "1.7.12"]
                 [tranchis/clj-schema-inspector "0.2.0"]
                 [com.stuartsierra/component "0.3.0"]
                 ;; clojurescript
                 [org.clojure/clojurescript "1.7.170"
                  :exclusions [org.clojure/tools.reader]]
                 [tailrecursion/cljson "1.0.7"]
                 [cljs-http "0.1.37"
                  :exclusions [com.cognitect/transit-cljs
                               org.clojure/core.async]]
                 [org.omcljs/om "1.0.0-alpha23"]
                 [jayq "2.5.4"]
                 [fipp "0.6.2"]
                 [reagent-utils "0.1.4"]
                 ;; photon plugins
                 [io.github.lukehutch/fast-classpath-scanner "1.9.7"]
                 [congomongo "0.4.6"]
                 #_[tranchis/photon-riak "0.9.31"]
                 [tranchis/photon-cassandra "0.9.40"
                  :exclusions [org.clojure/tools.reader
                               com.taoensso/encore]]
                 [tranchis/photon-hazelcast "0.9.40"
                  :exclusions [com.cognitect/transit-clj]]
                 [tranchis/photon-redis "0.9.40"]
                 #_[tranchis/photon-mongo "0.9.31"]
                 [tranchis/photon-file "0.9.31"
                  :exclusions [commons-codec
                               org.clojure/tools.reader
                               com.cognitect/transit-clj]]]
  :ring {:handler photon.core/figwheel-instance
         :init photon.core/figwheel-init!}
  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-midje "3.1.3"]
            [lein-ring "0.9.7"]
            [lein-figwheel "0.5.0-1"
             :exclusions [org.clojure/clojure
                          org.codehaus.plexus/plexus-utils]]]
  :main photon.core ;; http-kit
  #_#_:warn-on-reflection true
  :jvm-opts [#_"-Xmx4g"
             #_"-XX:+PrintGCDetails"
             #_"server"
             "-dsa" "-d64" "-da" "-XX:+UseConcMarkSweepGC"
             "-XX:+UseParNewGC" "-XX:ParallelCMSThreads=4"
             "-XX:+ExplicitGCInvokesConcurrent"
             "-XX:+CMSParallelRemarkEnabled"
             "-XX:-CMSIncrementalPacing"
             "-XX:+UseCMSInitiatingOccupancyOnly"
             "-XX:CMSIncrementalDutyCycle=100"
             "-XX:CMSInitiatingOccupancyFraction=90"
             "-XX:CMSIncrementalSafetyFactor=10"
             "-XX:+CMSClassUnloadingEnabled" "-XX:+DoEscapeAnalysis"]
  :figwheel {:server-port 3000
             :load-warninged-code true
             :open-file-command "atom"
             :ring-handler photon.core/figwheel-init!}
  :cljsbuild
  {:builds [{:source-paths ["src-cljs"]
             :figwheel true
             :compiler {:main photon.ui.frontend
                        :asset-path "ui/js/out"
                        :output-to "resources/public/ui/js/main.js"
                        :output-dir "resources/public/ui/js/out"
                        :source-map "resources/public/ui/js/out.js.map"
                        :preamble ["react/react.min.js"]
                        :optimizations :none
                        :pretty-print  true}}]}
  :docker {:image-name "myregistry.example.org/myimage"
           :dockerfile "target/dist/Dockerfile"
           :build-dir  "target"}
  :aot :all
  :profiles
  {:repl {:dependencies [[midje "1.7.0"]]}
   :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})

(defproject clojureweb "0.1.0-SNAPSHOT"
  :description "This is an example of how to creat a Web application with Clojure"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [cheshire "5.3.1"]
                 [org.clojure/tools.nrepl "0.2.3"]
                 [com.cemerick/drawbridge "0.0.6" :exclusions [org.clojure/tools.nrepl]]
                 [ring-basic-authentication "1.0.1"]
                 [org.clojure/tools.trace "0.7.6"]
                 [clj-http "0.7.8"]
                 [org.clojure/data.json "0.2.4"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [clj-http "0.7.8"]]
  :plugins [[lein-ring "0.8.10"] [lein-junit "1.1.2"]
            [lein-midje "3.0.0"]]
  :min-lein-version "2.0.0"
  :source-paths ["src/main/clojure"]
  :java-source-paths ["src/main/java" "test/main/java"]
  :javac-options ["-target" "1.7" "-source" "1.7"]
  :test-paths ["test/main/clojure"]
  :junit ["test/main/java"]
  :ring {:handler clojureweb.handler/app}
  :main clojureweb.handler
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]
                                  [midje "1.6.0"]
                                  [junit/junit "4.11"]]}
             :production {:dependencies [[javax.servlet/servlet-api "2.5"]] }})

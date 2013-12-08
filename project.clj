(defproject clojureweb "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-jetty-adapter "1.1.6"]
                 [compojure "1.1.5"]
                 [de.ubercode.clostache/clostache "1.3.1"]
                 [com.cemerick/drawbridge "0.0.6"]
                 [ring-basic-authentication "1.0.1"]]
  :main clojureweb.core)

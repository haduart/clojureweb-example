(ns clojureweb.handler
  (:import [clojureweb Foo])
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [cheshire.core :refer :all ]
            [cheshire.generate :refer [add-encoder encode-str remove-encoder]]
            [ring.util.response :as resp]
            [cemerick.drawbridge :as drawbridge]
            [ring.middleware.params :as params]
            [ring.middleware.keyword-params :as keyword-params]
            [ring.middleware.nested-params :as nested-params]
            [ring.middleware.session :as session]
            [ring.middleware.basic-authentication :as basic]
            [ring.adapter.jetty :as jetty]
            [clojure.data.json :as json]))

(def drawbridge-handler
  (-> (drawbridge/ring-handler)
    (keyword-params/wrap-keyword-params)
    (nested-params/wrap-nested-params)
    (params/wrap-params)
    (session/wrap-session)))

(defn wrap-drawbridge [handler]
  (fn [req]
    (if (= "/repl" (:uri req))
      (drawbridge-handler req)
      (handler req))))


(defn json-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/write-str data)
   })

(defroutes app-routes
  (GET "/api/test/" [] (json-response {:response "response"}))
  (route/resources "/")
  (route/not-found (resp/resource-response "404.html" {:root "public"})))

(defn wrap-dir-index
  "Middleware to force request for / to return index.html"
  [handler] (fn [req] (handler (update-in req [:uri ] #(if (= "/" %) "/index.html" %)))))


(defn execute-routing [] (-> app-routes
                           wrap-drawbridge
                           wrap-dir-index))

(def app
  (handler/site (execute-routing)))

(defn -main [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT")))]
    (jetty/run-jetty (execute-routing)
      {:port port :join? false})))

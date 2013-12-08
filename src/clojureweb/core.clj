(ns clojureweb.core
  (:use
    [compojure.core]
    [ring.middleware.session.cookie])
  (:require
    [compojure.route :as route]
    [ring.adapter.jetty :as jetty]
    [cemerick.drawbridge :as drawbridge]
    [ring.middleware.params :as params]
    [ring.middleware.keyword-params :as keyword-params]
    [ring.middleware.nested-params :as nested-params]
    [ring.middleware.session :as session]
    [ring.middleware.basic-authentication :as basic]))

;(defn foo
;  "I don't do a whole lot."
;  [x]
;  (println x "Hello, World 2!"))

;(defn mean
;([values](mean (rest values) (first values)))
;([values result]
;  (if (empty? values)
;    (reverse result)
;    (mean (rest values) (+ result (first values)))
;  )))

;(defn test2
;  ([] (println "primera condicio"))
;  ([x] (println "segona condicio")))

(def drawbridge-handler
  (-> (cemerick.drawbridge/ring-handler)
    (keyword-params/wrap-keyword-params)
    (nested-params/wrap-nested-params)
    (params/wrap-params)
    (session/wrap-session)))

(defn wrap-drawbridge [handler]
  (fn [req]
    (if (= "/repl" (:uri req))
      (drawbridge-handler req)
      (handler req))))


(defroutes main-routes
  (let [nrepl-handler (cemerick.drawbridge/ring-handler)]
    (ANY "/repl" request (nrepl-handler request)))
  (GET "/" [] "Hello World 2")
  (GET "/test" [] "test")
  (GET "/test2" [] "this is another test")
  )

;(defn -main []
;  (jetty/run-jetty main-routes {:port 5000}))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty (wrap-drawbridge main-routes) {:port port})))

;(defn -main [& [port]]
;  (let [port (Integer. (or port (System/getenv "PORT")))]
;    (jetty/run-jetty (wrap-drawbridge main-routes)
;      {:port port :join? false})))

;(defn -main []
;  (jetty/run-jetty
;    (fn [req] {:status 200 :body "Hello World 2"})
;    {:port 5000}))
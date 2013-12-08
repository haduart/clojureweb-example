(ns clojureweb.core
  (:use compojure.core)
  (:require
    [compojure.route :as route]
    [ring.adapter.jetty :as jetty]))

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

(defroutes main-routes
  (GET "/" [] "Hello World 2")
  (GET "/test" [] "test")
  (GET "/test2" [] "this is another test")
  )

;(defn -main []
;  (jetty/run-jetty main-routes {:port 5000}))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty main-routes {:port port})))

;(defn -main []
;  (jetty/run-jetty
;    (fn [req] {:status 200 :body "Hello World 2"})
;    {:port 5000}))
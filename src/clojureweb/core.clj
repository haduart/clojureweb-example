(ns clojureweb.core
  (:use
    [compojure.core])
  (:require
    [compojure.route :as route]
    [ring.adapter.jetty :as jetty]))


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

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (jetty/run-jetty main-routes {:port port})))


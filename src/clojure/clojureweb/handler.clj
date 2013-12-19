(ns clojureweb.handler
  (:import [clojureweb Foo])
  (:use [compojure.core]
        [cheshire.core]
        [ring.util.response]
        [clojureweb.dao :as dao])
  (:require [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [compojure.route :as route]))

;{
;"id" : "some id"
;, "title" : "some title"
;, "text" : "some text"
;}

(defn random-document {:title "this is a title" :text "some text"})

(defroutes app-routes
  (context "/documents" [] (defroutes documents-routes
                             (GET "/" [] (dao/get-all-documents))
                             (GET "/generate" []
                               ((let [document random-document]
                                  dao/create-new-document {:title "this is a title" :text "some text"})))
                             (GET "/java" []
                               (let [foo (Foo.)]
                                 (.saySomething foo)))
                             (POST "/" {body :body} (dao/create-new-document body))
                             (context "/:id" [id] (defroutes document-routes
                                                    (GET "/" [] (dao/get-document id))
                                                    (PUT "/" {body :body} (dao/update-document id body))
                                                    (DELETE "/" [] (dao/delete-document id))))))

  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
    (middleware/wrap-json-body)
    (middleware/wrap-json-response)))
(ns clojureweb.test.handler
  (:use clojure.test
        midje.sweet)
  (:require [clojureweb.handler :as handler]
            [ring.mock.request :as request]
            [clojure.data.json :as json]))


(fact "GET /api/test/ "
  (let [response (delay (handler/app (request/request :get "/api/test/")))]
    "The status should be 200"
    (:status @response) => 200
    "Checking that the body is correct"
    (:body @response) => "{\"response\":\"response\"}"
    ))

;(provided
;  (historian/get-tag-names "BA") => [{"name" "tag1"} {"name" "tag2"}])

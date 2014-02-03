# ClojureWeb Example

In this project you can find an easy basic template that you can use for starting your own projects or learning how to start them.

## Usage
`lein run -m clojureweb.handler 5000`

Or maybe it's deployed in a heroku server:
http://fierce-shore-6716.herokuapp.com/api/test/


What can you find in this project?
* Basic Routing
* Java interoperability
* TDD using Midje
* Set up your project so that it works with Heroku

## Basic Routing
How to set up the ring handler so that you can start with `lein ring server` and also how to set it up as a normal Clojure application, so that it can be started as `lein run -m clojureweb.handler 5000`.
Also I set up a demo GET example that returns a clojure map `{:response "response"}` as a json object.

`(GET "/api/test/" [] (json-response {:response "response"}))`

## Java Interoperability
Check how to set up your project so that it picks up java code that you can invoke from your clojure program. Because unfortunately not everything is in Clojure, yet... ;)

project.clj:
  `:source-paths ["src/main/clojure"]`
  `:java-source-paths ["src/main/java" "test/main/java"]`
  `:javac-options ["-target" "1.7" "-source" "1.7"]`
  `:test-paths ["test/main/clojure"]`
  `:junit ["test/main/java"]`

## TDD using Midje
Because I truly believe in Test Driven Development I've put a basic test that shows how can you test even the handler itself.
Remember, first you have to write the failing test that forces you to implement the production code.
You can keep midje running all tests all the time, so that you can easily detect if you've broken anything.
`lein midje :autotest`


## Set up your project so that it works with Heroku
Deploying a normal Clojure project is easy, but there's a few details that you have to take care of, otherwise you will be trapped in a situation where locally everything compiles and works but, when deploying to heroku it fails dramatically.
Small details like setting up the minimum version for leiningen, a main entry point for your application, or setting up the system properties file if you want to use Java 1.7.


I'll try to keep this project up to date, but check the dependencies anyway:
 org.clojure/clojure "1.5.1"
 compojure "1.1.6"
 cheshire "5.3.1"
 org.clojure/tools.nrepl "0.2.3"
 com.cemerick/drawbridge "0.0.6"
 ring-basic-authentication "1.0.1"
 clj-http "0.7.8"
 org.clojure/data.json "0.2.4"
 ring/ring-jetty-adapter "1.1.6"
 clj-http "0.7.8"



## License

Eduard Céspedes Borràs
Twitter: [@haduart](http://twitter.com/haduart "Twitter's account")
Blog: [haduart.com](http://haduart.com)
Copyright © 2014

Distributed under the Eclipse Public License, the same as Clojure.

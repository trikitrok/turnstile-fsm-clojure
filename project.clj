(defproject turnstile "0.0.1-SNAPSHOT"
  :description "A simple FSM in Clojure to practice outside-in TDD with Midje."
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.3.1"]]
  :profiles {:dev {:dependencies [[midje "1.7.0"]]}
             :midje {}})

(ns turnstile.core-test
  (:require
    [midje.sweet :refer :all]
    [midje.open-protocols :refer [defrecord-openly]]
    [turnstile.core :refer [pass]]
    [turnstile.control :as control]))

(unfinished sound-alarm!)

(defrecord-openly FakeTurnstileController []
  control/TurnstileController
  (sound-alarm! [this]))

(facts
  "about turnstile"

  (facts
    "when locked"
    (fact
      "passing makes the alarm sound"
      (let [fake-controller (->FakeTurnstileController)]

        (pass :locked fake-controller) => :locked

        (provided
          (sound-alarm! fake-controller) => irrelevant :times 1)))))

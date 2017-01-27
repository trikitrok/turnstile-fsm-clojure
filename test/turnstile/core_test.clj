(ns turnstile.core-test
  (:require
    [midje.sweet :refer :all]
    [midje.open-protocols :refer [defrecord-openly]]
    [turnstile.core :refer [pass insert-coin]]
    [turnstile.control :as control]))

(unfinished sound-alarm!)
(unfinished store!)
(unfinished unlock!)
(unfinished lock!)

(defrecord-openly FakeTurnstileController []
  control/TurnstileController
  (sound-alarm! [this])
  (store! [this amount])
  (unlock! [this])
  (lock! [this]))

(facts
  "about a turnstile"

  (facts
    "when locked"
    (fact
      "passing makes the alarm sound"
      (let [fake-controller (->FakeTurnstileController)]

        (pass :locked fake-controller) => :locked

        (provided
          (sound-alarm! fake-controller) => irrelevant :times 1)))

    (fact
      "inserting a coin unlocks the turnstile"
      (let [fake-controller (->FakeTurnstileController)]
        (insert-coin :locked ..some-amount.. fake-controller) => :unlocked

        (provided
          (unlock! fake-controller) => irrelevant :times 1
          (store! fake-controller ..some-amount..) => irrelevant :times 1))))

  (facts
    "when unlocked"
    (fact
      "passing locks the turnstile"
      (let [fake-controller (->FakeTurnstileController)]
        (pass :unlocked fake-controller) => :locked

        (provided
          (lock! fake-controller) => irrelevant :times 1)))))

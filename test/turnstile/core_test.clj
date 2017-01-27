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
(unfinished thank-you!)

(defrecord-openly FakeTurnstile []
  control/Turnstile
  (sound-alarm! [this])
  (store! [this amount])
  (unlock! [this])
  (lock! [this])
  (thank-you! [this]))

(facts
  "about a turnstile"

  (facts
    "when locked"
    (fact
      "passing makes the alarm sound"
      (let [turnstile (assoc (->FakeTurnstile) :state :locked)]
        (:state (pass turnstile)) => :locked

        (provided
          (sound-alarm! turnstile) => irrelevant :times 1)))

    (fact
      "inserting a coin unlocks the turnstile"
      (let [turnstile (assoc (->FakeTurnstile) :state :locked)]
        (:state (insert-coin turnstile ..some-amount..)) => :unlocked

        (provided
          (unlock! turnstile) => irrelevant :times 1
          (store! turnstile ..some-amount..) => irrelevant :times 1))))

  (facts
    "when unlocked"
    (fact
      "passing locks the turnstile"
      (let [turnstile  (assoc (->FakeTurnstile) :state :unlocked)]
        (:state (pass turnstile)) => :locked

        (provided
          (lock! turnstile) => irrelevant :times 1)))

    (fact
      "inserting a coin makes the turnstile thank you"
      (let [turnstile (assoc (->FakeTurnstile) :state :unlocked)]
        (:state (insert-coin turnstile ..some-amount..)) => :unlocked

        (provided
          (thank-you! turnstile) => irrelevant :times 1
          (store! turnstile ..some-amount..) => irrelevant :times 1)))))

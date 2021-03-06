(ns turnstile.fsm-logic-test
  (:require
    [midje.sweet :refer :all]
    [midje.open-protocols :refer [defrecord-openly]]
    [turnstile.fsm-logic :as fsm-logic]))

(unfinished sound-alarm!)
(unfinished store!)
(unfinished unlock!)
(unfinished lock!)
(unfinished thank-you!)

(defrecord-openly FakeTurnstile []
  fsm-logic/TurnstileActions
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
        (fsm-logic/locked? (fsm-logic/pass turnstile)) => true

        (provided
          (sound-alarm! turnstile) => irrelevant :times 1)))

    (fact
      "inserting a coin unlocks the turnstile"
      (let [turnstile (assoc (->FakeTurnstile) :state :locked)]
        (fsm-logic/unlocked? (fsm-logic/insert-coin turnstile ..some-amount..)) => true

        (provided
          (unlock! turnstile) => irrelevant :times 1
          (store! turnstile ..some-amount..) => irrelevant :times 1))))

  (facts
    "when unlocked"
    (fact
      "passing locks the turnstile"
      (let [turnstile  (assoc (->FakeTurnstile) :state :unlocked)]
        (fsm-logic/locked? (fsm-logic/pass turnstile)) => true

        (provided
          (lock! turnstile) => irrelevant :times 1)))

    (fact
      "inserting a coin makes the turnstile thank you"
      (let [turnstile (assoc (->FakeTurnstile) :state :unlocked)]
        (fsm-logic/unlocked? (fsm-logic/insert-coin turnstile ..some-amount..)) => true

        (provided
          (thank-you! turnstile) => irrelevant :times 1
          (store! turnstile ..some-amount..) => irrelevant :times 1)))))

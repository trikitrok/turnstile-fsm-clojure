(ns turnstile.fsm-logic)

(defprotocol Turnstile
  (sound-alarm! [this])
  (store! [this amount])
  (unlock! [this])
  (lock! [this])
  (thank-you! [this]))

(defn locked? [turnstile]
  (= (:state turnstile) :locked))

(def unlocked? (complement locked?))

(defn pass [turnstile]
  (if (locked? turnstile)
    (sound-alarm! turnstile)
    (lock! turnstile))
  (assoc turnstile :state :locked))

(defn insert-coin [turnstile amount]
  (store! turnstile amount)
  (if (locked? turnstile)
    (unlock! turnstile)
    (thank-you! turnstile))
  (assoc turnstile :state :unlocked))

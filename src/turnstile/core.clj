(ns turnstile.core
  (:require
    [turnstile.control :as control]))

(defn- locked? [turnstile]
  (= (:state turnstile) :locked))

(defn pass [turnstile]
  (if (locked? turnstile)
    (control/sound-alarm! turnstile)
    (control/lock! turnstile))
  (assoc turnstile :state :locked))

(defn insert-coin [turnstile amount]
  (control/store! turnstile amount)
  (if (locked? turnstile)
    (control/unlock! turnstile)
    (control/thank-you! turnstile))
  (assoc turnstile :state :unlocked))

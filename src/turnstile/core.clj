(ns turnstile.core
  (:require
    [turnstile.control :as control]))

(defn pass [turnstile]
  (if (= (:state turnstile) :locked)
    (control/sound-alarm! turnstile)
    (control/lock! turnstile))
  (assoc turnstile :state :locked))

(defn insert-coin [turnstile amount]
  (control/store! turnstile amount)
  (if (= (:state turnstile) :locked)
    (control/unlock! turnstile)
    (control/thank-you! turnstile))
  (assoc turnstile :state :unlocked))

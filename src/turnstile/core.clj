(ns turnstile.core
  (:require
    [turnstile.control :as control]))

(defn pass [turnstile]
  (if (= (:state turnstile) :locked)
    (control/sound-alarm! turnstile)
    (control/lock! turnstile))
  (assoc turnstile :state :locked))

(defn insert-coin [state amount controller]
  (control/store! controller amount)
  (if (= state :locked)
    (control/unlock! controller)
    (control/thank-you! controller))
  :unlocked)

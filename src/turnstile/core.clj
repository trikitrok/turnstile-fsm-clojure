(ns turnstile.core
  (:require
    [turnstile.control :as control]))

(defn pass [state controller]
  (if (= state :locked)
    (control/sound-alarm! controller)
    (control/lock! controller))
  :locked)

(defn insert-coin [state amount controller]
  (control/store! controller amount)
  (if (= state :locked)
    (control/unlock! controller)
    (control/thank-you! controller))
  :unlocked)

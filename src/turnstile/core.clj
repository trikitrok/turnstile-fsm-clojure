(ns turnstile.core
  (:require
    [turnstile.control :as control]))

(defn pass [state controller]
  (control/sound-alarm! controller)
  :locked)

(defn insert-coin [state amount controller]
  (control/store! controller amount)
  (control/unlock! controller)
  :unlocked)

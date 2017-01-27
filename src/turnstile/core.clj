(ns turnstile.core
  (:require
    [turnstile.control :as control]))

(defn pass [state controller]
  (control/sound-alarm! controller)
  :locked)

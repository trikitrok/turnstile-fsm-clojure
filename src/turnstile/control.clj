(ns turnstile.control)

(defprotocol TurnstileController
  (sound-alarm! [this])
  (store! [this amount])
  (unlock! [this])
  (lock! [this]))

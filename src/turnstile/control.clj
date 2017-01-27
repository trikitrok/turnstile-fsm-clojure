(ns turnstile.control)

(defprotocol TurnstileController
  (sound-alarm! [this]))

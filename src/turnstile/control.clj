(ns turnstile.control)

(defprotocol Turnstile
  (sound-alarm! [this])
  (store! [this amount])
  (unlock! [this])
  (lock! [this])
  (thank-you! [this]))

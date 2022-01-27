(ns quizzard.events)

(def app-state (atom {}))

(defn fire-event
  "Accepts an event object and executes the respective function associated with that event type. Returns
   a map of state changes. (Impure function - prints to console)"
  [event]
    (case (event :type)
      :start-event      (do
                          (println (str "Application start event occurred. Payload: " (event :payload)))
                          (swap! app-state merge {:app-running true, :current-route :welcome}))

      :navigation-event (do
                          (println (str "Navigation event occurred. Payload: " (event :payload)))
                          (swap! app-state merge {:current-route (event :payload)}))

      :login-event      (do
                          (println "Login event occurred! Payload:" (event :payload))
                          (swap! app-state merge {:current-user {:username "wsingleton"}, :current-route :dashboard}))

      :register-event   (do
                          (println "Register event occurred! Payload:" (event :payload))
                          (swap! app-state merge {:current-user {:username "wsingleton"}, :current-route :dashboard}))

      :exit-event       (do
                          (println "\nExiting application.")
                          (swap! app-state assoc :app-running false))))

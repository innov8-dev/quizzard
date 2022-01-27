(ns quizzard.core
  (:require [quizzard.events :refer [fire-event app-state]]
            [quizzard.dsl :refer [render-component]]
            [quizzard.components.welcome :refer [welcome-component]]
            [quizzard.components.login :refer [login-component]]
            [quizzard.components.register :refer [register-component]]))

(def routes
  {:welcome welcome-component
   :login login-component
   :register register-component})

(defn -main
  [& args]
    (fire-event {:type :start-event, :payload args})
    (while (@app-state :app-running)
      (let [next-route (@app-state :current-route)]
        (if (nil? (routes next-route))
          (do
            (println "Invalid route specified. Navigating back to Welcome screen.")
            (fire-event {:type :navigation-event, :payload :welcome}))
          (do
            (render-component (routes next-route))))))
    @app-state)

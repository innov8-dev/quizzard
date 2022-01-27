(ns quizzard.components.welcome
  (:use [quizzard.dsl]
        [quizzard.events]))

(defn handle-welcome-selection
  "Uses the provided welcome component state to handle the user's menu selection. Returns a
   state map that maybe different from the original if any errors occurred during handling.
   (Impure function - due to calls to the impure fire-event function)"
  [welcome-state]
  (let [mutable-state (atom welcome-state)]
    (case (welcome-state :selection)
      "1" (fire-event {:type :navigation-event, :payload :login})
      "2" (fire-event {:type :navigation-event, :payload :register})
      "3" (fire-event {:type :exit-event})
      (do
        (println "Invalid selection. Please try again.\n")
        (swap! mutable-state assoc :error "Invalid selection.")))
    @mutable-state))

(def welcome-component
  "Welcome component elements"
  [(header "Welcome to Quizzard!")
   (paragraph "Please make a selection from the menu-below:")
   (item-list true "Login" "Register" "Exit")
   (input-field ">" :selection (fn [v] (re-matches #"[0-9]{1,3}" v)))
   (and-then handle-welcome-selection)])

(defn render
  []
    (render-component welcome-component))

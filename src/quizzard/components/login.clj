(ns quizzard.components.login
  (:use [quizzard.dsl]
        [quizzard.events]
        [quizzard.util]
        [quizzard.validators]))

(defn validate-login-input
  "Uses the provided login component state to attempt a login-event. An event will only fire if the
   provided credentials are not nil. Returns a state map that maybe different from the original if
   any errors occurred during handling. (Impure function - can potentially change app-state)"
  [login-state]
  (let [mutable-state (atom login-state)
        un (login-state :username-val)
        pw (login-state :password-val)]
    (if (no-nil? [un pw])
      (do
        (fire-event {:type :login-event, :payload {:username un :password pw}})
        (fire-event {:type :navigation-event, :payload :dashboard}))

      (do
        (let [err-msg "Invalid credentials provided."]
          (println err-msg)
          (swap! mutable-state assoc :error err-msg)
          (fire-event {:type :navigation-event, :payload :welcome}))))
    @mutable-state))

(def login-component
  "Login component elements"
  [(header "Log into your Quizzard account!")
   (paragraph "Please provide your account credentials below.")
   (input-field "Username" :username-val meets-username-reqs?)
   (input-field "Password" :password-val meets-password-reqs?)
   (and-then validate-login-input)])

(ns quizzard.components.register
  (:use [quizzard.dsl]
        [quizzard.events]
        [quizzard.util]
        [quizzard.validators]))

(defn validate-register-input
  "Uses the provided register component state to attempt a login-event. An event will only fire if both
   all provided registration details are not nil. Returns a state map that maybe different from the original
   if any errors occurred during handling. (Impure function - can potentially change app-state)"
  [register-state]
  (let [mutable-state (atom register-state)
        first (register-state :firstname-val)
        last (register-state :lastname-val)
        email (register-state :email-val)
        un (register-state :username-val)
        pw (register-state :password-val)]
    (if (no-nil? [first last email un pw])
      (do
        (fire-event {:type :register-event, :payload {:firstname first :lastname last :email email :username un :password pw}})
        (fire-event {:type :navigation-event, :payload :dashboard}))

      (do
        (let [err-msg "Invalid registration details provided. Returning to welcome screen..."]
          (println err-msg)
          (swap! mutable-state assoc :error err-msg)
          (fire-event {:type :navigation-event, :payload :welcome}))))
    @mutable-state))

(def register-component
  "Register component elements."
  [(header "Register for a Quizzard account!")
   (paragraph "Please provide your account information below.")
   (input-field "First name" :firstname-val not-blank?)
   (input-field "Last name" :lastname-val not-blank?)
   (input-field "Email" :email-val meets-email-reqs?)
   (input-field "Username" :username-val meets-username-reqs?)
   (input-field "Password" :password-val meets-password-reqs?)
   (and-then validate-register-input)])

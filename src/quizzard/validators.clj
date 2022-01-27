(ns quizzard.validators)

(defn meets-email-reqs?
  "Simple email validator that checks that a provided email has some characters preceding a '@' symbol, and more
   characters containing a '.' symbol follow afterword. (Pure function)"
  [email]
  (and (string? email) (re-matches #".+\@.+\..+" email)))

(defn meets-username-reqs?
  "A username must have between 8 and 24 characters that can include any lower case character or digit
  (number). Only the underscore and dash special characters can be used. (Pure function)"
  [un]
  (re-matches #"^[a-z0-9_-]{8,24}$" un))

(defn meets-password-reqs?
  "A password must have between 8 and 24 characters and include at least one digit, one upper case letter,
  one lower case letter and one of the following special symbols: @#$% (Pure function)"
  [pw]
  (re-matches #"((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,24})" pw))

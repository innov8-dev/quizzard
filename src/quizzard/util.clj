(ns quizzard.util
  (:require [clojure.string :as str]))

(defn any-nil?
  "Returns true if any items in the provided collection are nil, otherwise false. (Pure function)"
  [coll]
  (not (not-any? nil? coll)))

(defn no-nil?
  "Returns true only if none of the items in the provided collection are nil, otherwise false. (Pure function)"
  [coll]
  (not-any? nil? coll))

(defn not-blank?
  "Returns true if the provided string is not blank (nil, only whitespace, or non-string), otherwise returns false.
   (Pure function)"
  [name]
  (and (string? name) (not (str/blank? name))))

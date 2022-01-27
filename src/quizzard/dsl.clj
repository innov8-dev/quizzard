(ns quizzard.dsl
  (:require [clojure.string :refer [join]]))

(defn get-user-input
  "Prints a formatted version of the provided label and prompts the user for input. User input is validated
   against a provided validation function if provided, otherwise user input is not validated. Returns either
   the valid user input or nil. (Impure function - prints to console)"
  [label & [vf]]
  (if (nil? vf)
    (get-user-input label (fn [] true))
    (do
      (if (= label ">") (print (str label " ")) (print (str label ": ")))
      (flush)
      (let [input (read-line)]
        (if (vf input) input nil)))))

(defn header
  "Creates a Hiccup-style header element vector. See convert-header and render-element for more
   details on how these elements are processed. (Pure function)"
  [text]
  [:h1 {:value text}])

(defn paragraph
  "Creates a Hiccup-style paragraph element vector. See convert-paragraph and render-element for more
   details on how these elements are processed. (Pure function)"
  [text]
  [:p {:value text}])

(defn input-field
  "Creates a Hiccup-style input-field element vector. See process-input-field and render-element for more
   details on how these elements are processed. (Pure function)"
  [label name handler]
  [:input {:label label :name name :handler handler :value nil}])

(defn item-list
  "Creates a Hiccup-style item-list element vector. Ordered items are represented using a vector, whereas
   unordered items are represented using a list. See convert-item-list and render-element for more details
   on how these elements are processed. (Pure function)"
  [is-ordered & items]
  [:list {:ordered is-ordered :items (if is-ordered (into [] items) items)}])

(defn and-then [fun]
  "Creates a Hiccup-style and-then element vector. See render-element for details on how these are
   processed. (Pure function)"
  [:and-then fun])

(defn get-element-name
  "Gets the name attribute associated with a Hiccup-style UI element, if present- otherwise returns nil.
   (Pure function)"
  [element]
  ((element 1) :name))

(defn convert-header
  "Converts a Hiccup-style header element (:h1) to a string with a new line for printing to the console.
   Returns nil if the provided arg is not a header element, as identified by (first header). (Pure function)"
  [header]
  (if (= (first header) :h1)
    (str ((nth header 1) :value) "\n")
    nil))

(defn convert-paragraph
  "Converts a Hiccup-style paragraph element (:p) to a string without a new line for printing to the console.
   Returns nil if the provided arg is not a paragraph element, as identified by (first paragraph). (Pure function)"
  [paragraph]
  (if (= (first paragraph) :p)
    ((nth paragraph 1) :value)
    nil))

(defn process-input-field
  "Processes a Hiccup-style input-field element (:input) to display text that prompts the user for input.
   User input is validated against an optionally provided validator function. If the provided user input
   is valid, then it returned - otherwise, nil is returned. Also, returns nil if the provided arg is not
   an input-field element, as identified by (first input-field). (Pure function)"
  [input-field]
  (if (= (first input-field) :input)
    (get-user-input ((nth input-field 1) :label) ((nth input-field 1) :handler))
    nil))

(defn convert-item-list
  "Converts a Hiccup-style item-list element (:list) to a string with labels (numbers or hyphens, depending
   on if the list is ordered or not (as indicated by the :ordered attribute) Returns nil if the provided arg
   is not an item-list element, as identified by (first list-element). (Pure function)"
  [item-list]
  (if (= (first item-list) :list)
    (let [items ((second item-list) :items)]
      (if ((second item-list) :ordered)
        (let [ordered-items (reduce into {} (map-indexed #(assoc {} %1 %2) items))]
          (join "\n" (map (fn [[k v]] (str (inc k) ") " v)) (seq ordered-items))))
        (join "\n" (map (fn [v] (str "- " v)) items))))
    nil))

(defn render-element
  "Attempts to render a provided Hiccup-style UI element. Header and paragraph elements are simply printed
   to the standard output. Input field labels are printed and then wait for user input. And-then elements
   are functions that will execute taking the component's state as an argument. Elements can appear anywhere
   and will be executed in the order that they are specified. Note: This function is not pure, the provided
   state object is subject to mutation based on the input-field and and-then elements provided. (Impure function -
   prints to console and potential changes provided component-state)"
  [element component-state]
  (case (first element)
    :h1 (println (convert-header element))
    :p (println (convert-paragraph element))
    :input (swap! component-state assoc-in [(get-element-name element)] (process-input-field element))
    :list (println (convert-item-list element))
    :and-then (reset! component-state ((element 1) @component-state))))

(defn map-inputs-to-state
  "Accepts Hiccup-style UI component elements and isolates the input-field elements in order
   to create an atom map containing the :name from each input-field as a key associated with a
   nil value. (Pure function)"
  [elements]
  (let [input-elements (filter (fn [v] (= (v 0) :input)) elements)]
    (atom (reduce #(assoc %1 %2 nil) {} (map (fn [v] ((v 1) :name)) input-elements)))))

(defn render-component
  "Takes Hiccup-style UI component elements and renders them to the standard output. Input fields are
   mapped to a mutable component state map. After component state is instantiated, rendering begins.
   The last element within a component must be an and-then element to indicate what the component should
   do once rendering is complete. Returns an immutable, representing the component's state when rendering
   finished. (Impure function - due to calls to the impure render-element function)"
  [component-elements]
  (let [state (map-inputs-to-state component-elements)]
    (last (for [element component-elements]
            (do
              (render-element element state)
              @state)))))

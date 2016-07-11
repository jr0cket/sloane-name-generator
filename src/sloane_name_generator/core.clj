;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; Project:  Sloane name generator
;;
;; Description:
;; Given a first and last name, return a specific slone name
;;
;; Author:  John Stevenson
;; Twitter: @jr0cket
;; Date:    7th July 2016
;; License: Creative Commons Attribution Share-Alike International 4.0
;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(ns sloane-name-generator.core)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Defining a data structure

;; The following seems to be the simplest way to model the sloane names.  This follows the representation in the original source material.
#_(def sloane-first-name
  {"a" "Ally-Pally"
   "b" "Bongo"
   "c" "Chippers"})

#_(def slone-second-name
  {"a" "Anstruther"
   "b" "Beaufort"
   "c" "Cholmondeley"})

#_(def slone-third-name
  {"a" "Arbuthnot"
   "b" "Battenburg"
   "c" "Coutts"})

;; This data structure design is very simple and consise but does loose some of the semantic meaning.  The positoin of the names is not defined in terms of the contenxt of the problem.
#_(def slone-names
  {:a ["Ally-Pally" "Anstruther" "Arbuthnot"]})

;; this alternative map of maps seems a bit more complex and doesnt reduce the amout of reading
#_(def slone-names
    {:first {:a "Ally-Pally"
             :b "Bongo"
             :c "Chipper"}
     :second {}
     :third  {}})


;; This design removes some of the redundancy in defineing each letter of the alphabet several times.  Apart from less typing and therefore reading by the development teeam, it also explicitly defines the semantic meaning of each name within the context of this problem.
(def slone-names
  {:a {:first "Ally-Pally" :second "Anstruther"   :third "Arbuthnot"}
   :b {:first "Bongo"      :second "Beaufort"     :third "Battenburg"}
   :c {:first "Chippers"   :second "Cholmondeley" :third "Coutts"}})



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Creating the algorithm to construct your sloane name

;; The first sloane name is chosen from the first character of the first name
;; The second sloane name chosen from the first character of the second name
;; The third sloane name is chosen from the second character of the second name

;; you can get the first element of a string by treating it just like a collection.  However this returns a character
(first "Strings also act as collections")

;; a string can be converted to a keyword, a character cannot
(keyword "a")

;; a character can be converted to a string using the str function
(str (first "Strings also act as collections"))

;; the keywords need to be the same case, so convert the first character to lower case (which returns a string, so the explicit str function is no longer required.)
(clojure.string/lower-case (first "Strings also act as collections"))

;; putting it all together.
(keyword (clojure.string/lower-case (first "Strings also act as collections")))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Create a function to calculate your sloane name


(defn sloane-name
  "Given a first and last name as a string, returns your equivalent Sloane name as a string"
  [name]
  (let [first-name  (keyword (clojure.string/lower-case (first (first (clojure.string/split name #" ")))))
        middle-name (keyword (clojure.string/lower-case (first (second (clojure.string/split name #" ")))))
        last-name   (keyword (clojure.string/lower-case (second (second (clojure.string/split name #" ")))))]
    (str (get-in slone-names [first-name  :first])
         " "
         (get-in slone-names [middle-name :second])
         " "
         (get-in slone-names [last-name   :third]))))

;; Testing this works

(sloane-name "Billy Abstainer")

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Comments
;;
;; The slone-name function needs to be refactored as there is a lot of duplicate code in there

;; Then I just need to convert this project to be Sloane-name as a Service...

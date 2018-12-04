(ns advent-of-code.day-1)

(defn numbers []
  (->> (line-seq r)
       (map #(Integer/parseInt %))
       (doall)
       (with-open [r (clojure.java.io/reader (clojure.java.io/resource "day1.txt"))])))

(defn solve-1 []
  (reduce + (numbers)))

(defn solve-2 []
  (->>
   (numbers)
   (cycle)
   (reductions +)
   (reduce (fn [frs x]
             (if (contains? frs x)
               (reduced x)
               (conj frs x))) #{})))

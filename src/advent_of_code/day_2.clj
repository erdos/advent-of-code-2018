(ns advent-of-code.day-2)

(defn words []
  (->> (line-seq r)
       (doall)
       (with-open [r (clojure.java.io/reader (clojure.java.io/resource "day2.txt"))])))

(defn solve-1 []
  (let [xs (frequencies (mapcat (comp set vals frequencies) (words)))]
    (* (xs 2 0) (xs 3 0))))

(defn solve-2 []
  ;; this is a super-naive n2 algo.
  (letfn [(diff [a b] (reduce + (map {true 1 false 0} (map not= a b))))
          (common [v w] (clojure.string/join (filter some? (map #(when (= %1 %2) %1) v w))))]
    (for [w (words)
          v (words)
          :when (= 1 (diff v w))]
      (common v w))))

(ns advent-of-code.day-2)

(defn- words []
  (->> (line-seq r)
       (doall)
       (with-open [r (clojure.java.io/reader (clojure.java.io/resource "day3.txt"))])))

(defn- parse [line]
  (let [f #(Integer/parseInt (str %))
        [_ id x y w h] (re-find #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)" (str line))]
    {:id (f id), :x (f x), :y (f y), :width (f w), :height (f h)}))

(defn- coords [{:keys [x y width height]}]
  (for [x (range x (+ x width))
        y (range y (+ y height))] [x y]))

(defn solve-1 []
  (count (remove #{1} (vals (frequencies (mapcat coords (map parse (words))))))))

; (solve-1)

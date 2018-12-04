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
  (for [w (range width) h (range height)]
    [(+ x w) (+ y h)]))

(defn solve-1 []
  (count (remove #{1} (vals (frequencies (mapcat coords (map parse (words))))))))

;; (solve-1)

(defn- coords-with-id [{:keys [x y width height id]}]
  (for [w (range width) h (range height)]
    {:x (+ x w) :y (+ y h) :id id}))

(defn solve-2 []
  (let [alles (mapcat coords-with-id (map parse (words)))]
    (reduce disj (set (map :id alles))
            (for [[_ ps] (group-by (juxt :x :y) alles)
                  :when (not= 1 (count ps))
                  p ps]
              (:id p)))))

;; (solve-2)

(ns ttt.game)

(defn create-game [{:keys [size]}]
  {:size size
   :next-player :x})

(def next-player
  {:x :o :o :x})

(defn valid-tic?
  [{:keys [size tics]} y x]
  (and (->> [y x] (every? #(<= 0 % size)))
       (not (contains? tics [y x]))))

(defn tic
  [game y x]
  (let [player (:next-player game)]
    (if-not (valid-tic? game y x)
      game
      (-> game
          (assoc-in [:tics [y x]] player)
          (assoc :next-player (next-player player))))))

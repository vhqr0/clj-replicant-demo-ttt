(ns ttt.game)

(defn create-game [{:keys [size]}]
  {:next-player :x
   :size size})

(def next-player
  {:x :o :o :x})

(defn tic [game y x]
  (let [player (:next-player game)]
    (if (get-in game [:tics [y x]])
      game
      (-> game
          (assoc-in [:tics [y x]] player)
          (assoc :next-player (next-player player))))))

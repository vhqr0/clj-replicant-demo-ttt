(ns ttt.core
  (:require [replicant.dom :as r]
            [ttt.game :as game]
            [ttt.ui :as ui]))

(defn main
  []
  (let [store (atom nil)
        el (js/document.getElementById "app")]
    (r/set-dispatch!
     (fn [_ [action & args]]
       (case action
         :tic (apply swap! store game/tic args)
         (prn action args))))
    (add-watch store ::render
               (fn [_ _ _ game]
                 (->> (ui/game->ui-data game)
                      ui/render-board
                      (r/render el))))
    (reset! store (game/create-game {:size 3}))))

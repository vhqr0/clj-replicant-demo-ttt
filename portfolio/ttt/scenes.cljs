(ns ttt.scenes
  (:require [portfolio.replicant :refer-macros [defscene]]
            [portfolio.ui :as portfolio]
            [ttt.ui :as ui]))

(defscene empty-cell
  (ui/render-cell
   {:clickable? true}))

(defscene cell-with-x
  (ui/render-cell
   {:content ui/mark-x}))

(defscene cell-with-o
  (ui/render-cell
   {:content ui/mark-o}))

(defscene interactive-cell
  :params (atom nil)
  [store]
  (ui/render-cell
   {:clickable? true
    :content @store
    :on-click
    (fn [_]
      (swap! store #(if % nil ui/mark-x)))}))

(defscene dimmed-cell
  (ui/render-cell
   {:content ui/mark-o
    :dim? true}))

(defscene highlighted-cell
  (ui/render-cell
   {:content ui/mark-o
    :highlight? true}))

(defscene empty-board
  (ui/render-board
   {:rows [[{} {} {}]
           [{} {} {}]
           [{} {} {}]]}))

(defscene partial-board
  (ui/render-board
   {:rows [[{:content ui/mark-o} {} {}]
           [{:content ui/mark-x} {:content ui/mark-o} {}]
           [{} {} {}]]}))

(defscene winning-board
  (ui/render-board
   {:rows [[{:dim? true}
            {:content ui/mark-o :highlight? true}
            {:dim? true}]
           [{:content ui/mark-x :dim? true}
            {:content ui/mark-o :highlight? true}
            {:dim? true}]
           [{:dim? true}
            {:content ui/mark-o :highlight? true}
            {:content ui/mark-x :dim? true}]]}))

(defn main
  []
  (-> {:config {:css-paths ["/styles.css"]
                :viewport/defaults {:background/background-color "#fdeddd"}}}
      portfolio/start!))

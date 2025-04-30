(ns ttt.game-test
  (:require [clojure.test :refer [deftest is testing]]
            [ttt.game :as game]))

(deftest ttt-test
  (testing "X is the starting player"
    (is (= (game/create-game {:size 3})
           {:next-player :x :size 3})))
  (testing "X places a tic"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0))
           {:size 3
            :tics {[0 0] :x}
            :next-player :o})))
  (testing "O places a tic"
    (is (= (-> (game/create-game {:size 3})
               (game/tic 0 0)
               (game/tic 0 1)))
        {:size 3
         :tics {[0 0] :x [0 1] :o}
         :next-player :x})))

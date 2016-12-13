(ns tgames.sudoku.state
  (:require
      [tgames.sudoku.generator :as gen]
      [reagent.core :as r :refer [atom]]
  )
)

(def sudoku-state

  (r/atom {:board (gen/create-puzzle (gen/create-board) 2) }))

(defn change-number[index n]
  (.log js/console n)
  (swap! sudoku-state update-in [:board] assoc index n)

)

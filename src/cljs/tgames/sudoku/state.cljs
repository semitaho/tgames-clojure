(ns tgames.sudoku.state
  (:require
      [tgames.sudoku.generator :refer [create-board create-puzzle]]
      [reagent.core :as r :refer [atom]]
  )
)

(defn- map-puzzle-num
  [number]
   (if (nil? number) {:number nil :readonly false} {:number number :readonly true})
)

(def sudoku-state
  (r/atom
    (let [board (create-board)]
    {
     :board board
     :game-state "started"
     :timer 0
     :scores []
     :puzzle (apply vector (map map-puzzle-num  (create-puzzle board 1) ))
    })
  ))

(defn change-number[index n]
  (swap! sudoku-state update-in [:puzzle] assoc index {:number n :readonly false} )

)

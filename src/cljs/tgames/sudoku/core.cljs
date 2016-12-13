(ns tgames.sudoku.core
  (:require
    [tgames.sudoku.board :refer [board]]
    [tgames.sudoku.state :refer [sudoku-state] ]
    )
)

(defn sudokuApp[]
  [:div {:class "container"}
    [:h1 "Sudoku"]
      [:div {:class "flex flex-center vh-height"}

         
          (.log js/console (:board @sudoku-state) )
          [board (:board @sudoku-state) ]
      ]


  ]
)

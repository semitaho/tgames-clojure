(ns tgames.sudoku.core
  (:require
    [tgames.sudoku.board :refer [board]]
    [tgames.sudoku.generator :refer [create-board] ]
    )
)

(defn sudokuApp[]
  [:div {:class "container"}
    [:h1 "Sudoku"]
    [:div {:class "row"}
      [:div {:class "col-md-8 col-md-offset-2"}
        [board (create-board) ]
      ]

    ]
  ]
)

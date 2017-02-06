(ns tgames.sudoku.core
  (:require
    [tgames.sudoku.action :refer [change-state start-game fb-initialized] ]
    [tgames.sudoku.board :refer [board]]
    [fb-sdk-cljs.core :as fb]
    [tgames.sudoku.modal :refer [sudoku-modal]]
    [tgames.sudoku.filter :refer [format-time]]
    [tgames.sudoku.action :refer [fetch-scores login-facebook]]

    [tgames.sudoku.state :refer [sudoku-state] ]
    )
)

(defn sudoku-start-screen[]
  [:button {:class "btn btn-large" :on-click start-game   } "Start new sudoku" ]
)

(defn sudoku-end-screen[time]
  [sudoku-modal time]

)

(defn sudoku-timer[time]
  [:p {:class "flex justify-center"} (-> time format-time) ]

)
(defn sudokuApp[]
  (fb/load-sdk fb-initialized)

  (fn[]
    [:div {:class "container flex-full flex-col flex-center "}
      [:h1 "Sudoku"]
        [:div {:class ""}
            (let [game-state (:game-state @sudoku-state) ]
              (cond
                (= "started" game-state)
                  [sudoku-start-screen]

                (= "playing" game-state)
                  [:div
                    [board (:puzzle @sudoku-state) (:board @sudoku-state) ]
                    [sudoku-timer (:timer @sudoku-state) ]
                  ]
                (= "ended" game-state)
                  [sudoku-end-screen (:timer @sudoku-state) ]
              )
            )
        ]
    ]
  )
)

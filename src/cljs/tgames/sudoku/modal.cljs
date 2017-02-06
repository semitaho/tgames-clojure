(ns tgames.sudoku.modal
  (:require [reagent.core :as r]
            [tgames.sudoku.action :refer [start-game]]
            [tgames.sudoku.filter :refer [format-time]]
    )
)

(def jquery (js* "$"))


(defn modal-content[time]
  [:div {:id "sudoku-modal" :class "modal" }
    [:div {:class "modal-content"}
      [:h4 "Game finished!"]
      [:p "Your elapsed time was: " (-> time format-time) ]
      [:button {:class "btn" :on-click #( do  (-> (jquery "#sudoku-modal") .closeModal) (start-game)   )  } "Proceed to next level"  ]
    ]
  ]
)

(def modal-options
  (js-obj "dismissible" false)
)

(defn sudoku-modal[]
  (r/create-class                 ;; <-- expects a map of functions
    {:reagent-render
      (fn[time]
        [modal-content time]
      )
     :component-did-mount
          #(-> (jquery "#sudoku-modal") (.openModal modal-options)  )
    }
  )
)

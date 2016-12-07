(ns tgames.sudoku.board)


(defn get-class[index]
  "col l1"

)

(defn board[data]
   [:div
      (for [item data]
       [:div {:class (get-class :key) } item]
      )
   ]
)

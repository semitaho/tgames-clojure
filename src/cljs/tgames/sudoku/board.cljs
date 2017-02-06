(ns tgames.sudoku.board
  (:require [tgames.sudoku.cell :refer [cell] ])
)

(def default-style " flex flex-1 box-cell justify-center ")


(defn box-left[index]
  (if (= 0 (mod index 9)) "box-left "  "" )
)

(defn box-right[index]
  (if (= 0 (mod (inc index) 3 )) "box-right  " "" )
)

(defn box-top[index]
  (if (< index 9) "box-top "  "" )
)

(defn box-bottom[index]
  (if (or (>= index 72) (and (>= index 18) (<= index 26)) (and (>= index 45) (<= index 53))   ) "box-bottom "  "" )
)

(defn get-class[index]
  (str default-style (box-left index) (box-top index) (box-right index) (box-bottom index)  )

)

(defn board[puzzle data]
   [:div {:class "flex flex-9 flex-wrap full-height"}
      (for [index (take 81 (range))]
        (let [data (get puzzle index)]

          ^{:key index} [:div  {:class (get-class index) } [cell index data] ]
        )

      )

   ]
)

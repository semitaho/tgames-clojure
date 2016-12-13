(ns tgames.sudoku.cell
  (:require [tgames.sudoku.state :as s])
)

(defn change-number[index number]
  (s/change-number index (last number))
)

(defn cell [index number]
  [:input{:type "number" :min "1" :max "9" :class "flex center browser-default cell" :value number :on-change #(change-number index (-> % .-target .-value) )    } ]
)

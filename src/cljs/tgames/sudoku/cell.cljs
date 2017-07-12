(ns tgames.sudoku.cell
  (:require [tgames.sudoku.action :refer [change-state timer-updater]]
            [tgames.sudoku.state :refer [sudoku-state change-number]]
            [cljs.reader :as reader]
            [tgames.sudoku.generator :refer [print-board]]
  )
)

(defn- map-to-vector [cell]
  (:number cell)
)

(defn- puzzle-to-numbers[]
  (let [vec (apply vector (map #(:number %) (:puzzle  @sudoku-state) ))]
    vec )

)
(defn check-game-state[]
  ( = (puzzle-to-numbers) (->  @sudoku-state :board)  )
)



(defn change-num[index number]
    (change-number index (reader/read-string number))
    (when (check-game-state)
      (js/clearInterval @timer-updater)
      (change-state "ended")


    )
)


(defn cell [index data]
  [:input{:type "number" :min "1" :max "9" :readOnly (true? (:readonly data)  )  :class (str "flex center browser-default cell" (when (false? (:readonly data) ) " edit") )  :value (:number data)  :on-change #(change-num index (-> % .-target .-value) )    } ]
)

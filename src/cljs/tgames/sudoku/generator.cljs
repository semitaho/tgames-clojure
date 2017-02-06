(ns tgames.sudoku.generator
    (:use [clojure.set  :only [union intersection difference] ])
)



(def sudoku-numbers (range 1 10) )


(defn index->value[board area]
    (remove nil? (map #(get board %) area))
)

(defn take-area[area index]
    (flatten (filter #(some (fn[a] (= a index)) %) area) )
)


(defn- area-calc[func]
    (map func (range 1 10))
)

(def areas {1 [(range 1 4)(range 1 4)]
            2 [(range 1 4)(range 4 7)]
            3 [(range 1 4)(range 7 10)]
            4 [(range 4 7)(range 1 4)]
            5 [(range 4 7)(range 4 7)]
            6 [(range 4 7)(range 7 10)]
            7 [(range 7 10)(range 1 4)]
            8 [(range 7 10)(range 4 7)]
            9 [(range 7 10)(range 7 10)]

})


(defn- row-indices[rownum]
    (map #(+ (* (dec rownum) 9)  %) (range 0 9) )
)
(defn col-indices[colnum]
    (map #(+ (dec colnum) (* 9 %) ) (range 0 9) )

)


(defn form-area-rowcol[fnc, area]
        (reduce (fn[a b](concat a (fnc b) )) [] area)
)

(defn area-indices[num]
    (intersection (into #{} (form-area-rowcol col-indices ( (areas num) 1) ) ) (into #{} (form-area-rowcol row-indices ( (areas num) 0))))
)

(def rows (area-calc row-indices))

(def cols (area-calc col-indices))
(def alueet (map #(into () %) (area-calc area-indices)))


(defn calculate-available-sudoku-numbers[board index]
    (let [sudoku-numbers-set (into #{} sudoku-numbers) ]
       (into [] (difference sudoku-numbers-set (into #{} (union (index->value board (take-area rows index))
              (index->value board (take-area cols index))
              (index->value board (take-area alueet index))
        )))))
)

(defn rand-new-number[vect]
    (nth vect (rand-int (count vect)))
)

(defn rand-new-sudoku-number[board]
    (let [available-numbers ( calculate-available-sudoku-numbers board (count board))]
        (rand-new-number available-numbers)
    )
)

(defn board-filled? [board]
   (= (count board) 81)
)

(defn- count-nil-numbers[board]
  (reduce (fn[x y] (if (nil? y) (inc x) x )) 0 board)

)
(defn- remove-from-board-ind [board current]
  (let [ind (-> board count  rand-int) ]

    (if (get board ind)

      (let [new-board (assoc board ind nil) available-numbers-count (-> new-board (calculate-available-sudoku-numbers ind) count)]
        (if (= 1 available-numbers-count)
          ind
          (remove-from-board-ind board current)
        )

      )
      (remove-from-board-ind board current)
    )
  )
 )

(defn create-puzzle[board level]
  (reduce (fn[x y] (assoc x (-> x (remove-from-board-ind (inc y) )) nil) ) board (range 0 level) )
)

(defn create-board
    ([] (create-board []))
    ([board]
        (let [available-numbers (-> board (calculate-available-sudoku-numbers (count board)) shuffle)]
            (loop [index 0]
                (if (nil? (get available-numbers index))
                    nil
                    (if (board-filled? board)
                        board
                        (if (= (count available-numbers) 0)
                            nil
                            (let [createdBoard (create-board (conj board (get available-numbers index)))]
                                (if (nil? createdBoard) (recur (inc index)) createdBoard)
                            )
                        )
                   )
                )
           )
        )
    )
)

(defn print-board [board]
    (doseq [x board] (println  (type x) )  )

)

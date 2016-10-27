(ns tgames.routes)

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to tgames"]
   [:div
     [:ul
         [:li
            [:a {:href "/sudoku"} "go to sudoku"]
         ]
     ] ]
  ]
)
(defn about-page []
  [:div [:h2 "About paska"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn sudoku []
  (print "paska")
  [:div [:h2 "jes sudoku"] ]
)   


(ns tgames.routes)

;; -------------------------
;; Views

(defn home-page []
  [:div {:class "container flex-col flex-full flex-center "}
    [:h2 "Welcome to tgames"]
    [:div {:class "flex"}
      [:div {:class "card flex"}
        [:a {:class "card-action card-title flex align-center" :href "/#/sudoku" } "Sudoku"  ]
      ]
    ]
  ]
)
(defn about-page []
  [:div [:h2 "About paska"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn sudoku []
  (print "paska")
  [:div [:h2 "jes sudoku"] ]
)

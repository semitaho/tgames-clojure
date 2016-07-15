(ns tgames.routes)

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to tgames"]
   [:div [:a {:href "/about"} "go to about page"]]])

(defn about-page []
  [:div [:h2 "About paska"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn sudoku []
  [:div [:h2 "jes sudoku"] ]
)   


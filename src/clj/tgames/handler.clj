(ns tgames.handler
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [tgames.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]))

(def mount-target
  [:div#container
      [:h3 "ClojureScript has not been finished!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1, maximum-scale=1"}]
   [:title "tGames"]
   (include-css  (if (env :dev) "/css/materialize.css" "/css/materialize.min.css" ) "/css/flex.css" "/css/main.css"  )])

(def loading-page
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/jquery.min.js" "/js/materialize.js" "/js/app.js")
    ]
  )
)




(defroutes routes
  (GET "/" [] loading-page)
  (GET "/about" [] loading-page)
  (GET "/sudoku" [] loading-page)

  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))

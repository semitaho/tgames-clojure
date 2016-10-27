(ns tgames.core
    (:require [reagent.core :as rg :refer [atom]]
              [reagent.session :as session]
              [tgames.routes :as routes :refer [home-page about-page]]
              [tgames.sudoku.core :refer [sudokuApp] ]
              [secretary.core :as secretary :include-macros true :refer-macros [defroute]]
              [accountant.core :as accountant]))


(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(defroute "/" []
  (session/put! :current-page #'home-page))

(defroute "/about" []
  (session/put! :current-page #'about-page))
(defroute "/sudoku" []
  (session/put! :current-page #'sudokuApp))  

;; -------------------------
;; Initialize app

(defn mount-root []
  (rg/render [current-page] (.getElementById js/document "container")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))

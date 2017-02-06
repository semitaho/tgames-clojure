(ns tgames.sudoku.action
  (:require [reagent.core :as r]
            [fb-sdk-cljs.core :as fb]
            [tgames.sudoku.state :refer [sudoku-state] ]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            )
  (:require-macros [cljs.core.async.macros :refer [go]])
)

(def timer-updater (r/atom nil))


(defn- update-sudoku-state[key val]
  (swap! sudoku-state assoc key val)
)


(def base-uri "https://api.mlab.com/api/1")
(def api-key "eOp9LjtwzApDNb-TbPbCEZ2V74XTSwrV")


(defn fb-initialized[]
  (fb/init {:appId "1768419953389920"
             :status true
             :cookies true
             :xfbml true
             :version "v2.0"}
  )
  (fb/get-login-status
    (fn [response]
      (if (= (:status response) "connected")
        (js/console.log "connected " response)
       ;; else
        (fb/login #(js/console.log "else-" %) {:scope "email"})
      )
    )
  )
)






(defn login-facebook[]
  (fb/get-login-status
    (fn [response]
      (if (= (:status response) "connected")
        (.log js/console "connected")
     ;; else
        (fb/login #(.log js/console "else-" %) {:scope "email"})
      )
    )
  )

)

(defn fetch-scores[]

  (go (let [response (<! (http/get (str base-uri "/databases/tgamesdb/collections/scoreboard?apiKey=" api-key)
                                 {:with-credentials? false
                                  :query-params {"since" 135}}))]
      (prn (:status response))
      (prn (:body response)))
  )
  (prn "keijo")
)

(defn change-state [state]
  (update-sudoku-state :game-state state)
)

(defn start-game[]
  (do
    (update-sudoku-state :timer 0)
    (change-state "playing")
    (reset! timer-updater (js/setInterval #(swap! sudoku-state update-in [:timer] inc)   1000) )

  )
)

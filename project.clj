(defproject blog-in-clojure "0.2.0"
            :description "Simple blog, something like hello world in clojure"
            :dependencies [[org.clojure/clojure "1.6.0"]
                                    [compojure "1.1.8"]
                                    [hiccup "1.0.5"]
                           [noir "1.3.0-beta3"]  ; deprecated
                                    [org.markdownj/markdownj "0.3.0-1.0.2b4"]
                                    [com.novemberain/monger "2.0.0-rc1"]]
            :plugins [[lein-ring "0.8.10"]]
            :ring {:handler blog-in-clojure.server/app}
            :main blog-in-clojure.server)


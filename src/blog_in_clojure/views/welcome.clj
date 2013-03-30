(ns blog-in-clojure.views.welcome
  (:require [blog-in-clojure.views.common :as common])
  (:use [noir.core]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to blog-in-clojure"]))

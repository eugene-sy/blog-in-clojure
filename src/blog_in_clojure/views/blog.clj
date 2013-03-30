(ns blog-in-clojure.views.blog
  (:require [blog-in-clojure.views.common :as common]
  					[noir.response :as resp])
  (:use [noir.core]))

(defpage "/about" []
	(resp/redirect "/about/"))

(defpage "/contact" []
	(resp/redirect "/contact/"))

(defpage "/about/" []
	(common/layout
		[:p "About this blog"]))

(defpage "/contact/" []
	(common/layout
		[:p "Contact me!"]))


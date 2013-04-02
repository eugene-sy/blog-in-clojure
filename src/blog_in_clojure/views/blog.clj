(ns blog-in-clojure.views.blog
  (:require [blog-in-clojure.views.common :as common]
  					[noir.response :as resp]
  					[blog-in-clojure.helpers.markdown :as md])
  (:use [noir.core]))

(defpage "/about" []
	(resp/redirect "/about/"))

(defpage "/contact" []
	(resp/redirect "/contact/"))

(defpage "/about/" []
	(common/layout
		[:div 
			(md/render-file "resources/assets/about.md")]))

(defpage "/contact/" []
	(common/layout
		[:div 
			(md/render-file "resources/assets/contact.md")]))


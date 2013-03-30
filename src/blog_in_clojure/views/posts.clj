(ns blog-in-clojure.views.posts
  (:require [blog-in-clojure.views.common :as common]
  					[noir.response :as resp])
  (:use [noir.core]))

; default redirect
(defpage "/" []
	(resp/redirect "/posts/"))

; post redirect
(defpage "/posts" []
	(resp/redirect "/posts/"))

; show list of posts
(defpage "/posts/" []
	(common/layout
		[:p "Some posts here"]))

; show selected post
(defpage "/posts/:id" {:keys[id]}
	(common/layout
		[:p "A simple post here"]))

; show creation form
(defpage "/posts/create" []
	(common/layout
		[:p "Form to create post"]))

; edit post
(defpage "/posts/:id/edit" {:keys [id]}
	(common/layout
		[:p "Form to create post"]))
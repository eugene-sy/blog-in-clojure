(ns blog-in-clojure.views.posts
  (:require [blog-in-clojure.views.common :as common]
  					[blog-in-clojure.models.post :as post]
  					[blog-in-clojure.helpers.common :as ch]
  					[noir.response :as resp]
  					[clojure.core :as core])
  (:use [noir.core]
  			[hiccup.page]
        [hiccup.element]))

; some partials here

(defpartial short-post [post] 
	[:div.post-container
		[:a 
			{:href 
				(apply str 
					(concat 
						["/posts/" 
							(int (post :uid))]))}
			[:h3 (post :title)]]
		[:div.post-body-trunkated 
			(ch/truncate 
				(post :body))]])

(defpartial post-list [posts]
	[:div.posts
		(core/mapcat short-post posts)])

(defpartial full-post [post]
	[:div.post 
		[:h3 (post :title)]
		[:div.post-body
			(post :body)]
		[:a {:href "/posts/"} 
			[:span [:i.icon-arrow-left] "Back"]]])

; default redirect
(defpage "/" []
	(resp/redirect "/posts/"))

; post redirect
(defpage "/posts" []
	(resp/redirect "/posts/"))

; show list of posts
(defpage "/posts/" []
	(common/layout
		(post-list
			(post/find-all))))

; show selected post
(defpage "/posts/:id" {:keys[id]}
	(let [post (post/find-one id)]
		(common/layout
			(if (not (nil? post))
				(full-post post)
				"Sorry, there is no post with that id"))))

; show creation form
(defpage "/posts/create" []
	(common/layout
		[:p "Form to create post"]))

; edit post
(defpage "/posts/:id/edit" {:keys [id]}
	(common/layout
		[:p "Form to create post"]))

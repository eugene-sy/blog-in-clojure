(ns blog-in-clojure.views.posts
  (:require [blog-in-clojure.views.common :as common]
  					[blog-in-clojure.models.post :as post]
  					[blog-in-clojure.helpers.common :as ch]
  					[noir.response :as resp]
  					[clojure.core :as core])
  (:use noir.core
        hiccup.core
        hiccup.page
        hiccup.form))

; some partials here

(defpartial short-post [post] 
	(println post)
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
				(post :body))]
		[:div.links 
			[:a {:href (str "/posts/" (int (post :uid)) "/edit")} "Edit"]
			[:a {:href (str "/posts/" (int (post :uid)) "/delete")} "Delete"]]])

(defpartial post-list [posts]
	[:div.links.right
		[:a {:href "/posts/create"} "Add new post"]]
	[:div.posts
		(core/mapcat short-post posts)])

(defpartial full-post [post]
	[:div.post 
		[:h3 (post :title)]
		[:div.post-body
			(post :body)]
		(common/add-cancel-button "/posts/")])

(defpartial post-form [{:keys [title body]}]
	[:div
		(label :title "Text:")
		(text-field {:placeholder "Title"} :title title)
		(label :title "Body:")
		(text-area  {:placeholder "Body"} :body body)])

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
(defpage "/posts/create" {:as post}
	(common/layout
		(form-to [:post "/posts/create"]
			(post-form post)
			(common/add-submit-button "Add post")
			(common/add-cancel-button "/posts/"))))

(defpage [:post "/posts/create"] {:as post}
	(post/create (post :title) (post :body))
	(resp/redirect "/posts/"))

; edit post
(defpage "/posts/:id/edit" {:keys [id]}
	(let [post (post/find-one id)] 
	(common/layout
		(form-to [:post (str "/posts/" id "/edit")]
			(post-form post)
			(common/add-submit-button "Save post")
			(common/add-cancel-button "/posts/")))))

(defpage [:post "/posts/:id/edit" ] {:keys [id] :as post}
		(post/update id (post :title) (post :body))
		(resp/redirect "/posts/"))

(ns blog-in-clojure.views.users
  (:require [blog-in-clojure.views.common :as common]
  					[blog-in-clojure.models.post :as post]
  					[blog-in-clojure.helpers.common :as ch]
  					[noir.response :as resp]
  					[clojure.core :as core])
  (:use noir.core
        hiccup.core
        hiccup.page
        hiccup.form))

; user partials

(defpartial sign-up-form [] 
	[:div
		(label :name "Name:")
		(text-field {:placeholder "Name"} :title "")
		(label :email "Email:")
		(text-field  {:placeholder "e-mail"} :body "")
		(label :password "Password:")
		(text-field  {:placeholder "password"} :password "")
		(label :confirmation "Password confirmation:")
		(text-field  {:placeholder "password again"} :confirmation "")])

; user pages

(defpage "/login" []
	(resp/redirect "/login/"))

(defpage "/login/" []
	(common/layout "Login form here"))

(defpage [:post "/login/"] {:as user}
	(resp/redirect "/posts/"))

(defpage "/users/create" []
	(resp/redirect "/users/create/"))

(defpage "/users/create/" []
	(common/layout 
		(sign-up-form)
		(common/add-submit-button "Register")
		(common/add-cancel-button "/posts/")))

(defpage [:post "/users/create/"] {:as user}
	(resp/redirect "/posts/"))

(ns blog-in-clojure.views.users
  (:require [blog-in-clojure.views.common :as common]
  					[blog-in-clojure.models.user :as user]
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
		(text-field {:placeholder "Name"} :name "")
		(label :email "Email:")
		(text-field  {:placeholder "e-mail"} :email "")
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
		(form-to [:post "/users/create/"] 
			(sign-up-form)
			(common/add-submit-button "Register")
			(common/add-cancel-button "/posts/"))))

(defpage [:post "/users/create/"] {:as user}
	(user/create 
		(user :name)
		(user :email)
		(user :password)
		true)
	(resp/redirect "/posts/"))

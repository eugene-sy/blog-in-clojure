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

(defpage "/login" []
	(resp/redirect "/login/"))

(defpage "/login/" []
	(common/layout "Login form here"))

(defpage "/users/create" []
	(resp/redirect "/users/create/"))

(defpage "/users/create/" []
	(common/layout "Registration form here"))
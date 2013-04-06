(ns blog-in-clojure.models.post
	(:require [monger.collection :as mc]
						[blog-in-clojure.helpers.common :as ch]))

(def collection "posts")

(defn find-all []
	(mc/find-maps collection))

(defn find-one [id]
	(mc/find-maps collection {:_id id}))

(defn create [title body]
	(mc/insert collection {:title title
		:body body
		:created (ch/now)}))

(defn update [id title body]
	(mc/update {:_id id} {:title title :body body}))

(defn delete [id]
	(mc/remove {:_id id}))
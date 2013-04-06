(ns blog-in-clojure.models.post
	(:require [monger.collection :as mc]
						[monger.query :as mq]
						[blog-in-clojure.helpers.common :as ch]))

(def collection "posts")

(defn total []
	(mc/count collection))

(defn max-id []
	(if (> total 0)
		(mq/with-collection collection
			(mq/find {})
			(mq/fields [:uid])
			(mq/sort 
				(sorted-map :uid -1 :created -1))
			(mq/limit 1))
		1))

(defn get-new-uid [] 
	(inc (max-id :uid)))

(defn find-all []
	(mc/find-maps collection))

(defn find-one [id]
	(mc/find-one-as-map collection {:uid (Double/parseDouble id)}))

(defn create [title body]
	(mc/insert collection 
		{:uid get-new-uid
		:title title
		:body body
		:created (ch/now)}))

(defn update [id title body]
	(mc/update {:uid id} {:title title :body body}))

(defn delete [id]
	(mc/remove {:uid id}))
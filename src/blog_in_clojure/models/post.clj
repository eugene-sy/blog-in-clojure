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
			(mq/fields [:post])
			(mq/sort 
				(sorted-map :post -1 :created -1))
			(mq/limit 1))
		1))

(defn get-uid [] 
	(inc (max-id :post)))

(defn find-all []
	(mc/find-maps collection))

(defn find-one [id]
	(mc/find-one-as-map collection {:post id}))

(defn create [title body]
	(mc/insert collection 
		{:post get-uid
		:title title
		:body body
		:created (ch/now)}))

(defn update [id title body]
	(mc/update {:post id} {:title title :body body}))

(defn delete [id]
	(mc/remove {:post id}))
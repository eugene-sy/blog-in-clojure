(ns blog-in-clojure.models.user
	(:require [monger.collection :as mc]
						[monger.query :as mq]
						[blog-in-clojure.helpers.common :as ch]
						[blog-in-clojure.helpers.database :as db])
	(:use monger.operators))

; fields:
;; uid
;; name
;; email
;; password
;; editor
;; created

(def collection "users")

(defn total []
	(db/total collection))

(defn find-all []
	(mc/find-maps collection))

(defn find-one [id]
	(mc/find-one-as-map collection 
		(db/gen-uid id)))

(defn create [name email password editor]
	(mc/insert collection 
		{:uid (db/get-new-uid collection)
		:name name
		:email email
		:password password
		:editor editor
		:created (ch/now)}))

(defn update [id name email password editor]
	(mc/update collection 
		{:uid (db/gen-uid id) }
		{$set {:name name
			:email email
			:password password
			:editor editor}}))

(defn delete [id]
	(mc/remove collection (db/gen-uid id)))

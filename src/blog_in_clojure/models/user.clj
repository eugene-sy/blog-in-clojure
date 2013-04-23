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
;; salt
;; created

(def collection "users")

(defn total []
	(db/total collection))

(defn find-all []
	(mc/find-maps collection))

(defn find-one [id]
	(mc/find-one-as-map collection 
		(db/gen-uid id)))

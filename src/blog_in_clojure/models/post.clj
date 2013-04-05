(ns blog-in-clojure.models.post
	(:require [monger.collection :as mc]))

(def collection "posts")

(defn find-all []
	(mc/find-maps collection))

(defn find-one [])

(defn create [])

(defn update [])

(defn delete [])

(defn total [])
(ns blog-in-clojure.models
  (:require [monger.core :as mongo])
  (:import org.bson.types.ObjectId))

(let [^ServerAddress sa
        (mongo/server-address "127.0.0.1" 27017)]
      (mongo/connect sa))

; (mongo/set-db
;   (mongo/get-db "blog-in-clojure"))

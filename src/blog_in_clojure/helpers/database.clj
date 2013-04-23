(ns blog-in-clojure.helpers.database)

(defn total [collection]
	(mc/count collection))

(defn gen-uid [id]
	{:uid (Double/parseDouble id)})

(defn max-id [collection]
	(if (mc/any? collection)
		(first 
			(mq/with-collection collection
				(mq/find {})
				(mq/fields [:uid])
				(mq/sort 
					(sorted-map :uid -1 :created -1))
				(mq/limit 1)))
		{:uid 1}))

(defn get-new-uid [collection] 
	(inc 
		(:uid (max-id collection))))
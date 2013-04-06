(ns blog-in-clojure.helpers.common)

(defn size [input]
	(count input))

(defn truncate [input]
	(if (> (size input) 100)
		(concat 	
			(take 100 input) "...")
		input))


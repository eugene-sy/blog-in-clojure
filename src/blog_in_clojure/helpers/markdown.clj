(ns blog-in-clojure.helpers.markdown
	(:require [clojure.core :as core])
  (:import com.petebevin.markdown.MarkdownProcessor))

(def mdp (MarkdownProcessor.))

(defn get-file-data [filename]
	(core/slurp filename))

(defn render-md [text]
  (. mdp (markdown text)))

(defn render-file [filename]
	(render-md 
		(get-file-data filename)))
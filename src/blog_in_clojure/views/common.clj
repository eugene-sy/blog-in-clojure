(ns blog-in-clojure.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]))


; list of includes
(def incls {:reset (include-css "/css/reset.css")
						:blog-css (include-css "/css/blog.css")
						:bootstrap-css (include-css "http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css")
						:jquery (include-js "http://code.jquery.com/jquery-1.9.1.min.js")
						:bootstrap-js (include-js "http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js")
						:blog-js (include-js "/js/blog.js")
						})

; some page templates
(defpartial set-head [incs]
	[:head
    [:title "blog-in-clojure"]
      (map #(get incls %) incs)])

(defpartial set-body-wrapper [& content]
	[:body
		[:div.container-narrow
      [:div.masthead
        [:h3.muted "blog-in-clojure" ]]
      [:hr]
      [:div.container content]
      [:hr]
      [:div.footer
        [:p "© Company 2013"]]]])

; default layout
(defpartial layout 
	[& content]
    (html5
      (set-head [:bootstrap-css :blog-css :jquery :bootstrap-js :blog-js])
      (set-body-wrapper content)))


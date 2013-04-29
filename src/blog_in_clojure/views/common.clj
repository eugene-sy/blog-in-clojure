(ns blog-in-clojure.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page]
        [hiccup.element]
        [hiccup.form]))


; list of includes
(def incls {:reset (include-css "/css/reset.css")
						:blog-css (include-css "/css/blog.css")
						:bootstrap-css (include-css "http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/css/bootstrap-combined.min.css")
						:jquery (include-js "http://code.jquery.com/jquery-1.9.1.min.js")
						:bootstrap-js (include-js "http://netdna.bootstrapcdn.com/twitter-bootstrap/2.3.1/js/bootstrap.min.js")
						:blog-js (include-js "/js/blog.js")
						})

; list of items in top-right menu
(def menu-items {:posts  
										(link-to "/posts/" "Blog")
								 :about 
								 		(link-to "/about/" "About")
								 :contact
								 		(link-to "/contact/" "Contact")
								 :register
								 		(link-to "/users/create" "Sign up")
								 :login
								 		(link-to "/login" "Log in")
								 })

; some page templates
(defpartial set-head [incs]
	[:head
    [:title "blog-in-clojure"]
      (map #(get incls %) incs)])

(defpartial list-item [item]
	[:li item])

(defpartial top-links [link-items]
	[:ul.nav.nav-pills.pull-right
		(map list-item 
			(map #(get menu-items %) link-items))])

(defpartial footer [] 
	[:div#footer
    [:div.container-narrow
    	[:hr]
      [:p.muted.credit 
      	"Created by " 
      	[:a {:href "https://github.com/Axblade"} "Axblade"]
      	" in 2013"]]])

(defpartial set-body-wrapper [& content]
	[:body
		[:div#wrap
			[:div.container-narrow
    	  [:div.masthead
    	  (top-links [:posts :about :contact :register :login])
    	    [:h3.muted "blog-in-clojure" ]]
    	  [:hr]
    	  [:div.container-narrow content]]]
    (footer)])

(defpartial add-submit-button [title]
	(submit-button {:class "btn btn-primary submit"} title))

(defpartial add-cancel-button [backUrl]
	[:a.btn {:href backUrl} 
			[:span [:i.icon-arrow-left] "Back"]])

; default layout
(defpartial layout 
	[& content]
    (html5
      (set-head [:bootstrap-css :blog-css :jquery :bootstrap-js :blog-js])
      (set-body-wrapper content)))


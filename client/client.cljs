(ns tracula.client)

(defn log [str]
  (.log js/console str))

; (defn widget [data]
;   (om/component
;     (dom/div nil "Hello world!")))

; (om/root {} widget (.getElementById js/document "container"))

(def comments-data [{:author "Pete Hunt" :text "This is a comment."}
                    {:author "Jordan Walke" :text "This is *another* coment"}])

(def Comment
  (js/React.createClass
   #js {:render (fn []
                  (this-as this
                           (let [comment (.. this -props -comment)]
                             (js/React.DOM.div
                              #js {:className "comment"}
                              (js/React.DOM.h2 nil  (:author comment))
                              (js/React.DOM.span nil (:text comment))))))}))

(def CommentList 
  (js/React.createClass
   #js {:render (fn []
                  (this-as this
                           (js/React.DOM.div
                            #js {:className "commentList"}
                            (into-array
                             (map #(Comment #js {:comment %})
                                  (.. this -props -comments))))))}))

(def CommentBox
  (js/React.createClass
   #js {:render (fn []
                  (this-as this
                           (js/React.DOM.div
                            #js {:className "commentBox"}
                            (js/React.DOM.h1 nil "Comments")
                            (CommentList #js {:comments
                                              (.. this -props -comments)}))))}))

(js/React.renderComponent
 (CommentBox #js {:comments comments-data})
 (.getElementById js/document "container")) 
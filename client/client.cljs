(ns tracula.client)

(defn log [str]
  (.log js/console str))

; (defn widget [data]
;   (om/component
;     (dom/div nil "Hello world!")))

; (om/root {} widget (.getElementById js/document "container"))

(def dom js/React.DOM)

; Create the ReactJS object (which contains a standard js literal obj with a :render key)
(defn component [renderFn] 
  (js/React.createClass #js {:render renderFn}))


(def comments-data [{:author "Pete Hunt" :text "This is a comment."}
                    {:author "Jordan Walke" :text "This is *another* coment"}])

; (def NavBar
;   component )
(def Comment
  (component (fn []
                  (this-as this
                           (let [comment (.. this -props -comment)]
                             (dom.div
                              (:className "comment")
                              (dom.h2 nil  (:author comment))
                              (dom.span nil (:text comment))))))))

(def CommentList 
  (component (fn []
                  (this-as this
                           (dom.div
                            (:className "commentList")
                            (into-array
                             (map #(Comment (:comment %))
                                  (.. this -props -comments))))))))

(def CommentBox
  (component (fn []
                  (this-as this
                           (dom.div
                            #js {:className "commentBox"}
                            (dom.h1 nil "Comments")
                            (CommentList #js {:comments
                                              (.. this -props -comments)}))))))

(js/React.renderComponent
 (CommentBox #js {:comments comments-data})
 (.getElementById js/document "container")) 
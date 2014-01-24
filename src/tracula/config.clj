(ns tracula.config)
(def url "https://svn.jimdo-server.com/trac/login/jsonrpc")
; (def user-creds ["mark" "O3xVfe14"])
(def socket-timeout 3000)
(def conn-timeout 3000)
;make things easier for testing
(def example-ticket 60988)

(def port 8080)
; (def ^:dynamic auth nil)

; (defn set-auth! [auth-str]
;   (binding [auth auth-str]))
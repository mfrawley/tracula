(defproject tracula "1.0.0-SNAPSHOT"
	:main tracula.core
  :description "FIXME: write description"
  :plugins [[lein-cljsbuild "1.0.0"]]
  :dependencies [
          [org.clojure/clojure "1.5.1"]
          [org.clojure/tools.reader "0.8.2"]
  				[clj-http "0.7.6"]
  				[org.clojure/data.json "0.2.3"]
  				[clj-time "0.6.0"]
  				[ring "1.2.1"]
  				[compojure "1.1.6"]
  				; [prismatic/dommy "0.1.1"]
          [org.clojure/clojurescript "0.0-2120"]
          [om "0.1.0"]
  ]
:cljsbuild {
	:builds [{
		; The path to the top-level ClojureScript source directory:
      	:source-paths ["client"]
      	; The standard ClojureScript compiler options:
      	; (See the ClojureScript compiler documentation for details.)
        :notify-command ["terminal-notifier" "-message"]
      	:compiler {
          ; :output-dir "resources/public/js/"
        	:output-to "resources/public/js/app.js"
        	:optimizations :whitespace
          :source-maps true
          :pretty-print true
    	 }
    }]
}
)
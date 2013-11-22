(ns tracula.test.core
  (:use [tracula.core])
  (:use [clojure.test]))

(defn has-keys [expected-keys obj]
	(is expected-keys (keys obj)))

(testing "API - basic tests"
	(deftest test-get-ticket
  	(let [ticket (get-ticket 61450)]
  		(is (type ticket) clojure.lang.PersistentArrayMap)
  		(has-keys [:id :time_created :time_changed :attributes] ticket)))

	(deftest test-get-current-timestamp-str
		(is (string? (get-current-timestamp-str))))

	(deftest test-format-ts-for-json
		(is (contains? (format-ts-for-json (get-current-timestamp-str)) "__jsonclass__")))

	(deftest test-list-methods
		(is
			(list-methods) ["system.multicall" "system.listMethods" "system.methodHelp" "system.methodSignature" "system.getAPIVersion" "ticket.query"
			"ticket.getRecentChanges" "ticket.getAvailableActions" "ticket.getActions" "ticket.get" "ticket.create" "ticket.update" "ticket.delete"
			"ticket.changeLog" "ticket.listAttachments" "ticket.getAttachment" "ticket.putAttachment" "ticket.deleteAttachment" "ticket.getTicketFields" "ticket.status.getAll"
			"ticket.status.get" "ticket.status.delete" "ticket.status.create" "ticket.status.update" "ticket.component.getAll" "ticket.component.get" "ticket.component.delete"
			"ticket.component.create" "ticket.component.update" "ticket.version.getAll" "ticket.version.get" "ticket.version.delete" "ticket.version.create" "ticket.version.update"
			"ticket.milestone.getAll" "ticket.milestone.get" "ticket.milestone.delete" "ticket.milestone.create" "ticket.milestone.update" "ticket.type.getAll" "ticket.type.get"
			"ticket.type.delete" "ticket.type.create" "ticket.type.update" "ticket.resolution.getAll" "ticket.resolution.get" "ticket.resolution.delete" "ticket.resolution.create"
			"ticket.resolution.update" "ticket.priority.getAll" "ticket.priority.get" "ticket.priority.delete" "ticket.priority.create" "ticket.priority.update" "ticket.severity.getAll"
			"ticket.severity.get" "ticket.severity.delete" "ticket.severity.create" "ticket.severity.update" "wiki.getRecentChanges" "wiki.getRPCVersionSupported" "wiki.getPage" "wiki.getPageVersion"
			"wiki.getPageHTML" "wiki.getPageHTMLVersion" "wiki.getAllPages" "wiki.getPageInfo" "wiki.getPageInfoVersion" "wiki.putPage" "wiki.listAttachments" "wiki.getAttachment" "wiki.putAttachment"
			"wiki.putAttachmentEx" "wiki.deletePage" "wiki.deleteAttachment" "wiki.listLinks" "wiki.wikiToHtml" "search.getSearchFilters" "search.performSearch"]))

	(deftest test-create-delete-ticket
		(let [new-ticket-id (create-ticket "test-summary" "test-description")
					delete-res-id (delete-ticket new-ticket-id)]
					(is (integer? new-ticket-id))
					(is (= delete-res-id 0))))

)
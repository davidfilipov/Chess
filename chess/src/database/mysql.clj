(ns database.mysql)

(let [db-host "localhost"
      db-port 3306
      db-name "nst"]
 
  (def db {:classname "com.mysql.jdbc.Driver" 
           :subprotocol "mysql"
           :subname (str "//" db-host ":" db-port "/" db-name)
           ; Any additional keys are passed to the driver
           ; as driver-specific properties.
          :user "root" }))


(defn create-blogs
  "Create a table to store blog entries"
  []
  (clojure.java.jdbc/create-table
   :blogs
   [:id :integer "PRIMARY KEY" "AUTO_INCREMENT"]
   [:title "varchar(255)"]
   [:body :text]))

 (clojure.java.jdbc/with-connection
   db
   (clojure.java.jdbc/transaction
     (create-blogs)))
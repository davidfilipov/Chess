(ns chess.core)

(use 'clojure.contrib.sql)
(use 'clojure.java.jdbc)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))




 
(let [db-host "localhost"
      db-port 3306
      db-name "nst"]
 
  (def db {:classname "com.mysql.jdbc.Driver" ; must be in classpath
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








































(def db {:classname "com.mysql.jdbc.Driver" 
                   :subprotocol "mysql" 
                   :subname "//localhost:3306/nmr" 
                   :user "root"})

(defmacro with-connection
  "Evaluates body in the context of a new connection to a database then
  closes the connection. db-spec is a map containing values for one of the
  following parameter sets:

  Factory:
    :factory     (required) a function of one argument, a map of params
    (others)     (optional) passed to the factory function in a map

  DriverManager:
    :classname   (required) a String, the jdbc driver class name
    :subprotocol (required) a String, the jdbc subprotocol
    :subname     (required) a String, the jdbc subname
    (others)     (optional) passed to the driver as properties.

  DataSource:
    :datasource  (required) a javax.sql.DataSource
    :username    (optional) a String
    :password    (optional) a String, required if :username is supplied

  JNDI:
    :name        (required) a String or javax.naming.Name
    :environment (optional) a java.util.Map"
  [db-spec & body]
  `(with-connection* ~db-spec (fn [] ~@body)))

(with-connection db (with-query-results rs ["select * from nst"] (count rs)))
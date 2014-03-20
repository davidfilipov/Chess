(ns chess.core)

(use 'clojure.contrib.sql)
(use 'clojure.java.jdbc)




































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
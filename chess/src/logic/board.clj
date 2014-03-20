(ns logic.board)

(def initial-board
[\r \n \b \q \k \b \n \r
\p \p \p \p \p \p \p \p
\- \- \- \- \- \- \- \-
\- \- \- \- \- \- \- \-
\- \- \- \- \- \- \- \-
\- \- \- \- \- \- \- \-
\P \P \P \P \P \P \P \P
\R \N \B \Q \K \B \N \R])

(def ^:dynamic *file-key* \a)

(def ^:dynamic *rank-key* \0)

(defn- file-component [file]
 (- (int file) (int *file-key*)))

(defn- rank-component [rank]
 (* 8 (- 8 (- (int rank) (int *rank-key*)))))

(defn- index [file rank]
 (+ (file-component file) (rank-component rank)))

(defn lookup [board pos]
 (let [[file rank] pos]
   (board (index file rank))))

(letfn [(index [file rank]
               (let [f (- (int file) (int \a))
                     r (* 8 (- 8 (- (int rank) (int \0))))]
                 (+ f r)))]
  (defn lookup [board pos]
    (let [[file rank] pos]
      (board (index file rank)))))
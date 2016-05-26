(defn constant [x]
  (fn [arguments] x))

(defn variable [name]
  (fn [arguments]
    (arguments name)))

(defn anyOp [oper]
  (fn [& xs]
    (fn [arguments]
      (apply oper
             (map (fn [fun]
                    (fun arguments)) xs)))))

(def add (anyOp +))
(def subtract (anyOp -))
(def multiply (anyOp *))
(def divide (anyOp
              (fn [^double x ^double y]
                (/ x y))))

(def negate subtract)
(def sin (anyOp (fn [x] (Math/sin x))))
(def cos (anyOp (fn [x] (Math/cos x))))

(def operations {"+" add "-" subtract "*" multiply "/" divide "negate" negate "sin" sin "cos" cos})

(defn myParse [arg]
  (cond
    (number? arg) (constant (double arg))
    (symbol? arg) (variable (str arg))
    :else (apply
            (operations
              (str
                (first arg)))
            (map
              (fn [x]
                (myParse x))
              (rest arg)))))

(defn parseFunction [expression]
  (let [string (read-string expression)]
    (myParse string)))


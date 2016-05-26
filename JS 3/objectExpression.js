var variables = {
	"x" : 0,
	"y" : 1,
	"z" : 2
};

function add(left, right) {return left + right}
function subtract(left, right) {return left - right}
function multiply(left, right) {return left * right}
function divide(left, right) {return left / right}
function negate(value) {return -value}
function cos(value) {return Math.cos(value)}
function sin(value) {return Math.sin(value)}
function exp(value) {return Math.exp(value)}
function arcTan(value) {return Math.atan(value)}

function Const(value) {
	this.value = value;
}

Const.prototype.evaluate = function () {
	return this.value;
};

Const.prototype.toString = function () {
	return this.value.toString();
};


Const.prototype.prefix = function () {
    return this.value.toString();
}

function Variable(varName) {
	this.varName = varName;
}

Variable.prototype.evaluate = function () {
	return arguments[variables[this.varName]];
};


Variable.prototype.toString = function () {
	return this.varName;
};

Variable.prototype.prefix = function () {
    return this.varName;
}


function Operation(operation, symbol, left, right) {
	this.operation = operation;
	this.symbol = symbol;
	this.left = left;
	this.right = right;
}

Operation.prototype.evaluate = function () {
	if (this.right == undefined) {
		return this.operation(this.left.evaluate.apply(this.left, arguments));
	}

	return this.operation(this.left.evaluate.apply(this.left, arguments), this.right.evaluate.apply(this.right, arguments));
}

Operation.prototype.toString = function () {
	var s = this.left.toString() + " ";
	if (this.right != undefined) {
		s += this.right.toString() + " ";
	}

	s = s + this.symbol;
	return s;
}

Operation.prototype.prefix = function () {
    var s = "(" + this.symbol + " " + this.left.prefix();
    if (this.right != undefined) {
        s += " " + this.right.prefix();
    }
    s += ")";
    return s;
}

function builder(operation, symbol) {
	function Cons(left, right) {
		Operation.call(this, operation, symbol, left, right);
	}

	Cons.prototype = Object.create(Operation.prototype);
	return Cons;
}

var Add = builder(add, "+");
var Subtract = builder(subtract, "-");
var Multiply = builder(multiply, "*");
var Divide = builder(divide,  "/");
var Negate = builder(negate,  "negate");
var Sin = builder(sin, "sin");
var Cos = builder(cos, "cos");
var Exp = builder(exp, "exp");
var ArcTan = builder(arcTan, "atan");

var binaryOperations = {
	"+" : add,
	"-" : subtract,
	"*" : multiply,
	"/" : divide
}

var unaryOperations = {
	"negate" : negate,
	"Cos" : cos,
	"Sin" : sin,
	"exp" : exp,
	"atan" : arcTan
}

function parsePrefix(s) {
    function throwError(text, pos) {
        var str = "";
        for (var i = 0; i < s.length; i++) {
            str += s[i];
        }
		str = text + " expression: " + str;
		if (pos != undefined)
			str += ", at position: " + pos;
        throw Error(str);
    }
	if (s == "") {
		throw Error("Empty expression");
	} else {
		s = s.replace(/\(/g, " ( ");
		s = s.replace(/\)/g, " ) ");
		s = s.match(/\S+/g);
		var st = [];                   
		for (var i = 0; i < s.length; i++) {
			var now = s[i];
			if (now in variables) {
				st.push(new Variable(now));
				continue;
			}
			if (now == "(" || now in unaryOperations || now in binaryOperations) {
				st.push(now);
				continue;
			}
			if (now == ")") {
				var arr = [];
				while (st.length > 0 && st[st.length - 1] != "(") {
					arr.push(st.pop());
				}
				if (st[st.length - 1] != "(") {
					throwError("we need ( bracket");
				}
				st.pop();
				var operation = arr.pop();
				arr.reverse();
				if (operation in unaryOperations) {
					if(arr.length != 1) {
						throwError("unary operation takes 1 argument, but found " + arr.length + ".", i);
					}
					st.push(new Operation(unaryOperations[operation], operation, arr[0]));
				} else if (operation in binaryOperations) {
					if(arr.length != 2) {
						throwError("binary operation takes 2 argument, but found " + arr.length + ".", i);
					}
					//println(operation);
					st.push(new Operation(binaryOperations[operation], operation, arr[0], arr[1]));
				} else {
					throwError("Invalid operation: " + operation, i);
				}
				continue;
			}
			now = parseFloat(+now);
			if (isNaN(now)) {
				throwError("Invalid token: " + s[i], i);
			}
			st.push(new Const(now));
		}
		if (st.length == 1)
			return st[0];
		throw Error("Missing ).", i);
	}
}

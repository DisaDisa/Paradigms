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

function Const(value) {
	this.value = value;
	this.name = "Const";
}

Const.prototype.evaluate = function () {
	return this.value;
};

Const.prototype.toString = function () {
	return this.value.toString();
};


function Variable(varName) {
	this.varName = varName;
	this.name = "Variable";
}

Variable.prototype.evaluate = function () {
	return arguments[variables[this.varName]];
};


Variable.prototype.toString = function () {
	return this.varName;
};


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
		s = s + this.right.toString() + " ";
	}

	s = s + this.symbol;
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

var binaryOperations = {
	"+" : add,
	"-" : subtract,
	"*" : multiply,
	"/" : divide
}

var unaryOperations = {
	"negate" : negate,
	"Cos" : cos,
	"Sin" : sin
}

var parse = function(s) {
	var stack = [];
	s = s.match(/\S+/g);

	for (var i = 0; i < s.length; i++) {
		if(s[i] in variables) {
			stack.push(new Variable(s[i]));
		}
		else if (s[i] in binaryOperations) {
			var b = stack.pop(), a = stack.pop();
			stack.push(new Operation(binaryOperations[s[i]], s[i]));
		}
		else if (s[i] in unaryOperations) {
			var a = stack.pop();
			stack.push(new Operation(unaryOperations[s[i]], s[i]));
		}
		else {
			stack.push(new Const(parseFloat(s[i])));
		}
	}

	return stack.pop();
}
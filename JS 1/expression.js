var cnst = function (argument) {
	return function () {
		return argument;
	}
};

var variables = {
	"x" : 0,
	"y" : 1,
	"z" : 2
};

var variable = function (name) {
	return function () {
		return arguments[variables[name]];
	}
};

var operation = function (oper) {
	return function (x, y, z) {
		return function () {
			return oper (x && x.apply(null, arguments), y && y.apply(null, arguments), z && z.apply(null, arguments));
		}
	}
};

var add = operation (function (x, y) {
	return x + y;
});

var divide = operation (function (x, y) {
	return x / y;
});

var multiply = operation (function (x, y) {
	return x * y;
});

var mod = operation (function (x, y) {
	return x % y;
});

var power = operation (Math.pow);

var substract = operation (function (x, y) {
	return x - y;
});

var abs = operation (Math.abs);

var log = operation (Math.log);

var negate = operation (function (x) {
	return - x;
});

var mediana = operation (function (x, y, z) {
	arg = [x, y, z];
	arg.sort();
	return arg[1];
});

var operations = {
	"+" : [add, 2],
	"-" : [substract, 2],
	"*" : [multiply, 2],
	"/" : [divide, 2],
	"%" : [mod, 2],
	"**" : [power, 2],
	"abs" : [abs, 1],
	"log" : [log, 1],
	"negate" : [negate, 1],
	"mediana" : [mediana, 3]
};

var parse = function (str) {
	var stack = [];
	str = str.match(/\S+/g);
	for (i = 0; i < str.length; i++) {
		if (str[i] in operations) {
			var argum = [];
			for (j = 0; j < operations[str[i]][1]; j++) {
				argum.push(stack.pop());
			}
			argum.reverse();
			stack.push(operations[str[i]][0] (argum[0], argum[1], argum[2]));
		} else {
			if (str[i] in variables) {
				stack.push(variable(str[i]));
			} else {
				stack.push(cnst(parseFloat(str[i])));
			}		
		}
	}
	return stack.pop();
}

println(parse("x x 2 - * x * 1 +")(5));

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class PostfixEvaluator extends Exception {
	private final static char ADD = '+';
	private final static char SUBTRACT = '-';
	private final static char MULTIPLY = '*';
	private final static char DIVIDE = '/';
	private final static char MODULUS = '%';
	private final static char POWER = '^';
	private final static char UNARY = '~';
	private final static char FACTORIAL = '!';
	private final static char GREATERTHAN = '>';
	private final static char LESSTHAN = '<';
	private final static char EQUALS = '=';
	private final static char AND = '&';
	private final static char OR = '|';
	private final static char TERNARY = '?';

	private Stack<Integer> stack;
	private String errormsg = "Error. Please try again.";

	public PostfixEvaluator() {
		stack = new Stack<Integer>();
	}

	public int evaluate(String expr) throws Exception {
		int op1, op2, op3, result = 0;
		String token;
		Scanner parser = new Scanner(expr);

		while (parser.hasNext()) {

			token = parser.next();
			if (isOperator(token)) {
				op2 = (stack.pop()).intValue();
				op1 = (stack.pop()).intValue();
				result = evaluateSingleOperator(token.charAt(0), op1, op2);
				stack.push(result);
			} else if (isUnaryOperator(token)) {
				op1 = (stack.pop()).intValue();
				result = evaluateUnaryOperator(token.charAt(0), op1);
				stack.push(result);
			} else if (isBooleanOperator(token)) {
				op2 = (stack.pop()).intValue();
				op1 = (stack.pop()).intValue();
				result = evaluateBooleanOperator(token.charAt(0), op1, op2);
				stack.push(result);
			} else if (isTernaryOperator(token)) {
				op3 = (stack.pop()).intValue();
				op2 = (stack.pop()).intValue();
				op1 = (stack.pop()).intValue();
				result = evaluateTernaryOperator(token.charAt(0), op1, op2, op3);
				stack.push(result);
			}

			else {
				stack.push(Integer.parseInt(token));
			}

		}

		parser.close();
		return (stack.pop());
	}

	// Operator Methods
	private boolean isUnaryOperator(String token) {
		return (token.equals("~")) || (token.equals("!"));
	}

	private boolean isBooleanOperator(String token) {
		return (token.equals(">") || token.equals("<") || token.equals("=") || token.equals("&") || token.equals("|"));
	}

	private boolean isTernaryOperator(String token) {
		return (token.equals("?"));
	}

	private boolean isOperator(String token) {
		return (token.equals("+") || token.equals("-") || // OR return ("+-*/".indexOf(token) >= 0);
				token.equals("*") || token.equals("/") || token.equals("%") || token.equals("^"));
	}

	// Evaluation Methods

	private int evaluateTernaryOperator(char ternaryOperation, int op1, int op2, int op3) {
		int result = 0;
		
		switch (ternaryOperation) {
		case TERNARY:
			if (op1 > 0) {
				result = op2;
			} else {
				result = op3;
			}
		}
		return result;
	}
	
	private int evaluateBooleanOperator(char booleanOperation, int op1, int op2) {
		int result = 0;

		switch (booleanOperation) {
		case GREATERTHAN:
			if (op1 > op2) {
				result = 1;
			} else {
				result = 0;
			}
			break;
		case LESSTHAN:
			if (op1 < op2) {
				result = 1;
			} else {
				result = 0;
			}
			break;
		case EQUALS:
			if (op1 == op2) {
				result = 1;
			} else {
				result = 0;
			}
			break;

		case AND:
			if (op1 > 0 && op2 > 0) {
				result = 1;
			} else {
				result = 0;
			}
			break;

		case OR:
			if (op1 > 0 || op2 > 0) {
				result = 1;
			} else {
				result = 0;
			}
			break;

		}

		return result;
	}

	private int evaluateUnaryOperator(char unaryOperation, int op1) {
		int result = 0;

		switch (unaryOperation) {
		case UNARY:
			op1 = -op1;
			result = op1;
			break;
		case FACTORIAL:
			int i, fact = 1;
			for (i = 1; i <= op1; i++) {
				fact = fact * i;
				result = fact;
			}

		}

		return result;

	}

	private int evaluateSingleOperator(char operation, int op1, int op2) {
		int result = 0;

		switch (operation) {
		case ADD:
			result = op1 + op2;
			break;

		case SUBTRACT:
			result = op1 - op2;
			break;

		case MULTIPLY:
			result = op1 * op2;
			break;

		case DIVIDE:
			result = op1 / op2;
			break;
		case MODULUS:
			result = op1 % op2;
			break;
		case POWER:
			result = (int) Math.pow(op1, op2);
			break;
		}

		return result;
	}
}

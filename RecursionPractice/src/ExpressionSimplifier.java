import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExpressionSimplifier {

	final static int ENCLOSED_IN_BRACKETS = -2;
	final static int OPERATOR_NOT_FOUND = -1;

	class Operation {

		//one of {"/","*", "-", "+"}
		protected String operator = "";
		//left-hand-side of operation
		protected Operation lhs = null;
		//righ-hand-side of operation
		protected Operation rhs = null;
		//value is only valid if operator is not provided
		protected double value = 0;

		//constructors
		public Operation(String operator, Operation lhs, Operation rhs) {
			this.operator = operator;
			this.lhs = lhs;
			this.rhs = rhs;
		}

		public Operation(double value) {
			this.value = value;
		}

		public String toString() {
			if (this.operator.isEmpty() == false) {
				String lhs = (this.lhs == null ? "" : this.lhs.toString());
				String rhs = (this.rhs == null ? "" : this.rhs.toString());
				return String.format("(%s)%s(%s)", lhs,operator,rhs);			
			}
			else {
				return Double.toString(this.value);
			}
		}
	}

	//wrapper
	public double simplifyExpression(String expression) {
		//two stop process:
		//1. go through the expression and parse it into a data structure representing the operations, the order of the operations, and values
		Operation node = parseExpression(expression);
		//2. go through the data structure and simplify to a single value
		double result = simplifyExpression(node);
		return result;
	}

	private Operation parseExpression(String expression) {

		int operatorIndex = findHighestOrderOperator(expression);

		if (operatorIndex == OPERATOR_NOT_FOUND) {
			///base case... the expression has not operators, so assume it is a value
			return null;
		}
		else if (operatorIndex == ENCLOSED_IN_BRACKETS) {
			//simple recursive case... just strip the brackets and reparse
			return null;
		}
		else {
			//recursive case... split into left expression and right expression, and combine in an Operation object
			
			return null;
		}		
	}

	private double simplifyExpression(Operation node) {

		if (node.operator.isEmpty()) {
			//base case... no operator, so assume it is a value
			return node.value;
		}
		else {
			//recursive case... evaluate the expression
			
			//placeholder
			return 0;		
		}
		
	}

	//helper function... no need to modify
	//returns the index in the input String of the single-character operator that should be evaluated next
	private static int findHighestOrderOperator(String equation) {

		int location = OPERATOR_NOT_FOUND;
		int start = 0;
		int end = equation.length() - 1;
		int bracketCount = 0;
		boolean additionFound = false;
		boolean multiplicationFound = false;

		//iterate through the String to find the operator
		for (int index = start; index <= end; index++) {
			char current = equation.charAt(index);
			if (current == '(') {
				bracketCount++;
			}
			else if (current == ')') {
				bracketCount--;
			}
			else if ((current == '*' || current == '/') && (bracketCount == 0)) {
				if ((additionFound == false) && (multiplicationFound == false)) {
					location = index;
					multiplicationFound = true;
				}
			}
			else if ((current == '+' || current == '-') && (bracketCount == 0)) {
				if (additionFound == false) {
					location = index;
					additionFound = true;
				}
			}
//			System.out.println(String.format("index %d bracket %d location %d char %c +-%b */%b", index, bracketCount, location, current, additionFound, multiplicationFound));
		}

		if ((location == OPERATOR_NOT_FOUND) && (equation.charAt(start) == '(') && (equation.charAt(end) == ')')) {
			return ENCLOSED_IN_BRACKETS;
		}


		return location;
	}

	//provided for testing purposes
	public static void main(String[] args) {

		String equation = getInput();
		ExpressionSimplifier es = new ExpressionSimplifier();
		double result = es.simplifyExpression(equation);
		System.out.println(result);

	}

	//provided for testing purposes
	private static String getInput() {
		String input = "";
		try {
			System.out.print("Enter an expression:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			input = reader.readLine();
			reader.close();
		} catch (Exception e) {
		}
		return input;
	}


}

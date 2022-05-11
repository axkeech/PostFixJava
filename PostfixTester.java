
import java.util.Scanner;
public class PostfixTester {
	/**
	 * Reads and evaluates multiple postfix expressions.
	 */
	public static void main(String[] args) throws Exception {
		String expression, again;
		int result;

		Scanner in = new Scanner(System.in);
		
		do {
			
			try {
			PostfixEvaluator evaluator = new PostfixEvaluator();

			System.out.println("Enter a valid post-fix expression one token "
					+ "at a time with a space between each token (e.g. 5 4 + 3 2 1 - + *)");
			System.out.println("Each token must be an integer or an operator (+,-,*,/)");
			expression = in.nextLine();

			result = evaluator.evaluate(expression);

			System.out.println();
			System.out.println("That expression equals " + result);
			} catch (Exception e) {
				System.out.println("Invalid postfix expression.");
			}
			System.out.print("Evaluate another expression [Y/N]? ");
			again = in.nextLine();
			System.out.println();
			
		} while (again.equalsIgnoreCase("y"));

		
		
		in.close();
	}
}

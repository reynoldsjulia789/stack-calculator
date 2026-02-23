package src.Model;

/**
 * Handles operators and basic operations with the operator
 * @author Julia Reynolds
 */
public class Operators
{
    /**
     * Checks if a character is an operator
     * @param toCheck the character to evaluate
     * @return true if the character is an operator, false otherwise
     */
    public static boolean isOperator(char toCheck)
    {
        return (toCheck == '+' ||
                toCheck == '-' ||
                toCheck == '*' ||
                toCheck == '/' ||
                toCheck == '%' ||
                toCheck == '^');
    }

    /**
     * Compares two operators to determine precedence when converting
     * an infix expression to a postfix expression
     * @param operator1 operator at the top of the stack
     * @param operator2 current operator
     * @return returns an int representing the order of the operators: > 0 if operator 1 has higher precedence, < 0 if lower precedence, and 0 if equal
     * @throws ArithmeticException throws exception if an invalid operator is passed
     */
    public static int compareOperators(char operator1, char operator2) throws ArithmeticException
    {
        int operator1value, operator2value;

        operator1value = switch (operator1)
        {
            case '+', '-'       -> 2;
            case '*', '/', '%'  -> 4;
            case '^'            -> 5;
            case ')', '('       -> 0;
            default             -> throw new IllegalArgumentException("illegal operator");
        };

        operator2value = switch (operator2)
        {
            case '+', '-'       -> 1;
            case '*', '/', '%'  -> 3;
            case '^'            -> 6;
            case ')'            -> 0;
            case '('            -> 100;
            default             -> throw new IllegalArgumentException("illegal operator");
        };

        return operator1value - operator2value;
    }

    /**
     * Private helper method that evaluates an expression
     * @param left the integer to the left of the operator
     * @param operator addition(+), subtraction(-), multiplication(*), division(/), and exponents(^) are supported
     * @param right the integer to the right of the operator
     * @return int result
     * @throws IllegalArgumentException throws an exception if illegal operator is passed
     * @throws ArithmeticException throws an exception if attempting to divide by 0
     */
    public static int evaluate(int left, char operator, int right) throws IllegalArgumentException, ArithmeticException
    {
        return switch (operator)
        {
            case '+' -> (left + right);
            case '-' -> (left - right);
            case '*' -> (left * right);
            case '^' -> (int) Math.pow(left, right);
            case '/' ->
            {
                if (right == 0)
                {
                    throw new ArithmeticException("divide by 0 error");
                }

                yield (left / right);
            }
            default -> throw new IllegalArgumentException("invalid operator");
        };
    }

    /**
     * Private helper method that evaluates an expression
     * @param left the double to the left of the operator
     * @param operator addition(+), subtraction(-), multiplication(*), division(/), and exponents(^) are supported
     * @param right the double to the right of the operator
     * @return double result
     * @throws IllegalArgumentException throws an exception if illegal operator is passed
     * @throws ArithmeticException throws an exception if attempting to divide by 0
     */
    public static double evaluate(double left, String operator, double right) throws IllegalArgumentException, ArithmeticException
    {
        return switch (operator)
        {
            case "+" -> (left + right);
            case "-" -> (left - right);
            case "*" -> (left * right);
            case "^" -> (int) Math.pow(left, right);
            case "/" ->
            {
                if (right == 0)
                {
                    throw new ArithmeticException("divide by 0 error");
                }

                yield (left / right);
            }
            default -> throw new IllegalArgumentException("invalid operator");
        };
    }
}

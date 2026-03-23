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
     * Checks if a character is an operator
     * @param toCheck the character to evaluate
     * @return true if the character is an operator, false otherwise
     */
    public static boolean isOperator(String toCheck)
    {
        if (toCheck.length() != 1)
        {
            return false;
        }

        return isOperator(toCheck.charAt(0));
    }

    /**
     * Checks if a character is either '(' or ')'
     * @param toCheck the character to evaluate
     * @return true if the character is a parenthesis, false otherwise
     */
    public static boolean isParenthesis(char toCheck)
    {
        return (toCheck == '(' || toCheck == ')');
    }

    /**
     * Compares two operators to determine precedence when converting
     * an infix expression to a postfix expression
     * @param operator1 operator at the top of the stack
     * @param operator2 current operator
     * @return returns an int representing the order of the operators: > 0 if operator 1 has higher precedence, < 0 if lower precedence, and 0 if equal
     * @throws ArithmeticException throws exception if an invalid operator is passed
     */
    public static int compareOperators(String operator1, String operator2) throws ArithmeticException
    {
        int operator1value, operator2value;

        operator1value = switch (operator1)
        {
            case "+", "-"       -> 2;
            case "*", "/", "%"  -> 4;
            case "^"            -> 5;
            case ")", "("       -> 0;
            default             -> throw new IllegalArgumentException("illegal operator");
        };

        operator2value = switch (operator2)
        {
            case "+", "-"       -> 1;
            case "*", "/", "%"  -> 3;
            case "^"            -> 6;
            case ")"            -> 0;
            case "("            -> 100;
            default             -> throw new IllegalArgumentException("illegal operator");
        };

        return operator1value - operator2value;
    }
}

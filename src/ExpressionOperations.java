package src;

public class ExpressionOperations
{
    /**
     * Evaluates a postfix expression and returns the result.
     * Supports + - * / ^ operations and one-digit operands.
     * @param postfixExpression expression must be in postfix form
     * @return int result
     * @throws IllegalArgumentException expression is null or blank
     * @throws ArithmeticException expression is unable to be evaluated, usually caused by a syntax error
     */
    public static int evaluatePostfixExpression(String postfixExpression) throws IllegalArgumentException, ArithmeticException
    {
        char[]           exp;
        int              idx, right, left;
        Stack<Integer>   operands;

        if (postfixExpression == null || postfixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank expression");
        }

        exp      = postfixExpression.trim().toCharArray();
        operands = new Stack<>();

        // Iterate through postfix expression
        for (idx = 0; idx < exp.length; idx++)
        {

            // if the current item is an operand, push to stack
            if (Character.isDigit(exp[idx]))
            {
                operands.push(Character.getNumericValue(exp[idx]));
            }
            else if (isOperator(exp[idx]))
            {
                // pop stack & attach operand to right of operator
                right = operands.pop();

                // pop again and attach operand to left of operator
                left = operands.pop();

                // evaluate and push the results to stack
                operands.push(evaluate(left, exp[idx], right));
            }
        }

        // if only one number left in stack, pop and this is the final result
        if (operands.size == 1)
        {
            return operands.pop();
        }

        // else postfix has error in it
        throw new ArithmeticException("expression could not be evaluated. check expression syntax");
    }

    /**
     * Private helper method that checks if a character is an operator
     * @param toCheck the character to evaluate
     * @return true if the character is an operator, false otherwise
     */
    private static boolean isOperator(char toCheck)
    {
        return (toCheck == '+' ||
                toCheck == '-' ||
                toCheck == '*' ||
                toCheck == '/' ||
                toCheck == '^');
    }

    /**
     * Private helper method that evaluates an expression
     * @param left the integer to the left of the operator
     * @param operator addition(+), subtraction(-), multiplication(*), division(/), and exponents(^) are supported
     * @param right the integer to the right of the operator
     * @return int result
     * @throws ArithmeticException throws an exception if attempting to divide by 0 or use an illegal operator
     */
    private static int evaluate(int left, char operator, int right) throws ArithmeticException
    {
        switch (operator)
        {
            case '+': return (left + right);

            case '-': return (left - right);

            case '*': return (left * right);

            case '^': return (int) Math.pow(left, right);

            case '/':
                if (right == 0)
                {
                    throw new ArithmeticException("divide by 0 error");
                }
                return (left / right);

            default: throw new ArithmeticException("invalid operator");
        }
    }

    /**
     * Evaluates an infix expression by first converting it to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return int result
     */
    public static int evaluateInfixExpression(String infixExpression)
    {
        return evaluatePostfixExpression(convertToPostfix(infixExpression));
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return postfix expression as a String
     * @throws IllegalArgumentException null expression passed, blank/empty String passed, invalid infix expression
     */
    public static String convertToPostfix(String infixExpression) throws IllegalArgumentException
    {
        Stack<Character> operators;
        StringBuilder    postfixExpression;

        // verify expression exists
        if (infixExpression == null || infixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank infix expression");
        }

        postfixExpression = new StringBuilder();
        operators         = new Stack<>();

        return postfixExpression.toString();
    }
}

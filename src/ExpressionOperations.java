package src;

/**
 * A collection of static methods that perform operations on arithmetic expressions,
 * such as evaluating infix and postfix expressions and converting infix expressions to postfix expressions
 * @author Julia Reynolds
 */
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

        // verify expression exists and isn't blank
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
     * Evaluates a postfix expression and returns the result.
     * Supports + - * / ^ operations and one-digit operands.
     * @param postfixExpression expression must be in postfix form and must have whitespace separating tokens
     * @return int result
     * @throws IllegalArgumentException expression is null or blank
     * @throws ArithmeticException expression is unable to be evaluated, usually caused by a syntax error
     */
    public static double evaluatePostfixExpressionWithDoubles(String postfixExpression) throws IllegalArgumentException, ArithmeticException
    {
        double           right, left;
        int              idx;
        String[]         exp;
        Stack<Double>    operands;

        // verify expression exists and isn't blank
        if (postfixExpression == null || postfixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank expression");
        }

        exp      = postfixExpression.trim().split("\\s+");
        operands = new Stack<>();

        // Iterate through postfix expression
        for (idx = 0; idx < exp.length; idx++)
        {
            // if the current item is a double, push to stack
            if (isDouble(exp[idx]))
            {
                operands.push(Double.parseDouble(exp[idx].trim()));
            }
            else if (isInteger(exp[idx]))
            {
                operands.push((double) Integer.parseInt(exp[idx]));
            }
            else if (exp[idx].length() == 1 && isOperator(exp[idx].charAt(0)))
            {
                if (operands.size != 0)
                {
                    // pop stack & attach operand to right of operator
                    right = operands.pop();
                }
                else
                {
                    throw new IllegalArgumentException("syntax error");
                }

                if (operands.size != 0)
                {
                    // pop again and attach operand to left of operator
                    left = operands.pop();
                }
                else
                {
                    throw new IllegalArgumentException("syntax error");
                }

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
        throw new ArithmeticException("syntax error");
    }

    /**
     * Private helper method that checks if a String is a Double
     * @param toCheck the String to check
     * @return true if the String is a double, false if it is not a double
     */
    private static boolean isDouble(String toCheck)
    {
        try
        {
            Double.parseDouble(toCheck.trim());

            return true;
        }
        catch (Exception caught)
        {
            return false;
        }
    }

    /**
     * Private helper method that checks if a String is an Integer
     * @param toCheck the String to check
     * @return true if the String is an int, false if it is not an int
     */
    private static boolean isInteger(String toCheck)
    {
        try
        {
            Integer.parseInt(toCheck);

            return true;
        }
        catch (Exception caught)
        {
            return false;
        }
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
                toCheck == '%' ||
                toCheck == '^');
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
    private static int evaluate(int left, char operator, int right) throws IllegalArgumentException, ArithmeticException
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
    private static double evaluate(double left, String operator, double right) throws IllegalArgumentException, ArithmeticException
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

    /**
     * Evaluates an infix expression by first converting it to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return int result
     */
    public static double evaluateInfixExpressionWithDoubles(String infixExpression)
    {
        return evaluatePostfixExpressionWithDoubles(convertToPostfix(infixExpression.trim().split("\\s+")));
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression expression must be in infix form, cannot have doubles
     * @return postfix expression as a String
     * @throws IllegalArgumentException null expression passed or blank/empty String passed or expression is invalid
     */
    public static String convertToPostfix(String infixExpression) throws IllegalArgumentException
    {
        int           idx;
        StringBuilder builder;

        infixExpression = infixExpression.trim();

        // verify expression exists and isn't blank
        if (infixExpression == null || infixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank infix expression");
        }

        builder = new StringBuilder();

        // convert String to String[]
        for (idx = 0; idx < infixExpression.length(); idx++)
        {
            if (!Character.isWhitespace(infixExpression.charAt(idx)))
            {
                builder.append(infixExpression.charAt(idx));
                builder.append(" ");
            }
        }

        return convertToPostfix(builder.toString().split("\\s+"));
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression String[] containing expression tokens
     * @return postfix expression as a String
     * @throws IllegalArgumentException null expression passed or blank/empty String passed or expression is invalid
     */
    private static String convertToPostfix(String[] infixExpression) throws IllegalArgumentException
    {
        int              idx, lastType;
        Stack<Character> operators;
        StringBuilder    postfixExpression;
        String           expression;

        // verify expression exists and isn't blank
        if (infixExpression == null || infixExpression.length == 0)
        {
            throw new IllegalArgumentException("cannot evaluate null or blank infix expression");
        }

        postfixExpression = new StringBuilder();
        operators         = new Stack<>();
        lastType          = -1;

        // Iterate through postfix expression
        for (idx = 0; idx < infixExpression.length; idx++)
        {
            // write operands to postfix expression
            if (Character.isDigit(infixExpression[idx].charAt(0)) || isVariable(infixExpression[idx]))
            {
                postfixExpression.append(infixExpression[idx]);
                postfixExpression.append(" ");
                lastType = 0;
            }

            // push ( to stack
            else if (infixExpression[idx].equals("("))
            {
                operators.push(infixExpression[idx].charAt(0));
                lastType = 1;
            }

            // write operators in stack to postfix expression until we reach (, discard (
            else if (infixExpression[idx].equals(")"))
            {
                if (lastType == 1)
                {
                    throw new IllegalArgumentException("empty expression");
                }

                if (lastType == 3)
                {
                    throw new IllegalArgumentException("infix syntax error");
                }

                while (operators.size() != 0 && operators.peek() != '(')
                {
                    postfixExpression.append(operators.pop());
                    postfixExpression.append(" ");
                }

                if (operators.size() != 0 && operators.peek() == '(')
                {
                    operators.pop();
                }
                else
                {
                    throw new IllegalArgumentException("parenthesis mismatch");
                }

                lastType = 2;
            }

            // handle operator
            else if (isOperator(infixExpression[idx].charAt(0)))
            {
                if (idx == 0 || idx == infixExpression.length - 1 || lastType == 1 || lastType == 3)
                {
                    throw new IllegalArgumentException("infix syntax error");
                }

                // while stack is not empty and the precedence of the top item > current item
                while (operators.size() > 0 && compareOperators(operators.peek(), infixExpression[idx].charAt(0)) > 0)
                {
                    postfixExpression.append(operators.pop());
                    postfixExpression.append(" ");
                }

                // push current operator onto stack
                operators.push(infixExpression[idx].charAt(0));

                lastType = 3;
            }

            // ignore everything else
        }

        // write any remaining operators to the expression
        while (operators.size() > 0 && isOperator(operators.peek()))
        {
            postfixExpression.append(operators.pop());
            postfixExpression.append(" ");
        }

        if (operators.size() > 0)
        {
            throw new IllegalArgumentException("parenthesis mismatch");
        }

        expression = postfixExpression.toString().trim();

        if (expression.isBlank())
        {
            throw new IllegalArgumentException("empty infix expression");
        }

        return postfixExpression.toString().trim();
    }

    /**
     * Private helper method that compares two operators to determine precedence when converting
     * an infix expression to a postfix expression
     * @param operator1 operator at the top of the stack
     * @param operator2 current operator
     * @return returns an int representing the order of the operators: > 0 if operator 1 has higher precedence, < 0 if lower precedence, and 0 if equal
     * @throws ArithmeticException throws exception if an invalid operator is passed
     */
    private static int compareOperators(char operator1, char operator2) throws ArithmeticException
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

    private static boolean isVariable(String toCheck)
    {
        return (toCheck.equals("A") ||
                toCheck.equals("B") ||
                toCheck.equals("C") ||
                toCheck.equals("D") ||
                toCheck.equals("E") ||
                toCheck.equals("F") ||
                toCheck.equals("G") ||
                toCheck.equals("H") ||
                toCheck.equals("I") ||
                toCheck.equals("J"));
    }
}

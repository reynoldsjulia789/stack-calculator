package src.Model;

import src.Model.ExpressionManipulation;
import src.Model.Operators;
import src.Model.Stack;

/**
 * A collection of static methods that evaluate mathematical expressions.
 * @author Julia Reynolds
 */
public class ExpressionEvaluation
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
            else if (Operators.isOperator(exp[idx]))
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
            else if (exp[idx].length() == 1 && Operators.isOperator(exp[idx].charAt(0)))
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
     * Evaluates an infix expression by first converting it to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return int result
     */
    public static double evaluateInfix(String infixExpression)
    {
        return evaluatePostfixExpressionWithDoubles(ExpressionManipulation.convertToPostfix(infixExpression));
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

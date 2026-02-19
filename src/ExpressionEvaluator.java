package src;

import java.util.Scanner;

public class ExpressionEvaluator
{
    public static void main(String[] args)
    {
        // main method
    }

    /**
     * Evaluates a postfix expression and returns the result
     * @param postfixExpression expression must be in postfix form
     * @return int result
     */
    public int evaluatePostfixExpression(String postfixExpression)
    {
        Scanner postfixScanner;

        if (postfixExpression == null || postfixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank expression");
        }

        postfixScanner = new Scanner(postfixExpression);

        // While there are still items in the postfix expression
        while(postfixScanner.hasNext())
        {
            // if the current item is an operator, push to stack

            // else pop stack & attach operand to right of operator, pop again and attach operand to left of operator,
            // evaluate and push the results
        }

        return -1;
    }

    /**
     * Evaluates an infix expression by first converting it to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return int result
     */
    public int evaluateInfixExpression(String infixExpression)
    {
        return evaluatePostfixExpression(convertToPostfix(infixExpression));
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return postfix expression as a String
     * @throws IllegalArgumentException null expression passed, blank/empty String passed, invalid infix expression
     */
    private String convertToPostfix(String infixExpression) throws IllegalArgumentException
    {
        StringBuilder postfixExpression;

        // verify expression exists
        if (infixExpression == null || infixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank infix expression");
        }

        // verify expression is infix expression

        postfixExpression = new StringBuilder();

        return postfixExpression.toString();
    }
}

package src.Model;

import java.util.ArrayList;

public class ExpressionManipulation
{
    /**
     * Method that takes a string and turns it into a String ArrayList
     * @param expression the mathematical expression to tokenize
     * @return ArrayList of tokens, separated into operators and operands
     */
    public static ArrayList<String> tokenize(String expression)
    {
        char              check;
        int               idx;
        ArrayList<String> tokens;
        StringBuilder     temp;

        tokens = new ArrayList<>();
        temp   = new StringBuilder();

        for (idx = 0; idx < expression.length(); idx++)
        {
            check = expression.charAt(0);

            if (Character.isDigit(check) || check == '.')
            {
                temp.append(check);
            }
            else if (Operators.isOperator(check) || Operators.isParenthesis(check))
            {
                if (!temp.isEmpty())
                {
                    tokens.addLast(temp.toString());
                    temp  .delete(0, temp.length());
                }

                tokens.addLast(Character.toString(check));
            }
        }

        return tokens;
    }

    /**
     * Checks tokenized infix expression for errors,
     * adds '*' if there is a digit after ')',
     * adds 0 before '.' if there is not a digit there.
     * @param infixExpression String infix expression to normalize
     */
    public static void normalizeInfix(String infixExpression)
    {
        normalizeInfix(tokenize(infixExpression));
    }

    /**
     * Checks tokenized infix expression for errors,
     * adds '*' if there is a digit after ')',
     * adds 0 before '.' if there is not a digit there.
     * @param infixExpression tokenized infix expression to normalize
     */
    public static void normalizeInfix(ArrayList<String> infixExpression)
    {

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
            else if (Operators.isOperator(infixExpression[idx].charAt(0)))
            {
                if (idx == 0 || idx == infixExpression.length - 1 || lastType == 1 || lastType == 3)
                {
                    throw new IllegalArgumentException("infix syntax error");
                }

                // while stack is not empty and the precedence of the top item > current item
                while (operators.size() > 0 && Operators.compareOperators(operators.peek(), infixExpression[idx].charAt(0)) > 0)
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
        while (operators.size() > 0 && Operators.isOperator(operators.peek()))
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

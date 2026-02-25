package src.Model;

import java.util.ArrayList;

public class ExpressionManipulation
{
    private enum Type
    {
        UNKNOWN,
        OPERATOR,
        OPERAND,
        LEFT_PARENTHESIS,
        RIGHT_PARENTHESIS
    }

    /**
     * Method that takes a string and turns it into a String ArrayList
     * @param expression the mathematical expression to tokenize
     * @return ArrayList of tokens, separated into operators and operands
     */
    public static ArrayList<String> tokenize(final String expression)
    {
        char              check;
        int               idx, exprLength;
        ArrayList<String> tokens;
        StringBuilder     temp;
        String            expr;

        expr       = expression.trim();
        exprLength = expr.length();

        tokens     = new ArrayList<>();
        temp       = new StringBuilder();


        for (idx = 0; idx < exprLength; idx++)
        {
            check = expr.charAt(idx);

            if (Character.isDigit(check) || check == '.')
            {
                temp.append(check);
            }
            else if (Operators.isOperator(check) || Operators.isParenthesis(check) || Character.isLetter(check))
            {
                if (!temp.isEmpty())
                {
                    tokens.addLast(temp.toString());
                    temp  .delete(0, temp.length());
                }

                tokens.addLast(Character.toString(check));
            }
        }

        if (!temp.isEmpty())
        {
            tokens.addLast(temp.toString());
        }

        return tokens;
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression expression must be in infix form
     * @return postfix expression as an ArrayList<String>
     * @throws IllegalArgumentException null expression passed or blank/empty String passed or expression is invalid
     */
    public static ArrayList<String> convertToPostfix(String infixExpression) throws IllegalArgumentException
    {
        infixExpression = infixExpression.trim();

        // verify expression exists and isn't blank
        if (infixExpression == null || infixExpression.isBlank())
        {
            throw new IllegalArgumentException("cannot evaluate null or blank infix expression");
        }

        return convertToPostfix(tokenize(infixExpression));
    }

    /**
     * Converts an infix expression to a postfix expression
     * @param infixExpression ArrayList<String> containing expression tokens
     * @return a new ArrayList<String> containing the expression in postfix form
     * @throws IllegalArgumentException null expression passed or blank/empty String passed or expression is invalid
     */
    private static ArrayList<String> convertToPostfix(ArrayList<String> infixExpression) throws IllegalArgumentException
    {
        int               idx, expLength;
        Stack<String>     operators;
        ArrayList<String> postfixExpression;
        String            token;
        Type              lastType;

        expLength = infixExpression.size();

        // verify expression isn't blank
        if (expLength == 0)
        {
            throw new IllegalArgumentException("cannot convert empty infix expression");
        }

        postfixExpression = new ArrayList<>();
        operators         = new Stack<>();
        lastType          = Type.UNKNOWN;

        // Iterate through postfix expression
        for (idx = 0; idx < expLength; idx++)
        {
            token = infixExpression.get(idx);

            // write operands to postfix expression
            if (Character.isDigit(token.charAt(0)) || isVariable(token))
            {
                // if last Type was ')', convert as if there was a '*'
                if (lastType == Type.RIGHT_PARENTHESIS)
                {
                    // push * onto stack
                    operators.push("*");
                }

                postfixExpression.addLast(token);
                lastType = Type.OPERAND;
            }

            // push ( to stack
            else if (token.equals("("))
            {
                operators.push(token);
                lastType = Type.LEFT_PARENTHESIS;
            }

            // write operators in stack to postfix expression until we reach (, discard (
            else if (token.equals(")"))
            {
                if (lastType == Type.LEFT_PARENTHESIS)
                {
                    throw new IllegalArgumentException("empty expression");
                }

                if (lastType == Type.OPERATOR)
                {
                    throw new IllegalArgumentException("infix syntax error");
                }

                while (operators.size() != 0 && !operators.peek().equals("("))
                {
                    postfixExpression.addLast(operators.pop());
                }

                if (operators.size() != 0 && operators.peek().equals("("))
                {
                    operators.pop();
                }
                else
                {
                    throw new IllegalArgumentException("parenthesis mismatch");
                }

                lastType = Type.RIGHT_PARENTHESIS;
            }

            // handle operator
            else if (Operators.isOperator(token))
            {
                if (idx == 0 || idx == infixExpression.size() - 1 || lastType == Type.LEFT_PARENTHESIS || lastType == Type.OPERATOR)
                {
                    throw new IllegalArgumentException("infix syntax error");
                }

                // while stack is not empty and the precedence of the top item > current item
                while (operators.size() > 0 && Operators.compareOperators(operators.peek(), token) > 0)
                {
                    postfixExpression.addLast(operators.pop());
                }

                // push current operator onto stack
                operators.push(token);

                lastType = Type.OPERATOR;
            }

            // ignore everything else
        }

        // write any remaining operators to the expression
        while (operators.size() > 0 && Operators.isOperator(operators.peek()))
        {
            postfixExpression.addLast(operators.pop());
        }

        if (operators.size() > 0)
        {
            throw new IllegalArgumentException("parenthesis mismatch");
        }

        if (postfixExpression.isEmpty())
        {
            throw new IllegalArgumentException("empty infix expression");
        }

        return postfixExpression;
    }

    /**
     * Helper method to determine if a String is a variable.
     * Variables must be single characters. Variables can be both upper and lowercase.
     * @param toCheck the String to check
     * @return true if the String is a variable, false otherwise
     */
    private static boolean isVariable(String toCheck)
    {
        if (toCheck.trim().length() != 1)
        {
            return false;
        }

        return Character.isLetter(toCheck.charAt(0));
    }
}

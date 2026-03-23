package src.Model;

import java.util.ArrayList;

/**
 * This class handles expression conversion and evaluation.
 * @author Julia Reynolds
 */
public class Expression
{
    private final ArrayList<String> infixExpression;
    private boolean                 infixUsesVariables;
    private ArrayList<String>       postfixExpression;
    private double                  result;
    private String                  postfixErrorMsg, resultErrorMsg;

    private enum Type
    {
        UNKNOWN,
        OPERATOR,
        OPERAND,
        LEFT_PARENTHESIS,
        RIGHT_PARENTHESIS
    }

    /**
     * Constructor
     * @param expression a String representing an infix expression
     * @throws IllegalArgumentException if passed expression is null
     */
    public Expression(final String expression) throws IllegalArgumentException
    {
        if (expression == null || expression.trim().isBlank())
        {
            throw new IllegalArgumentException("expression cannot be null");
        }

        this.infixExpression = tokenize(expression);

        this.initialize();
    }

    /**
     * Constructor
     * @param tokenizedExpression an array list containing an infix expression split into tokens
     * @throws IllegalArgumentException if passed expression is null
     */
    public Expression(final ArrayList<String> tokenizedExpression) throws IllegalArgumentException
    {
        if (tokenizedExpression == null)
        {
            throw new IllegalArgumentException("expression cannot be");
        }

        this.infixExpression = new ArrayList<>(tokenizedExpression);

        this.initialize();
    }

    /**
     * Attempts to convert the expression to a postfix expression and evaluate.
     * Stores any errors that arise.
     */
    private void initialize()
    {
        try
        {
            this.postfixExpression = convertToPostfix();
            this.postfixErrorMsg   = null;
        }
        catch (Exception caught)
        {
            this.postfixErrorMsg   = caught.getMessage();
            this.postfixExpression = null;
        }

        if (!this.infixUsesVariables && this.postfixErrorMsg == null)
        {
            try
            {
                this.result         = evaluatePostfixExpression();
                this.resultErrorMsg = null;
            }
            catch (Exception caught)
            {
                this.resultErrorMsg = caught.getMessage();
            }
        }
    }

    /**
     * Returns the infix version of the expression to the caller
     * @return returns a String representing the infix expression
     */
    public String getInfix()
    {
        int           idx;
        StringBuilder builder;

        builder = new StringBuilder();

        for (idx = 0; idx < this.infixExpression.size(); idx++)
        {
            builder.append(this.infixExpression.get(idx)).append(" ");
        }

        return builder.toString().trim();
    }

    /**
     * Returns the postfix version of the expression to the caller
     * @return returns a String representing the postfix expression
     */
    public String getPostfix()
    {
        int           idx;
        StringBuilder builder;

        if (this.postfixErrorMsg != null)
        {
            throw new ArithmeticException(this.postfixErrorMsg);
        }

        builder = new StringBuilder();

        for (idx = 0; idx < this.postfixExpression.size(); idx++)
        {
            builder.append(this.postfixExpression.get(idx)).append(" ");
        }

        return builder.toString().trim();
    }

    /**
     * Returns the result of the expression to the caller
     * @return a double representing the result of evaluating the expression
     */
    public double getResult()
    {
        if (this.postfixErrorMsg != null)
        {
            throw new ArithmeticException(this.postfixErrorMsg);
        }

        if (this.infixUsesVariables)
        {
            throw new ArithmeticException("expression uses variables. Could not evaluate.");
        }

        if (this.resultErrorMsg != null)
        {
            throw new ArithmeticException(this.resultErrorMsg);
        }

        return this.result;
    }

    /**
     * Method that takes a string and turns it into a String ArrayList separated into operators and operands
     * @param expression the mathematical expression to tokenize
     */
    private ArrayList<String> tokenize(final String expression)
    {
        char              check;
        int               idx, exprLength;
        ArrayList<String> tokens;
        StringBuilder     temp;
        String            expr;

        if (expression == null || expression.trim().isBlank())
        {
            throw new IllegalArgumentException("expression is null or blank");
        }

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
     * @throws IllegalArgumentException expression is invalid
     */
    private ArrayList<String> convertToPostfix() throws IllegalArgumentException
    {
        int               idx, expLength;
        Stack<String>     operators;
        ArrayList<String> postfixExpression;
        String            token;
        Type              lastType;

        expLength               = this.infixExpression.size();
        this.infixUsesVariables = false;
        postfixExpression       = new ArrayList<>();
        operators               = new Stack<>();
        lastType                = Type.UNKNOWN;

        // Iterate through postfix expression
        for (idx = 0; idx < expLength; idx++)
        {
            token = this.infixExpression.get(idx);

            // write operands to postfix expression
            if (isDouble(token) || isVariable(token))
            {
                if (isVariable(token))
                {
                    this.infixUsesVariables = true;
                }

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
                // if last Type was OPERAND handle as *
                if (lastType == Type.OPERAND)
                {
                    // push * onto stack
                    operators.push("*");
                }

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
    private boolean isVariable(String toCheck)
    {
        if (toCheck.trim().length() != 1)
        {
            return false;
        }

        return Character.isLetter(toCheck.charAt(0));
    }

    /**
     * Evaluates a postfix expression.
     * Supports + - * / ^ operations and one-digit operands.
     * @throws IllegalArgumentException due to syntax error
     * @throws ArithmeticException expression is unable to be evaluated, usually caused by a syntax error
     */
    private double evaluatePostfixExpression() throws IllegalArgumentException, ArithmeticException
    {
        double           right, left;
        int              idx;
        Stack<Double>    operands;
        String[]         exp;

        operands = new Stack<>();
        exp      = this.postfixExpression.toArray(new String[0]);

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
    private boolean isDouble(String toCheck)
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
     * Private helper method that evaluates an expression
     * @param left the double to the left of the operator
     * @param operator addition(+), subtraction(-), multiplication(*), division(/), and exponents(^) are supported
     * @param right the double to the right of the operator
     * @return double result
     * @throws IllegalArgumentException throws an exception if illegal operator is passed
     * @throws ArithmeticException throws an exception if attempting to divide by 0
     */
    private double evaluate(double left, String operator, double right) throws IllegalArgumentException, ArithmeticException
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
     * To string for expression
     * @return returns a String in the form of "infix expression = result" unless there is an error or the expression
     * uses variables, in which case "infix expression -> error" or "infix w/ variables -> postfix w/ variables"
     * will be returned
     */
    @Override
    public String toString()
    {
        StringBuilder builder;

        builder = new StringBuilder();

        builder.append(this.getInfix());

        if (postfixErrorMsg != null)
        {
            builder.append(" -> ").append(this.postfixErrorMsg);
        }
        else if (infixUsesVariables)
        {
            builder.append(" -> ").append(this.getPostfix());
        }
        else if (resultErrorMsg != null)
        {
            builder.append(" -> ").append(this.resultErrorMsg);
        }
        else
        {
            builder.append(" = ").append(this.result);
        }

        return builder.toString();
    }
}

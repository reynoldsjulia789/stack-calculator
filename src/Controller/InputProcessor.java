package src.Controller;

import src.Model.ExpressionOperations;
import src.Model.Stack;
import src.Model.Tokenizer;
import src.Model.Operators;
import static src.Model.ExpressionOperations.evaluateInfixExpressionWithDoubles;

/**
 * The purpose of this class is to process user input from the user interface
 * @author Julia Reynolds
 */
public class InputProcessor
{
    private StringBuilder  input;
    private Stack<Integer> charsAdded;

    public InputProcessor()
    {
        this.input = new StringBuilder();
    }

    public String pressed(String buttonValue)
    {
        this.input.append(buttonValue);
        return this.input.toString();
    }

    public String compute()
    {
        Double result;

        // tokenize input

        // evaluate expression and return result
        result = ExpressionOperations.evaluateInfixExpressionWithDoubles("1");

        return result.toString();
    }

    public String convertExpression()
    {
        return ExpressionOperations.convertToPostfix(this.input.toString());
    }

}

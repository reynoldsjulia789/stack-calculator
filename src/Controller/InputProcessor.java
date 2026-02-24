package src.Controller;

import src.Model.ExpressionEvaluation;
import src.Model.ExpressionManipulation;
import src.Model.Stack;

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
        try
        {
            return "" + ExpressionEvaluation.evaluateInfix(this.input.toString());
        }
        catch (Exception caught)
        {
            return caught.getMessage();
        }
    }

    public String convertExpression()
    {
        return ExpressionManipulation.convertToPostfix(this.input.toString());
    }

}

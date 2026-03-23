package src.Controller;

import src.Model.Expression;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * The purpose of this class is to process user input from the GUI
 * @author Julia Reynolds
 */
public class InputProcessor
{
    private ArrayList<String>      input;
    private LinkedList<Expression> history;
    private Double                 lastResult;

    /**
     * Constructor for Input Processor
     */
    public InputProcessor()
    {
        this.input      = new ArrayList<>();
        this.history    = new LinkedList<>();
        this.lastResult = null;
    }

    /**
     * Adds the value of the pressed button to the expression
     * @param buttonValue String representing the value of the pressed button
     * @return returns the updated expression
     */
    public String pressed(String buttonValue)
    {
        this.input.addLast(buttonValue);

        return this.toString();
    }

    /**
     * Deletes the last input from the inputted expression.
     * @return returns the updated expression
     */
    public String deleteLastInput()
    {
        this.input.removeLast();

        return this.toString();
    }

    /**
     * Computes the result of the inputted expression, and stores the result and expression.
     * @return returns a string with the double result
     */
    public String compute()
    {
        Expression expression;

        try
        {
            expression      = new Expression(this.input.toString());
            this.lastResult = expression.getResult();

            this.history.addFirst(expression);

            this.input.clear();
            this.input.addLast(lastResult.toString());

            return this.lastResult.toString();
        }
        catch (Exception caught)
        {
            return caught.getMessage();
        }
    }

    /**
     * Gets the last expression stored in the history and returns it.
     * @return returns the last infix expression
     */
    public String getLastExpression()
    {
        return this.history.getFirst().toString();
    }

    /**
     * Input processor toString
     * @return returns the input expression as a string
     */
    public String toString()
    {
        int           idx;
        StringBuilder builder;

        builder = new StringBuilder();

        for (idx = 0; idx < this.input.size(); idx++)
        {
            builder.append(this.input.get(idx)).append(" ");
        }

        return builder.toString().trim();
    }
}

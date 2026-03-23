package src.View;

import src.Model.Expression;

import java.util.Scanner;

/**
 * Command Line Interface for Calculator
 * @author Julia Reynolds
 */
public class CalculatorCLI
{
    /**
     * Main method.
     * Reads user input,
     * provides feedback to user if there are errors,
     * and displays the result of the entered expression
     */
    public static void main(String[] args)
    {
        int     result;
        String  infixExpression, postfixExpression;
        Scanner userInput;

        // Welcome user
        System.out.println("Welcome to the Stack Calculator!\r\n");
        System.out.println("This calculator processes infix expressions (can include spaces).\r\n" +
                           "It only handles integers 0-9. No decimals or numbers > 9\r\n");

        userInput = new Scanner(System.in);

        while (true)
        {
            // Read inputted user expression
            System.out.print("Please enter the infix expression to process:\t\t");
            infixExpression = userInput.nextLine();

            // Try to evaluate expression
            try
            {
                // convert infix expression to postfix expression
                postfixExpression = Expression.convertToPostfix(infixExpression);

                // Print postfix expression
                System.out.println("The postfix expression for the input infix is:\t\t" + postfixExpression);

                // Try to evaluate expression
                result = ExpressionEvaluation.evaluatePostfixExpression(postfixExpression);

                // Print results
                System.out.println("The final result after evaluating the postfix is:\t" + result);

                // Ask user if they would like to evaluate another expression
                System.out.print("Do you have another expression you would like to process? (y/n)\t");
                switch (userInput.nextLine().trim())
                {
                    case "y", "Y", "yes", "Yes", "YES" -> System.out.println();
                    case "n", "N", "no", "No", "NO"    ->
                    {
                        System.out.println("\r\nThank you for using the Stack Calculator!");
                        return;
                    }
                    default                            -> System.out.println("Please provide a y/n answer.");
                }
            }
            catch (Exception caught)
            {
                System.out.println("There was an error evaluating your expression.");
                System.out.println("Error message: " + caught.getMessage() + "\r\n");
            }
        }
    }
}

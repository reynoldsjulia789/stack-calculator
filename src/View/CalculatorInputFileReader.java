package src.View;

import src.Model.Expression;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads from provided input files and writes solution to output file
 * @author Julia Reynolds
 */
public class CalculatorInputFileReader
{
    private static Double A, B, C, D, E, F, G, H, I, J;

    /**
     * Main method.
     * Reads and stores the value of variables from a file.
     * Reads expressions from a file and evaluates.
     * Prints results to output file.
     */
    public static void main(String[] args)
    {
        // Read in value of variables from file
        getVariableValuesFromFile("src/InputFiles/hw5_input2.txt");

        // Read and evaluate expressions, printing results to output file
        EvaluateExpressionsFromFile("src/InputFiles/hw5_input3.txt", "hw5_output.txt");
    }

    /**
     * Reads variable values from file and stores them.
     * @param filePath path to the file to read from
     */
    private static void getVariableValuesFromFile(String filePath)
    {
        String[]  tokens;

        try (Scanner fileReader = new Scanner(new File(filePath)))
        {
            while (fileReader.hasNextLine())
            {
                tokens = fileReader.nextLine().trim().split("\\s+");

                switch (tokens[0])
                {
                    case "A" -> A = Double.parseDouble(tokens[2]);
                    case "B" -> B = Double.parseDouble(tokens[2]);
                    case "C" -> C = Double.parseDouble(tokens[2]);
                    case "D" -> D = Double.parseDouble(tokens[2]);
                    case "E" -> E = Double.parseDouble(tokens[2]);
                    case "F" -> F = Double.parseDouble(tokens[2]);
                    case "G" -> G = Double.parseDouble(tokens[2]);
                    case "H" -> H = Double.parseDouble(tokens[2]);
                    case "I" -> I = Double.parseDouble(tokens[2]);
                    case "J" -> J = Double.parseDouble(tokens[2]);
                }
            }
        }
        catch (Exception caught)
        {
            System.out.println("Error reading file.\r\nError message: " + caught.getMessage());
        }
    }

    /**
     * Reads expressions from a file and prints results to output file
     * @param inputFilePath path to the file to read expressions from
     * @param outputFileName name of the file to print results to
     */
    private static void EvaluateExpressionsFromFile(String inputFilePath, String outputFileName)
    {
        PrintStream fileWriter;
        Expression  expression, varExpression;

        try (Scanner fileReader = new Scanner(new File(inputFilePath)))
        {
            fileWriter = new PrintStream(outputFileName);

            while (fileReader.hasNext())
            {
                varExpression = new Expression(fileReader.nextLine().trim());

                // print original infix expression
                fileWriter.print(varExpression.getInfix() + " --> ");
                System.out.print(varExpression.getInfix() + " --> ");

                try
                {
                    // print corresponding postfix expression
                    fileWriter.print(varExpression.getPostfix() + " --> ");
                    System.out.print(varExpression.getPostfix() + " --> ");

                    // replace variables in expression
                    expression = new Expression(findAndReplaceVariables(varExpression.getInfix()));

                    // print corresponding postfix expression
                    fileWriter.print(expression.getPostfix() + " --> ");
                    System.out.print(expression.getPostfix() + " --> ");

                    // evaluate postfix expression
                    fileWriter.println(expression.getResult());
                    System.out.println(expression.getResult());
                }
                catch (Exception caught)
                {
                    fileWriter.println(caught.getMessage());
                    System.out.println(caught.getMessage());
                }
            }
        }
        catch (Exception caught)
        {
            System.out.println("File IO Error.\r\nError message: " + caught.getMessage());
        }
    }

    /**
     * Private helper method that replaces the variable names in an expression with the value of the variable
     * @param originalExpression the expression with variable names, tokens must be separated by whitespace
     * @return returns a String Array List expression with values of the variables instead of variable names
     */
    private static ArrayList<String> findAndReplaceVariables(String originalExpression)
    {
        int               idx;
        String[]          tokens;
        ArrayList<String> newExpression;

        tokens        = originalExpression.split("\\s+");
        newExpression = new ArrayList<>();

        for (idx = 0; idx < tokens.length; idx++)
        {
            switch (tokens[idx])
            {
                case "A" -> newExpression.addLast(A.toString());
                case "B" -> newExpression.addLast(B.toString());
                case "C" -> newExpression.addLast(C.toString());
                case "D" -> newExpression.addLast(D.toString());
                case "E" -> newExpression.addLast(E.toString());
                case "F" -> newExpression.addLast(F.toString());
                case "G" -> newExpression.addLast(G.toString());
                case "H" -> newExpression.addLast(H.toString());
                case "I" -> newExpression.addLast(I.toString());
                case "J" -> newExpression.addLast(J.toString());
                default  -> newExpression.addLast(tokens[idx]);
            }
        }

        return newExpression;
    }
}

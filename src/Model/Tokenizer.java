package src.Model;

import java.util.ArrayList;
import java.util.Scanner;

public class Tokenizer
{
    /**
     * Method that takes a string and turns it into a String ArrayList
     * @param expression the mathematical expression to tokenize
     * @return ArrayList of tokens, separated into operators and operands
     */
    public static ArrayList<String> tokenize(String expression)
    {
        ArrayList<String> tokens;
        Scanner expressionScanner;

        tokens            = new ArrayList<>();
        expressionScanner = new Scanner(expression);

        while (expressionScanner.hasNext())
        {
            if (expressionScanner.hasNextDouble())
            {
                tokens.addLast(expressionScanner.nextDouble() + "");
            }
            else if (expressionScanner.hasNextInt())
            {
                tokens.addLast(expressionScanner.nextInt() + "");
            }
            else
            {
                tokens.addLast(expressionScanner.next());
            }
        }

        return tokens;
    }
}

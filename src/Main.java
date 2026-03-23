package src;

import src.View.CalculatorInputFileReader;
import src.View.CalculatorCLI;
import src.View.CalculatorGUI;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        boolean repeat;
        Scanner userInput;

        System.out.println("Welcome!");
        System.out.println("Please choose one of the following: ");
        System.out.println("A: Calculator GUI");
        System.out.println("B: Calculator CLI");
        System.out.println("C: Calculator File I/O Demo Using Variables");
        System.out.print("\r\n   Your choice:  ");

        userInput = new Scanner(System.in);
        repeat    = false;

        do
        {
            switch (userInput.nextLine().trim())
            {
                case "A", "a" ->
                {
                    System.out.println("\r\nLaunching GUI...");
                    new CalculatorGUI();
                }
                case "B", "b" ->
                {
                    System.out.println("\r\nLaunching CLI...\r\n");
                    CalculatorCLI.main(args);
                }
                case "C", "c" ->
                {
                    System.out.println("\r\nCalculator File I/O Demo\r\n");
                    CalculatorInputFileReader.main(args);
                }
                default ->
                {
                    System.out.print("   Please provide a valid input:  ");
                    repeat = true;
                }
            }
        }
        while (repeat);
    }
}

package test;

import src.ExpressionOperations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionOperationsTest
{
    @Nested
    @DisplayName("postfix expression evaluation tests")
    class evaluatePostfix
    {
        @Test
        @DisplayName("Basic Addition")
        public void add()
        {
            assertEquals(4, ExpressionOperations.evaluatePostfixExpression("22+"));
        }

        @Test
        @DisplayName("Basic Subtraction")
        public void subtract()
        {
            assertEquals(1, ExpressionOperations.evaluatePostfixExpression("43-"));
        }

        @Test
        @DisplayName("Basic Multiplication")
        public void multiply()
        {
            assertEquals(10, ExpressionOperations.evaluatePostfixExpression("25*"));
        }

        @Test
        @DisplayName("Basic Integer Division")
        public void divide()
        {
            assertEquals(2, ExpressionOperations.evaluatePostfixExpression("52/"));
        }

        @Test
        @DisplayName("Throws exception if attempting to divide by 0")
        public void divide0()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpression("20/"));
        }

        @Test
        @DisplayName("Basic Exponent")
        public void exponent()
        {
            assertEquals(25, ExpressionOperations.evaluatePostfixExpression("52^"));
        }

        @Test
        @DisplayName("Handles spaces between characters")
        public void space()
        {
            assertEquals(4, ExpressionOperations.evaluatePostfixExpression("2 2 +"));
        }

        @Test
        @DisplayName("Handles space at front of string")
        public void spacefront()
        {
            assertEquals(4, ExpressionOperations.evaluatePostfixExpression(" 22+"));
        }

        @Test
        @DisplayName("Handles space at end of string")
        public void spaceend()
        {
            assertEquals(4, ExpressionOperations.evaluatePostfixExpression("22+ "));
        }

        @Test
        @DisplayName("evaluates complex expression")
        public void complex()
        {
            assertEquals(4, ExpressionOperations.evaluatePostfixExpression("1 2 + 3 4 - - 6 5 - /"));
        }

        @Test
        @DisplayName("throws exception if expression is blank")
        public void blank()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpression(" "));
        }

        @Test
        @DisplayName("throws exception if expression is empty")
        public void empty()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpression(""));
        }

        @Test
        @DisplayName("throws exception if expression is null")
        public void nullexp()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpression(null));
        }
    }

    @Nested
    class convertInfixToPostfix
    {

    }

    @Nested
    class evaluateInfix
    {

    }
}

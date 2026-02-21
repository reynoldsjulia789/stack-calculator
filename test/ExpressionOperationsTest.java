package test;

import src.ExpressionOperations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests to verify the methods in the ExpressionOperations class are working as intended
 * @author Julia Reynolds
 */
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
        public void spaceFront()
        {
            assertEquals(4, ExpressionOperations.evaluatePostfixExpression(" 22+"));
        }

        @Test
        @DisplayName("Handles space at end of string")
        public void spaceEnd()
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
        public void nullExp()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpression(null));
        }
    }

    @Nested
    @DisplayName("postfix expression with doubles evaluation tests")
    class evaluatePostfixDoubles
    {
        @Test
        @DisplayName("Basic Addition")
        public void add()
        {
            assertEquals(4.0, ExpressionOperations.evaluatePostfixExpressionWithDoubles("2 2 +"));
        }

        @Test
        @DisplayName("Basic Subtraction")
        public void subtract()
        {
            assertEquals(1.0, ExpressionOperations.evaluatePostfixExpressionWithDoubles("43.5 42.5 -"));
        }

        @Test
        @DisplayName("Basic Multiplication")
        public void multiply()
        {
            assertEquals(10.0, ExpressionOperations.evaluatePostfixExpressionWithDoubles("2.0 5.0 *"));
        }

        @Test
        @DisplayName("Basic Integer Division")
        public void divide()
        {
            assertEquals(2.5, ExpressionOperations.evaluatePostfixExpressionWithDoubles("5 2 /"));
        }

        @Test
        @DisplayName("Throws exception if attempting to divide by 0")
        public void divide0()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpressionWithDoubles("2 0 /"));
        }

        @Test
        @DisplayName("Basic Exponent")
        public void exponent()
        {
            assertEquals(25.0, ExpressionOperations.evaluatePostfixExpressionWithDoubles("5 2.0 ^"));
        }

        @Test
        @DisplayName("evaluates complex expression")
        public void complex()
        {
            assertEquals(4.0, ExpressionOperations.evaluatePostfixExpressionWithDoubles("1 2 + 3 4 - - 6 5 - /"));
        }

        @Test
        @DisplayName("throws exception if expression is blank")
        public void blank()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpressionWithDoubles(" "));
        }

        @Test
        @DisplayName("throws exception if expression is empty")
        public void empty()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpressionWithDoubles(""));
        }

        @Test
        @DisplayName("throws exception if expression is null")
        public void nullExp()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.evaluatePostfixExpressionWithDoubles(null));
        }
    }

    @Nested
    @DisplayName("Infix conversion tests")
    class convertInfixToPostfix
    {
        @Test
        @DisplayName("Converts simple addition")
        public void addition()
        {
            assertEquals("2 2 +", ExpressionOperations.convertToPostfix("2+2"));
        }

        @Test
        @DisplayName("Converts simple subtraction w ()")
        public void subtraction()
        {
            assertEquals("2 2 -", ExpressionOperations.convertToPostfix("(2-2)"));
        }

        @Test
        @DisplayName("Converts complex expression with ()")
        public void complex1()
        {
            assertEquals("1 2 + 3 4 - - 6 5 - /", ExpressionOperations.convertToPostfix("(((1+2)-(3-4))/(6-5))"));
        }

        @Test
        @DisplayName("Converts complex expression no ()")
        public void complex2()
        {
            assertEquals("2 4 * 2 2 1 ^ ^ -", ExpressionOperations.convertToPostfix("2*4-2^2^1"));
        }

        @Test
        @DisplayName("Handles passed postfix expression")
        public void alreadyPostfix()
        {
            assertEquals("2 2 +", ExpressionOperations.convertToPostfix("22+"));
        }

        @Test
        @DisplayName("Throws exception if expression is invalid")
        public void invalidExpression()
        {
            assertThrows(Exception.class, () -> ExpressionOperations.convertToPostfix("2(2+"));
            assertThrows(Exception.class, () -> ExpressionOperations.convertToPostfix(")22+"));
        }

        @Nested
        @DisplayName("Throws exception for null or blank expression")
        class nullBlankTests
        {
            @Test
            @DisplayName("throws exception if expression is blank")
            public void blank()
            {
                assertThrows(Exception.class, () -> ExpressionOperations.convertToPostfix(" "));
            }

            @Test
            @DisplayName("throws exception if expression is empty")
            public void empty()
            {
                assertThrows(Exception.class, () -> ExpressionOperations.convertToPostfix(""));
            }

            @Test
            @DisplayName("throws exception if expression is null")
            public void nullExp()
            {
                assertThrows(Exception.class, () -> ExpressionOperations.convertToPostfix(null));
            }
        }

        @Nested
        @DisplayName("Handles spaces in expression")
        class spaceTests
        {
            @Test
            @DisplayName("Handles spaces between")
            public void spaces()
            {
                assertEquals("2 2 *", ExpressionOperations.convertToPostfix("2 * 2"));
            }

            @Test
            @DisplayName("Handles space at front")
            public void spaceFront()
            {
                assertEquals("2 2 /", ExpressionOperations.convertToPostfix(" 2/2"));
            }

            @Test
            @DisplayName("Handles space at end")
            public void spaceEnd()
            {
                assertEquals("2 2 ^", ExpressionOperations.convertToPostfix("2^2 "));
            }
        }

        @Nested
        @DisplayName("Correctly determines operator precedence")
        class precedenceTests
        {
            @Test
            @DisplayName("Multiplication has higher precedence than addition")
            public void addMultiply()
            {
                assertEquals("2 3 4 * +", ExpressionOperations.convertToPostfix("2 + 3 * 4"));
            }

            @Test
            @DisplayName("Division has higher precedence than subtraction")
            public void subtractDivide()
            {
                assertEquals("2 3 4 / -", ExpressionOperations.convertToPostfix("2 - 3 / 4"));
            }

            @Test
            @DisplayName("Exponent has higher precedence than multiplication")
            public void exponentAdd()
            {
                assertEquals("2 3 4 ^ *", ExpressionOperations.convertToPostfix("2 * 3 ^ 4"));
            }

            @Test
            @DisplayName("() has higher precedence than ^")
            public void parenthesisExponent()
            {
                assertEquals("2 3 + 4 ^", ExpressionOperations.convertToPostfix("(2 + 3) ^ 4"));
            }
        }
    }
}

package test;

import src.Model.ExpressionEvaluation;
import src.Model.ExpressionManipulation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests to ensure the methods in the ExpressionManipulation class are working as intended
 * @author Julia Reynolds
 */
public class ExpressionManipulationTest
{
    @Nested
    @DisplayName("Infix conversion tests")
    class convertInfixToPostfix
    {
        @Test
        @DisplayName("Converts simple addition")
        public void addition()
        {
            assertEquals("2 2 +", ExpressionEvaluation.convertToPostfix("2+2"));
        }

        @Test
        @DisplayName("Converts simple subtraction w ()")
        public void subtraction()
        {
            assertEquals("2 2 -", ExpressionEvaluation.convertToPostfix("(2-2)"));
        }

        @Test
        @DisplayName("Converts complex expression with ()")
        public void complex1()
        {
            assertEquals("1 2 + 3 4 - - 6 5 - /", ExpressionEvaluation.convertToPostfix("(((1+2)-(3-4))/(6-5))"));
        }

        @Test
        @DisplayName("Converts complex expression no ()")
        public void complex2()
        {
            assertEquals("2 4 * 2 2 1 ^ ^ -", ExpressionEvaluation.convertToPostfix("2*4-2^2^1"));
        }

        @Test
        @DisplayName("Throws exception if expression is invalid")
        public void invalidExpression()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.convertToPostfix("2(2+"));
            assertThrows(Exception.class, () -> ExpressionEvaluation.convertToPostfix(")22+"));
        }

        @Nested
        @DisplayName("Throws exception for null or blank expression")
        class nullBlankTests
        {
            @Test
            @DisplayName("throws exception if expression is blank")
            public void blank()
            {
                assertThrows(Exception.class, () -> ExpressionEvaluation.convertToPostfix(" "));
            }

            @Test
            @DisplayName("throws exception if expression is empty")
            public void empty()
            {
                assertThrows(Exception.class, () -> ExpressionEvaluation.convertToPostfix(""));
            }

            @Test
            @DisplayName("throws exception if expression is null")
            public void nullExp()
            {
                assertThrows(Exception.class, () -> ExpressionEvaluation.convertToPostfix(null));
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
                assertEquals("2 2 *", ExpressionEvaluation.convertToPostfix("2 * 2"));
            }

            @Test
            @DisplayName("Handles space at front")
            public void spaceFront()
            {
                assertEquals("2 2 /", ExpressionEvaluation.convertToPostfix(" 2/2"));
            }

            @Test
            @DisplayName("Handles space at end")
            public void spaceEnd()
            {
                assertEquals("2 2 ^", ExpressionEvaluation.convertToPostfix("2^2 "));
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
                assertEquals("2 3 4 * +", ExpressionEvaluation.convertToPostfix("2 + 3 * 4"));
            }

            @Test
            @DisplayName("Division has higher precedence than subtraction")
            public void subtractDivide()
            {
                assertEquals("2 3 4 / -", ExpressionEvaluation.convertToPostfix("2 - 3 / 4"));
            }

            @Test
            @DisplayName("Exponent has higher precedence than multiplication")
            public void exponentAdd()
            {
                assertEquals("2 3 4 ^ *", ExpressionEvaluation.convertToPostfix("2 * 3 ^ 4"));
            }

            @Test
            @DisplayName("() has higher precedence than ^")
            public void parenthesisExponent()
            {
                assertEquals("2 3 + 4 ^", ExpressionEvaluation.convertToPostfix("(2 + 3) ^ 4"));
            }
        }
    }

    @Nested
    @DisplayName("tokenize expression tests")
    class tokenizeExpressionTests
    {
        @Test
        @DisplayName("tokenizes simple expression")
        public void simple()
        {
            ArrayList<String> check;

            check = new ArrayList<>();

            check.addLast("2");
            check.addLast("+");
            check.addLast("2");

            assertEquals(check, ExpressionManipulation.tokenize("2+2"));
        }
    }
}

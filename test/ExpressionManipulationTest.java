package test;

import src.Model.Expression;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests to ensure the methods in the Expression class are working as intended
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
            assertEquals("2 2 +", new Expression("2+2").getPostfix());
        }

        @Test
        @DisplayName("Converts simple subtraction w ()")
        public void subtraction()
        {
            assertEquals("2 2 -", new Expression("(2-2)").getPostfix());
        }

        @Test
        @DisplayName("Converts complex expression with ()")
        public void complex1()
        {
            assertEquals("1 2 + 3 4 - - 6 5 - /", new Expression("(((1+2)-(3-4))/(6-5))").getPostfix());
        }

        @Test
        @DisplayName("Converts complex expression no ()")
        public void complex2()
        {
            assertEquals("2 4 * 2 2 1 ^ ^ -", new Expression("2*4-2^2^1").getPostfix());
        }

        @Test
        @DisplayName("Throws exception if expression is invalid")
        public void invalidExpression()
        {
            assertThrows(Exception.class, () -> new Expression("2(2+").getPostfix());
            assertThrows(Exception.class, () -> new Expression(")22+").getPostfix());
        }

        @Nested
        @DisplayName("Throws exception for null or blank expression")
        class nullBlankTests
        {
            @Test
            @DisplayName("throws exception if expression is blank")
            public void blank()
            {
                assertThrows(Exception.class, () -> new Expression(" "));
            }

            @Test
            @DisplayName("throws exception if expression is empty")
            public void empty()
            {
                assertThrows(Exception.class, () -> new Expression(""));
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
                assertEquals("2 2 *", new Expression("2 * 2").getPostfix());
            }

            @Test
            @DisplayName("Handles space at front")
            public void spaceFront()
            {
                assertEquals("2 2 /", new Expression(" 2/2").getPostfix());
            }

            @Test
            @DisplayName("Handles space at end")
            public void spaceEnd()
            {
                assertEquals("2 2 ^", new Expression("2^2 ").getPostfix());
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
                assertEquals("2 3 4 * +", new Expression("2 + 3 * 4").getPostfix());
            }

            @Test
            @DisplayName("Division has higher precedence than subtraction")
            public void subtractDivide()
            {
                assertEquals("2 3 4 / -", new Expression("2 - 3 / 4").getPostfix());
            }

            @Test
            @DisplayName("Exponent has higher precedence than multiplication")
            public void exponentAdd()
            {
                assertEquals("2 3 4 ^ *", new Expression("2 * 3 ^ 4").getPostfix());
            }

            @Test
            @DisplayName("() has higher precedence than ^")
            public void parenthesisExponent()
            {
                assertEquals("2 3 + 4 ^", new Expression("(2 + 3) ^ 4").getPostfix());
            }
        }
    }

    // commented out due to project reorganization
//    @Nested
//    @DisplayName("tokenize expression tests")
//    class tokenizeExpressionTests
//    {
//        @Test
//        @DisplayName("tokenizes simple expression with integers")
//        public void simpleInt()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("2");
//            check.addLast("+");
//            check.addLast("2");
//
//            assertEquals(check, Expression.tokenize("2+2"));
//        }
//
//        @Test
//        @DisplayName("tokenizes simple expression with doubles")
//        public void simpleDouble()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("2.0");
//            check.addLast("+");
//            check.addLast("2.0");
//
//            assertEquals(check, Expression.tokenize("2.0+2.0"));
//        }
//
//        @Test
//        @DisplayName("simple double expression with spaces between tokens")
//        public void simpleDoubleSpace()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("2.0");
//            check.addLast("+");
//            check.addLast("2.0");
//
//            assertEquals(check, Expression.tokenize("2.0 + 2.0"));
//        }
//
//        @Test
//        @DisplayName("simple expression mixed double and int")
//        public void simpleMixed()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("2.0");
//            check.addLast("+");
//            check.addLast("2");
//
//            assertEquals(check, Expression.tokenize("2.0+2"));
//        }
//
//        @Test
//        @DisplayName("simple expression with spaces in odd places")
//        public void simpleOddSpace()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("2.0");
//            check.addLast("+");
//            check.addLast("2");
//
//            assertEquals(check, Expression.tokenize(" 2.0+ 2 "));
//        }
//
//        @Test
//        @DisplayName("complicated expression")
//        public void complicated()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("(");
//            check.addLast("5");
//            check.addLast("+");
//            check.addLast("2.3");
//            check.addLast(")");
//            check.addLast("*");
//            check.addLast("24");
//            check.addLast("^");
//            check.addLast("0.5");
//            check.addLast("-");
//            check.addLast("(");
//            check.addLast("15");
//            check.addLast("/");
//            check.addLast("3");
//            check.addLast(")");
//
//            assertEquals(check, Expression.tokenize("(5+2.3 ) *24^0.5 -( 15 / 3)   "));
//        }
//
//        @Test
//        @DisplayName("simple expression with variables")
//        public void variablesSimple()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("A");
//            check.addLast("+");
//            check.addLast("B");
//
//            assertEquals(check, Expression.tokenize("A+B"));
//        }
//
//        @Test
//        @DisplayName("simple expression with variables")
//        public void variablesComplicated()
//        {
//            ArrayList<String> check;
//
//            check = new ArrayList<>();
//
//            check.addLast("(");
//            check.addLast("A");
//            check.addLast("+");
//            check.addLast("b");
//            check.addLast(")");
//            check.addLast("*");
//            check.addLast("X");
//            check.addLast("^");
//            check.addLast("y");
//            check.addLast("-");
//            check.addLast("(");
//            check.addLast("C");
//            check.addLast("/");
//            check.addLast("c");
//            check.addLast(")");
//
//            assertEquals(check, Expression.tokenize(" (A+b ) *X^y -( C / c)   "));
//        }
//    }
}

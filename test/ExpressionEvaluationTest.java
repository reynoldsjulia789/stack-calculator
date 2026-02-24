package test;

import src.Model.ExpressionEvaluation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests to verify the methods in the ExpressionEvaluation class are working as intended
 * @author Julia Reynolds
 */
public class ExpressionEvaluationTest
{
    @Nested
    @DisplayName("postfix expression evaluation tests")
    class evaluatePostfix
    {
        @Test
        @DisplayName("Basic Addition")
        public void add()
        {
            assertEquals(4, ExpressionEvaluation.evaluatePostfixExpression("22+"));
        }

        @Test
        @DisplayName("Basic Subtraction")
        public void subtract()
        {
            assertEquals(1, ExpressionEvaluation.evaluatePostfixExpression("43-"));
        }

        @Test
        @DisplayName("Basic Multiplication")
        public void multiply()
        {
            assertEquals(10, ExpressionEvaluation.evaluatePostfixExpression("25*"));
        }

        @Test
        @DisplayName("Basic Integer Division")
        public void divide()
        {
            assertEquals(2, ExpressionEvaluation.evaluatePostfixExpression("52/"));
        }

        @Test
        @DisplayName("Throws exception if attempting to divide by 0")
        public void divide0()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpression("20/"));
        }

        @Test
        @DisplayName("Basic Exponent")
        public void exponent()
        {
            assertEquals(25, ExpressionEvaluation.evaluatePostfixExpression("52^"));
        }

        @Test
        @DisplayName("Handles spaces between characters")
        public void space()
        {
            assertEquals(4, ExpressionEvaluation.evaluatePostfixExpression("2 2 +"));
        }

        @Test
        @DisplayName("Handles space at front of string")
        public void spaceFront()
        {
            assertEquals(4, ExpressionEvaluation.evaluatePostfixExpression(" 22+"));
        }

        @Test
        @DisplayName("Handles space at end of string")
        public void spaceEnd()
        {
            assertEquals(4, ExpressionEvaluation.evaluatePostfixExpression("22+ "));
        }

        @Test
        @DisplayName("evaluates complex expression")
        public void complex()
        {
            assertEquals(4, ExpressionEvaluation.evaluatePostfixExpression("1 2 + 3 4 - - 6 5 - /"));
        }

        @Test
        @DisplayName("throws exception if expression is blank")
        public void blank()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpression(" "));
        }

        @Test
        @DisplayName("throws exception if expression is empty")
        public void empty()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpression(""));
        }

        @Test
        @DisplayName("throws exception if expression is null")
        public void nullExp()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpression(null));
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
            assertEquals(4.0, ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("2 2 +"));
        }

        @Test
        @DisplayName("Basic Subtraction")
        public void subtract()
        {
            assertEquals(1.0, ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("43.5 42.5 -"));
        }

        @Test
        @DisplayName("Basic Multiplication")
        public void multiply()
        {
            assertEquals(10.0, ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("2.0 5.0 *"));
        }

        @Test
        @DisplayName("Basic Integer Division")
        public void divide()
        {
            assertEquals(2.5, ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("5 2 /"));
        }

        @Test
        @DisplayName("Throws exception if attempting to divide by 0")
        public void divide0()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("2 0 /"));
        }

        @Test
        @DisplayName("Basic Exponent")
        public void exponent()
        {
            assertEquals(25.0, ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("5 2.0 ^"));
        }

        @Test
        @DisplayName("evaluates complex expression")
        public void complex()
        {
            assertEquals(4.0, ExpressionEvaluation.evaluatePostfixExpressionWithDoubles("1 2 + 3 4 - - 6 5 - /"));
        }

        @Test
        @DisplayName("throws exception if expression is blank")
        public void blank()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpressionWithDoubles(" "));
        }

        @Test
        @DisplayName("throws exception if expression is empty")
        public void empty()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpressionWithDoubles(""));
        }

        @Test
        @DisplayName("throws exception if expression is null")
        public void nullExp()
        {
            assertThrows(Exception.class, () -> ExpressionEvaluation.evaluatePostfixExpressionWithDoubles(null));
        }
    }
}

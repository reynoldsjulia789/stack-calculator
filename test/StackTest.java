package test;

import org.junit.jupiter.api.DisplayName;
import src.Model.Stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests to ensure the Stack class is working as intended
 * @author Julia Reynolds
 */
public class StackTest
{
    @Nested
    class stackPushTests
    {
        @Test
        @DisplayName("Pushes One Item")
        public void addItem()
        {
            Stack<Integer> test = new Stack<>();

            test.push(1);

            assertEquals(1, test.peek());
        }

        @Test
        @DisplayName("Pushes items correctly")
        public void addMany()
        {
            Stack<Integer> test = new Stack<>();

            test.push(1);
            test.push(2);
            test.push(3);

            assertEquals(3, test.peek());
        }
    }

    @Nested
    class stackSizeTests
    {
        @Test
        @DisplayName("Empty stack has size 0")
        public void emptyStack()
        {
            Stack<Integer> test = new Stack<>();

            assertEquals(0, test.size());
        }

        @Test
        @DisplayName("Stack Updates size to 1")
        public void stackUpdatesSizePush1()
        {
            Stack<Integer> test = new Stack<>();

            test.push(1);

            assertEquals(1, test.size());
        }

        public void stacksize5()
        {
            Stack<Integer> test = new Stack<>();

            test.push(1);
            test.push(2);
            test.push(3);
            test.push(4);
            test.push(5);

            assertEquals(5, test.size());
        }

        @Test
        public void stackUpdatesSizeOnPop()
        {
            Stack<Integer> test = new Stack<>();

            test.push(1);
            test.push(2);
            test.push(3);
            test.push(4);
            test.push(5);

            test.pop();

            assertEquals(4, test.size());
        }
    }
}

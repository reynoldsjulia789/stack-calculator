package test;

import org.junit.jupiter.api.DisplayName;
import src.Stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackTest
{
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

        }
    }
}

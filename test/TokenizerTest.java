package test;

import src.Model.Tokenizer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenizerTest
{
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

            assertEquals(check, Tokenizer.tokenize("2+2"));
        }
    }
}

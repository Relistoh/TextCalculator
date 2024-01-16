package com.github.relistoh.text_calculator.expression;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpressionFinderTest {

    @Test
    void testEvaluateMathExpressions_ValidExpression() {
        String result = ExpressionFinder.evaluateMathExpressions("2 + 3 * (4 - 1)");
        assertEquals("11.0", result);
    }

    @Test
    void testEvaluateMathExpressions_InvalidExpression() {
        String result = ExpressionFinder.evaluateMathExpressions("2 + 3 * (4 - 1))");
        assertEquals("2 + 3 * (4 - 1))", result);
    }

    @Test
    void testEvaluateMathExpressions_MultipleExpressions() {
        String result = ExpressionFinder.evaluateMathExpressions("(2+3*(4-1))/10");
        assertEquals("1.1", result);
    }
}

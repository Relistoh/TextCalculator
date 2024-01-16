package com.github.relistoh.text_calculator.expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionCalculatorTest {

    @Test
    void testEvaluateExpression_Addition() {
        double result = ExpressionCalculator.evaluateExpression("2+3");
        assertEquals(5.0, result);
    }

    @Test
    void testEvaluateExpression_Subtraction() {
        double result = ExpressionCalculator.evaluateExpression("5-2");
        assertEquals(3.0, result);
    }

    @Test
    void testEvaluateExpression_Multiplication() {
        double result = ExpressionCalculator.evaluateExpression("4*3");
        assertEquals(12.0, result);
    }

    @Test
    void testEvaluateExpression_Division() {
        double result = ExpressionCalculator.evaluateExpression("8/2");
        assertEquals(4.0, result);
    }

    @Test
    void testEvaluateExpression_ComplexExpression() {
        double result = ExpressionCalculator.evaluateExpression("2+3*4-6/2");
        assertEquals(11.0, result);
    }

    @Test
    void testEvaluateExpression_ExpressionWithParentheses() {
        double result = ExpressionCalculator.evaluateExpression("(2+3)*4");
        assertEquals(20.0, result);
    }

    @Test
    void testEvaluateExpression_DivisionByZero() {
        assertThrows(Exception.class, () -> {
            ExpressionCalculator.evaluateExpression("4/0");
        });
    }
}
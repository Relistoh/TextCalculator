package com.github.relistoh.text_calculator.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Stack;

public class ExpressionFinder {
    public static String evaluateMathExpressions(String input) {
        Pattern pattern = Pattern.compile("[0-9\\\\(][0-9+\\-*/ \\\\(\\\\)]+[0-9\\\\)]");
        Matcher matcher = pattern.matcher(input);

        StringBuilder resultBuffer = new StringBuilder();

        while (matcher.find()) {
            String expression = matcher.group();
            //System.out.println(expression);
            if (isValidExpression(expression)) {
                //System.out.println(expression);
                double result = ExpressionCalculator.evaluateExpression(expression);
                //System.out.println(result);
                matcher.appendReplacement(resultBuffer, String.valueOf(result));
            } else {
                System.out.println("Error: Invalid expression - " + expression);
            }
        }

        matcher.appendTail(resultBuffer);

        return resultBuffer.toString();
    }

    public static boolean isValidExpression(String expression) {
        Stack<Character> stack = new Stack<>();
        char prevChar = 0;

        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (isOperator(prevChar)) {
                    return false;
                }
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            } else if (isOperator(ch)) {
                if (isOperator(prevChar) || prevChar == '(') {
                    return false;
                }
            }
            prevChar = ch;
        }

        return stack.isEmpty();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}

package com.github.relistoh.text_calculator.expression;

import java.util.Stack;

public class ExpressionCalculator {

    public static double evaluateExpression(String input) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringBuilder currentNumber = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                currentNumber.append(c);
            } else if (isOperator(c)) {
                if (!currentNumber.isEmpty()) {
                    numbers.push(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                while (!operators.isEmpty() && hasPrecedence(operators.peek(), c)) {
                    applyOperation(numbers, operators.pop());
                }
                operators.push(c);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                if (!currentNumber.isEmpty()) {
                    numbers.push(Double.parseDouble(currentNumber.toString()));
                    currentNumber.setLength(0);
                }
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperation(numbers, operators.pop());
                }
                operators.pop();
            }
        }

        if (!currentNumber.isEmpty()) {
            numbers.push(Double.parseDouble(currentNumber.toString()));
        }

        while (!operators.isEmpty()) {
            applyOperation(numbers, operators.pop());
        }

        return numbers.pop();
    }

    private static void applyOperation(Stack<Double> numbers, char operator) {
        double operand2 = numbers.pop();
        double operand1 = numbers.pop();

        switch (operator) {
            case '+' -> numbers.push(operand1 + operand2);
            case '-' -> numbers.push(operand1 - operand2);
            case '*' -> numbers.push(operand1 * operand2);
            case '/' -> {
                if (operand2 != 0) {
                    numbers.push(operand1 / operand2);
                } else {
                    System.out.println("Деление на ноль!");
                }
            }
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int getPrecedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }

    private static boolean hasPrecedence(char op1, char op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);

        if (precedence1 == precedence2) {
            return true;
        }

        return precedence1 > precedence2;
    }
}
package org.example;

import java.util.Scanner;
import java.util.Stack;

public class Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the valid arithmetic expression: ");
        String expression = input.nextLine();
        System.out.println("Expression: " + expression);

        boolean isValid = isValidParenthisis(expression) && isValidOperators(expression);

        if (isValid) {
            try {
                String postfix = infixToPostfix(expression);
                double result = evaluatePostfix(postfix);
                System.out.printf("Result: %.2f%n", result);
            } catch (Exception e) {
                System.out.println("Error evaluating expression: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid expression!");
        }
    }

    public static boolean isValidOperators(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            char current = expression.charAt(i);
            char next = expression.charAt(i + 1);
            if (isOperator(current) && isOperator(next)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidParenthisis(String expression) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expression.toCharArray()) {
            if (ch == '(') {
                stack.push('(');
            } else if (ch == ')') {
                if (!stack.empty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                if (ch == '[' || ch == '{' || ch == ']' || ch == '}') {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String sanitizedInfix = infix.replaceAll("\\s+", "");
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < sanitizedInfix.length(); i++) {
            char c = sanitizedInfix.charAt(i);

            if (Character.isDigit(c) || c == '.') {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    postfix.append(number).append(" ");
                    number = new StringBuilder();
                }

                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        postfix.append(stack.pop()).append(" ");
                    }
                    if (!stack.isEmpty()) {
                        stack.pop(); // Remove '('
                    }
                } else if (isOperator(c)) {
                    while (!stack.isEmpty() && stack.peek() != '(' &&
                            getPrecedence(stack.peek()) >= getPrecedence(c)) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(c);
                }
            }
        }

        if (number.length() > 0) {
            postfix.append(number).append(" ");
        }

        while (!stack.isEmpty()) {
            if (stack.peek() != '(') {
                postfix.append(stack.pop()).append(" ");
            } else {
                stack.pop();
            }
        }

        return postfix.toString().trim();
    }

    private static double evaluatePostfix(String postfix) {
        Stack<Double> stack = new Stack<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.length() == 1 && isOperator(token.charAt(0))) {
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                double result = performOperation(operand1, operand2, token.charAt(0));
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop();
    }

    private static double performOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
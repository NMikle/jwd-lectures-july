package com.epam.jwd.interpreter;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public enum ReversePolishNotationExpressionParser implements ExpressionParser {
    INSTANCE;


    private static final Expression ADDITION_EXPRESSION = c -> c.push(c.pop() + c.pop());

    private static final Expression MINUS_EXPRESSION = c -> {
        final int secondOperand = c.pop();
        c.push(c.pop() - secondOperand);
    };

    private static final Expression MULTIPLICATION_EXPRESSION = c -> c.push(c.pop() * c.pop());

    private static final Expression DIVISION_EXPRESSION = c -> {
        final int secondOperand = c.pop();
        c.push(c.pop() / secondOperand);
    };

    @Override
    public Expression parse(String expression) {
        List<Expression> expressions = new LinkedList<>();
        for (String lexeme : expression.split("\\p{Blank}+")) {
            if (lexeme.isEmpty()) {
                continue;
            }
            final char operator = lexeme.charAt(0);
            switch (operator) {
                case '+':
                    expressions.add(ADDITION_EXPRESSION);
                    break;
                case '-':
                    expressions.add(MINUS_EXPRESSION);
                    break;
                case '*':
                    expressions.add(MULTIPLICATION_EXPRESSION);
                    break;
                case '/':
                    expressions.add(DIVISION_EXPRESSION);
                    break;
                default:
                    final Scanner scan = new Scanner(lexeme);
                    if (scan.hasNextInt()) {
                        expressions.add(new ExpressionNumber(scan.nextInt()));
                    }
                    break;
            }
        }
        return c -> {
            for (Expression exp : expressions) {
                exp.interpret(c);
            }
        };
    }
}

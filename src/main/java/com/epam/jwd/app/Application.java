package com.epam.jwd.app;

import com.epam.jwd.interpreter.Expression;
import com.epam.jwd.interpreter.ExpressionParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        String expression = "4 7 + 2 * 8 -";
        final ExpressionParser parser = ExpressionParser.reversePolishNotation();
        final Expression parsedExpression = parser.parse(expression);
        final Number result = parsedExpression.result();
        System.out.println(result);
    }
}

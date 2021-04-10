package string;

import java.util.Stack;

/**
 * Asked in Arc.dev online assessment in 2021. All the basic calculator type problem are based on the following
 * implementation.
 */
public class _224_BasicCalculator {

    private void processExpression(char operator, Stack<Integer> valStack) {
        int a, b;

        if (isMarker(operator)) {
            a = valStack.pop();

            switch (operator) {
                case 'p':
                    valStack.push(+a);
                    break;
                case 'm':
                    valStack.push(-a);
                    break;
            }
        } else {
            b = valStack.pop();
            a = valStack.pop();

            switch (operator) {
                case '+':
                    valStack.push(a + b);
                    break;
                case '-':
                    valStack.push(a - b);
                    break;
                case '*':
                    valStack.push(a * b);
                    break;
                case '/':
                    valStack.push(a / b);
                    break;
            }
        }
    }

    private int priority(char operator) {
        if (operator == 'p' || operator == 'm')
            return 3;
        if (operator == '*' || operator == '/')
            return 2;
        if (operator == '+' || operator == '-')
            return 1;

        return -1;
    }

    private boolean isHigherPriority(char topOperator, char currentOperator) {
        return priority(topOperator) >= priority(currentOperator);
    }

    private boolean isNum(char ch) {
        int num = ch - '0';
        return num >= 0 && num <= 9;
    }

    private boolean isMarker(char operator) {
        return operator == 'p' || operator == 'm';
    }

    private boolean isUnary(char operator) {
        return operator == '+' || operator == '-';
    }

    private int evaluate(String expression) {
        Stack<Integer> valStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        boolean mayBeUnary = true;

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ')
                continue;

            if (ch == '(') {
                opStack.push(ch);
                mayBeUnary = true;
            } else if (ch == ')') {
                while (!opStack.isEmpty() && opStack.peek() != '(') {
                    processExpression(opStack.pop(), valStack);
                }
                opStack.pop();
                mayBeUnary = false;
            } else if (!isNum(ch)) {
                if (mayBeUnary && isUnary(ch))
                    ch = ch == '+' ? 'p' : 'm';

                while (!opStack.isEmpty() && (
                        !isMarker(ch) && isHigherPriority(opStack.peek(), ch) ||
                                isMarker(ch) && priority(opStack.peek()) > priority(ch)
                )
                ) {
                    processExpression(opStack.pop(), valStack);
                }
                opStack.push(ch);
                mayBeUnary = true;
            } else {
                int value = 0;

                while (i < expression.length() && isNum(expression.charAt(i))) {
                    value = value * 10 + expression.charAt(i++) - '0';
                }

                valStack.push(value);
                i--;
                mayBeUnary = false;
            }
        }

        while (!opStack.isEmpty()) {
            processExpression(opStack.pop(), valStack);
        }

        return valStack.pop();
    }

    public static void main(String[] args) {
        _224_BasicCalculator ee = new _224_BasicCalculator();
        String[] expressions = {
                "55     +35",
                "55  - 2   +35",
                "2 + ( 8 * (5 - 3) + 10 * 5) / 2",
                "+48 + -49",
                "4*+(  5*  -6) +5",
                "+2"
        };

        for (String exp : expressions)
            System.out.println(ee.evaluate(exp));
    }
}

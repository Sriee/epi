package strings;

import java.util.Stack;

public class _150_EvaluateExpression {

    private boolean isOp(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private void process(char operator, Stack<Integer> valueStack) {
        int b = valueStack.pop();
        int a = valueStack.pop();

        switch (operator) {
            case '+':
                valueStack.push(a + b);
                break;
            case '-':
                valueStack.push(a - b);
                break;
            case '*':
                valueStack.push(a * b);
                break;
            case '/':
                valueStack.push(a / b);
                break;
        }
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> valueStack = new Stack<>();

        for (String token : tokens) {
            if (isOp(token)) {
                process(token.charAt(0), valueStack);
            } else {
                valueStack.push(Integer.parseInt(token));
            }
        }

        return valueStack.pop();
    }

    public static void main(String[] args) {
        _150_EvaluateExpression ee = new _150_EvaluateExpression();

        String[][] inps = {
                {"2", "1", "+", "3", "*"},
                {"4", "13", "5", "/", "+"},
                {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
        };

        for(String[] expression : inps)
            System.out.println(ee.evalRPN(expression));
    }
}

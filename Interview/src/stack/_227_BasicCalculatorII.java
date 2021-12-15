package stack;

import java.util.*;

public class _227_BasicCalculatorII {

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = s.length(), number = 0, result = 0;
        char operator = '+';

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            boolean num = isNumeric(ch - '0');
            if (num) {
                number = number * 10 + (ch - '0');
            }

            if ((!num && ch != ' ') || i == len - 1) {
                switch (operator) {
                    case '+':
                        stack.push(number);
                        break;
                    case '-':
                        stack.push(-number);
                        break;
                    case '*':
                        stack.push(stack.pop() * number);
                        break;
                    case '/':
                        stack.push(stack.pop() / number);
                        break;
                }
                operator = ch;
                number = 0;
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    private boolean isNumeric(int num) {
        return num >= 0 && num < 10;
    }

    public static void main(String[] args) {
        _227_BasicCalculatorII bc = new _227_BasicCalculatorII();
        String[] expressions = {
                "3+2*2",
                " 3/2 ",
                " 3+5 / 2 ",
                " 7  / 2 * 3",
                "5",
                "1*2-3/4+5*6-7*8+9/10"
        };

        for (int i = 0; i < expressions.length; i++) {
            System.out.println(i + 1 + ".\tExpression = \"" + expressions[i] + "\"");
            System.out.println("\tOutput: " + bc.calculate(expressions[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

}

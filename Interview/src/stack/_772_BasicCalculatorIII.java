package stack;

import java.util.*;

public class _772_BasicCalculatorIII {

    public int calculate(String s) {
        return calculate(0, s)[1];
    }

    private int[] calculate(int i, String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int number = 0, len = s.length(), result = 0;
        char operator = '+';

        for (;i < len; i++) {
            char ch = s.charAt(i);
            boolean isNum = isNumeric(ch);
            if (isNum) {
                number = number * 10 + (ch - '0');
            }

            if (ch == '(') {
                int[] res = calculate(i + 1, s);
                i = res[0];
                number = res[1];
            }

            if (!isNum || ch == ')' || i == len - 1){
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

            if (ch == ')')
                break;
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return new int[] { i, result };
    }

    /**
     * Using this helper method is faster than using Character.isDigit()
     */
    private boolean isNumeric(char ch) {
        int num = ch - '0';
        return num >= 0 && num < 10;
    }

    public static void main(String[] args) {
        _227_BasicCalculatorII bc = new _227_BasicCalculatorII();
        String[] expressions = {
                "3+2*2",
                "5",
                "1*2-3/4+5*6-7*8+9/10",
                "2*(5+5*2)/3+(6/2+8)"
        };

        for (int i = 0; i < expressions.length; i++) {
            System.out.println(i + 1 + ".\tExpression = \"" + expressions[i] + "\"");
            System.out.println("\tOutput: " + bc.calculate(expressions[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

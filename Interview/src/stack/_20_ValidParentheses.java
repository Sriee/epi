package stack;

import java.util.*;

public class _20_ValidParentheses {
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int top = -1;
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{')
                stack[++top] = ch;
            else {
                if (top == -1 || stack[top] != map.get(ch)) {
                    return false;
                }

                top--;
            }
        }

        return top == -1;
    }

    public static void main(String[] args) {
        _20_ValidParentheses vp = new _20_ValidParentheses();
        String[] inputs = {
                "[",
                "}",
                "[{}]",
                "[](){}",
                "(){[{()}]}"
        };

        for (int i = 0; i < inputs.length; i++) {
            System.out.println(i + 1 + ".\tInput = \"" + inputs[i] + "\"");
            System.out.println("\tIs Valid: " + vp.isValid(inputs[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }
}

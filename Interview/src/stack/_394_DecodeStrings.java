package stack;

import java.util.Stack;

public class _394_DecodeStrings {

    /**
     * Two Stack approach: Clean approach
     */
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> stack = new Stack<>();
        StringBuilder runner = new StringBuilder();
        int k = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // Didn't know character has a isDigit check. We were using our own implementation
            if (Character.isDigit(ch)) {
                k = (k * 10) + ch - '0';
            } else if (ch == '[') {
                stack.push(runner);
                runner = new StringBuilder();

                numStack.push(k);
                k = 0;
            } else if (ch == ']') {
                StringBuilder decoded = stack.pop();

                for (int n = numStack.pop(); n > 0; n--)
                    decoded.append(runner); // Didn't know we can append StringBuilder to another StringBuilder
                runner = decoded;
            } else {
                runner.append(ch);
            }
        }

        return runner.toString();
    }

    public static void main(String[] args) {
        _394_DecodeStrings ds = new _394_DecodeStrings();
        String[] input = {
                "3[a]2[bc]",
                "3[a]",
                "3[a]2[b]1[c]",
                "jkl3[a]2[b]1[c]mno",
                "4",
                "mnbvd",
                "2[a10[b3[c]de]2[f]]",
                "3[a2[c]]",
                "3[z]2[2[y]pq4[2[jk]e1[f]]]ef",
        };

        for (String s : input)
            System.out.println(ds.decodeString(s));
    }
}

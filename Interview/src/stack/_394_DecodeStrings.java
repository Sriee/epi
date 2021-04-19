package stack;

import java.util.Stack;

public class _394_DecodeStrings {

    /**
     * Two Stack approach: Uglified implementation.
     */
    public String decodeString(String s) {
        String[] arr = s.split("");
        Stack<String> stack = new Stack<>();
        Stack<Integer> num = new Stack<>();
        StringBuilder runner = new StringBuilder(), sb;
        int k = 0;

        for (String curr : arr) {

            if (Character.isDigit(curr.charAt(0))) {
                if (runner.length() != 0) {
                    while (!stack.isEmpty() && !stack.peek().equals("["))
                        runner.insert(0, stack.pop());

                    stack.push(runner.toString());
                    runner = new StringBuilder();
                }

                k = (k * 10) + curr.charAt(0) - '0';
            } else if (curr.equals("[")) {
                num.push(k);
                stack.push(curr);
                k = 0;
            } else if (curr.equals("]")) {
                if (runner.length() != 0) {
                    while (!stack.peek().equals("["))
                        runner.insert(0, stack.pop());

                    stack.push(runner.toString());
                    runner = new StringBuilder();
                }

                k = num.pop();
                sb = new StringBuilder();

                String top = stack.pop();
                for (int i = 0; i < k; i++)
                    sb.append(top);

                stack.pop(); // Remove [

                if (!stack.isEmpty() && !stack.peek().equals("["))
                    sb.insert(0, stack.pop());

                stack.push(sb.toString());
                k = 0;

            } else {
                runner.append(curr);
            }
        }

        if (runner.length() != 0)
            stack.push(runner.toString());

        sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.insert(0, stack.pop());

        return sb.toString();
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

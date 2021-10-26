package stack;

import java.util.*;

public class _1249_MinRemoveParantheses {

    /**
     * TC: O(n)
     * SC: O(n)
     *
     * Note: Using ArrayDeque improved the run time over using Stack. The run time
     *       reduced from 44 ms to 15 ms. Always use Deque over Stack!
     */
    public String minRemoveToMakeValid(String s) {
        char[] arr = s.toCharArray();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                if (!stack.isEmpty() && arr[stack.peek()] == ')')
                    arr[stack.pop()] = '0';

                stack.push(i);
            } else if (arr[i] == ')') {
                if (stack.isEmpty()) {
                    arr[i] = '0';
                } else if (arr[stack.peek()] == '(') {
                    stack.pop();
                } else {
                    arr[stack.pop()] = '0';
                }
            }
        }

        while (!stack.isEmpty()) {
            arr[stack.pop()] = '0';
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0')
                continue;
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        _1249_MinRemoveParantheses mrp = new _1249_MinRemoveParantheses();
        String[] input = {
            "lee(t(c)o)de)",
            "a)b(c)d",
            "))(("
        };

        for (String s : input)
            System.out.println(mrp.minRemoveToMakeValid(s));
    }
}

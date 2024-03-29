package sq;

import java.util.*;

public class Miscellaneous {

    /**
     * Leet code problem. Solution -> Accepted We are given an array asteroids of
     * integers representing asteroids in a row. For each asteroid, the absolute
     * value represents its size, and the sign represents its direction (+ meaning
     * right, - meaning left). Conditions: 1. If two asteroids meet, the smaller one
     * will explode. 2. If both are the same size, both will explode. Two asteroids
     * moving in the same direction will never meet.
     * 
     * @param asteroids array asteroids of integers representing asteroids in a row
     * @return state of the asteroids after all collisions
     */
    public int[] steroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0)
            return asteroids;

        int[] stack = new int[asteroids.length];
        int top = -1;
        boolean done = false;

        for (int i = 0; i < asteroids.length; i++) {
            stack[++top] = asteroids[i];
            done = false;
            while (top > 0 && !done) {
                if (stack[top - 1] > 0 && stack[top] < 0) {
                    if (Math.abs(stack[top]) == Math.abs(stack[top - 1]))
                        top -= 2;
                    else {
                        stack[top - 1] = (Math.abs(stack[top]) > Math.abs(stack[top - 1]) ? stack[top]
                                : stack[top - 1]);
                        top--;
                    }
                } else {
                    done = true;
                }
            }
        }

        return Arrays.copyOf(stack, top + 1);
    }

    /**
     * Stack version for the above.
     * 
     * @param asteroids array asteroids of integers representing asteroids in a row
     * @return state of the asteroids after all collisions
     */
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0)
            return asteroids;

        Stack<Integer> stack = new Stack<>();
        boolean done = false;

        for (int i = 0; i < asteroids.length; i++) {
            stack.push(asteroids[i]);
            done = false;
            while (!stack.isEmpty() && stack.size() > 1 && !done) {
                int a = stack.pop();
                int b = stack.pop();
                if (b > 0 && a < 0) {
                    if (Math.abs(a) == Math.abs(b))
                        continue;
                    else
                        stack.push((Math.abs(a) > Math.abs(b) ? a : b));
                } else {
                    done = true;
                    stack.push(b);
                    stack.push(a);
                }
            }
        }

        int[] result = new int[stack.size()];
        int idx = stack.size() - 1;
        while (!stack.isEmpty())
            result[idx--] = stack.pop();

        return result;
    }

    /**
     * Leet code problem. Solution -> Accepted Reverse Polish Notation (Array
     * Implementation)
     * 
     * @param expression arithmetic expression
     * @return evaluated result
     */
    public int evalRPN(String[] expression) {
        if (expression == null || expression.length == 0)
            return 0;

        int[] stack = new int[expression.length];
        int top = -1;
        for (String token : expression) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                switch (token.charAt(0)) {
                case '+':
                    stack[top - 1] = stack[top - 1] + stack[top];
                    break;
                case '-':
                    stack[top - 1] = stack[top - 1] - stack[top];
                    break;
                case '*':
                    stack[top - 1] = stack[top - 1] * stack[top];
                    break;
                case '/':
                    stack[top - 1] = stack[top - 1] / stack[top];
                    break;
                }
                top--;
            } else {
                stack[++top] = Integer.parseInt(token);
            }
        }

        return stack[0];
    }

    /**
     * Leet code problem. Solution -> Accepted.
     * 
     * @param n     number of process ids
     * @param logs. List of logs
     * @return execution time of functions
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        int[][] stack = new int[logs.size()][4];
        int top = -1;
        for (String entry : logs) {
            String[] stringTokens = entry.split(":");

            stack[++top] = new int[] { Integer.parseInt(stringTokens[0]), (stringTokens[1].equals("start")) ? 1 : 0,
                    Integer.parseInt(stringTokens[2]), 0 };

            while (top > 0 && stack[top][1] != 1) {
                int[] b = stack[top];
                int[] a = stack[top - 1];
                top -= 2;
                if (top > -1)
                    stack[top][3] = stack[top][3] + (b[2] - a[2]) + 1;

                result[a[0]] = result[a[0]] + (b[2] - a[2]) + 1 - a[3];
            }
        }
        return result;
    }

    /**
     * Leet code problem. Solution -> Accepted. Given a sequence of n integers a1,
     * a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k
     * and ai < ak < aj.
     *
     * @param nums
     * @return true: if 132 pattern found false: otherwise
     */
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;

        int s3 = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < s3) {
                return true;
            } else {
                while (!stack.isEmpty() && nums[i] > stack.peek()) {
                    s3 = stack.pop();
                }
                stack.push(nums[i]);
            }
        }
        return false;
    }

    /**
     * Leet code problem. Solution -> Accepted. Given a circular array (the next
     * element of the last element is the first element of the array), print the
     * Next Greater Number for every element.
     * 
     * @param args
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        int[] stack = new int[nums.length];
        int top = -1;
        for (int i = 2 * nums.length - 1; i >= 0; i--) {
            while (top > -1 && nums[stack[top]] <= nums[i % nums.length]) {
                top--;
            }
            res[i % nums.length] = top == -1 ? -1 : nums[stack[top]];
            stack[++top] = i % nums.length;
        }
        return res;
    }

    /**
     * Leet code problem. Solution -> Accepted Given a non-negative integer num
     * represented as a string, remove k digits from the number so that the new
     * number is the smallest possible.
     * 
     * @param word maxlen = 10002
     * @param k    number of characters to be removed
     * @return smallest number without trailing zeros as String
     */
    public String removeKdigits(String word, int k) {
        if (word == null || word.isEmpty() || word.length() == k)
            return "0";

        int idx = 0, top = 0, digits = word.length() - k;
        char[] stack = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            while (top > 0 && stack[top - 1] > ch && k > 0) {
                top--;
                k--;
            }

            stack[top++] = ch;
        }

        while (idx < digits && stack[idx] == '0')
            idx++;

        return (idx == digits) ? "0" : new String(stack, idx, digits - idx);
    }

    Queue<Integer> counter = new ArrayDeque<>();

    /**
     * Count any ping within time frame [t - 3000, t] including the current ping.
     * Leet code. Solution -> Accepted
     * 
     * @param t represents some time in milliseconds
     * @return the number of pings that have been made from 3000 milliseconds ago
     *         until now
     */
    public int pingCounter(int t) {
        counter.add(t);
        while (counter.peek() < t - 3000)
            counter.poll();
        return counter.size();
    }

    public static void main(String[] args) {
        Miscellaneous misc = new Miscellaneous();

        System.out.println(misc.removeKdigits("", 1));
        System.out.println(misc.removeKdigits(null, 1));
        System.out.println(misc.removeKdigits("10", 2));
        System.out.println(misc.removeKdigits("1863", 4));
        System.out.println(misc.removeKdigits("1432219", 3));
        System.out.println(misc.removeKdigits("10200", 1));
    }
}

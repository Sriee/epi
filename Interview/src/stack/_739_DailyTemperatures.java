package stack;

import java.util.Arrays;
import java.util.Stack;

public class _739_DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int top = stack.pop();
                result[top] = i - top;
            }
            stack.push(i);
        }

        return result;
    }

    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length, top = -1;
        int[] result = new int[n];
        int[] stack = new int[n << 1];

        for (int i = 0; i < n; i++) {
            while (top != -1 && temperatures[i] > temperatures[stack[top]]) {
                result[stack[top]] = i - stack[top];
                top--;
            }
            stack[++top] = i;
        }

        return result;
    }

    public static void main(String[] args) {
        _739_DailyTemperatures dt = new _739_DailyTemperatures();
        int[][] testCases = {
                {73, 74, 75, 72, 69, 72, 76, 73},
                {55, 54, 53, 52},
                {65, 66, 78, 80},
                {55, 54, 53, 52, 65, 66, 78, 80},
                {65, 66, 78, 80, 55, 54, 53, 52},
                {100}
        };

        int[] res;

        for (int i = 0; i < testCases.length; i++) {
            if ((i & 1) == 0) {
                res = dt.dailyTemperatures(testCases[i]);
                System.out.print("Stack: ");
            } else {
                res = dt.dailyTemperatures2(testCases[i]);
                System.out.print("Array Based Stack: ");
            }
            System.out.println(Arrays.toString(res));
        }
    }
}

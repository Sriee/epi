package hash_table;

import util.PrintHypens;

import java.util.*;

public class _496_NextGreaterElement {
    /**
     * Let m = number of elements in num1, and
     *     n = number of elements in num2
     *     m << n
     *
     * TC: O(n)
     * SC: O(n)
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums2) {
            while (!stack.isEmpty() && stack.peek() < i) {
                map.put(stack.pop(), i);
            }
            stack.push(i);
        }

        while (!stack.isEmpty())
            map.put(stack.pop(), -1);

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        _496_NextGreaterElement ng = new _496_NextGreaterElement();
        int[][] A = {
                {2, 4},
                {3, 2, 5},
                {14, 45, 52},
                {1, 3, 2},
                {4, 2},
                {0}
        };
        int[][] B = {
                {1, 2, 3, 4},
                {2, 3, 5, 1},
                {52, 14, 45, 65},
                {1, 3, 2, 4, 5},
                {1, 2, 4, 3},
                {0}
        };

        int x = 1;
        for (int i = 0; i < A.length; i++) {
            System.out.print(i + 1);
            System.out.println(".\tNums 1 = " + Arrays.toString(A[i]));
            System.out.println("\tNums 2 = " + Arrays.toString(B[i]));
            System.out.print("");
            System.out.println("\tThe Next Greater Element Array = " + Arrays.toString(ng.nextGreaterElement(A[i], B[i])));
            System.out.println(PrintHypens.generate());
        }
    }
}
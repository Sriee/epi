package linkedlist;

import java.util.*;
import util.PrintHypens;

public class _1019_NextGreaterNode {
    public int[] nextLargerNodes(ListNode head) {
        Map<Integer, ListNode> idxNodeMap = new HashMap<>();
        int n = 0, top = -1, i = 0;
        for (ListNode iter = head; iter != null; iter = iter.next, n++) {
            idxNodeMap.put(n, iter);
        }
        int[] res = new int[n];
        int[] stack = new int[n];

        for (ListNode iter = head; iter != null; iter = iter.next) {
            while (top != -1 && idxNodeMap.get(stack[top]).val < iter.val) {
                res[stack[top--]] = iter.val;
            }

            stack[++top] = i++;
        }

        return res;
    }

    public static void main(String[] args) {
        _1019_NextGreaterNode ngn = new _1019_NextGreaterNode();
        int[][] input = {
                {2, 1, 5},
                {52, 14, 45, 65, 11, 23, 11, 52},
                {8, 0, 6, 1, 0, 7, 8, 7, 5, 3, 5, 2, 4, 9}
        };
        for (int i = 0; i < input.length; i++) {
            ListNode head = ListUtils.construct(input[i]);

            System.out.print("\n" + (i + 1) + ".\tInput = ");
            ListUtils.print(head);

            System.out.println("\tThe Next Greater Element Array = " + Arrays.toString(ngn.nextLargerNodes(head)));
            System.out.println(PrintHypens.generate());
        }
    }
}

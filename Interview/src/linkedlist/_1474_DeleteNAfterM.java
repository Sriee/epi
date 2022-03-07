package linkedlist;

import util.PrintHypens;

import java.util.Arrays;

public class _1474_DeleteNAfterM {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode prev = null, curr = head;

        while (curr != null) {
            for (int i = 0; i < m && curr != null; i++) {
                prev = curr;
                curr = curr.next;
            }

            for (int i = 0; i < n && curr != null; i++) {
                curr = curr.next;
            }

            prev.next = curr;
        }

        prev.next = null;
        return head;
    }

    public static void main(String[] args) {
        _1474_DeleteNAfterM dnm = new _1474_DeleteNAfterM();

        int[][] input = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13},
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}
        };

        int[] ms = {2, 1};
        int[] ns = {3, 3};

        for (int i = 0; i < input.length; i++) {
            ListNode head = ListUtils.construct(input[i]);

            System.out.printf("\n%d. Input= %s\n", (i + 1), Arrays.toString(input[i]));
            System.out.printf("   List with n=%d nodes after m=%d nodes= ", ns[i], ms[i]);
            ListUtils.print(dnm.deleteNodes(head, ms[i], ns[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}

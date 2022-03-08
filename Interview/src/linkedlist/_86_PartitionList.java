package linkedlist;

import util.PrintHypens;

import java.util.Arrays;

public class _86_PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode leftHead = new ListNode(), rightHead = new ListNode();
        ListNode iter1 = leftHead, iter2 = rightHead;

        for (ListNode curr = head; curr != null; curr = curr.next) {
            if (curr.val < x) {
                iter1.next = curr;
                iter1 = iter1.next;
            } else {
                iter2.next = curr;
                iter2 = iter2.next;
            }
        }

        iter2.next = null;
        iter1.next = rightHead.next;
        return leftHead.next;
    }

    public static void main(String[] args) {
        _86_PartitionList pl = new _86_PartitionList();

        int[][] input = {
                {1, 4, 3, 2, 5, 2},
                {2, 1}
        };

        int[] xs = {3, 2};

        for (int i = 0; i < input.length; i++) {
            ListNode head = ListUtils.construct(input[i]);

            System.out.printf("\n%d. Input= %s\n", (i + 1), Arrays.toString(input[i]));
            System.out.print("   Partitioned List= ");
            ListUtils.print(pl.partition(head, xs[i]));
            System.out.println(PrintHypens.generate());
        }
    }
}

package linkedlist;

import util.PrintHypens;

import java.util.*;

public class _1836_RemoveDuplicatesUnsorted {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode dummyHead = new ListNode(), iter = head;
        Map<Integer, Integer> map = new HashMap<>();

        while (iter != null) {
            map.put(iter.val, map.getOrDefault(iter.val, 0) + 1);
            iter = iter.next;
        }

        ListNode curr = dummyHead;
        iter = head;

        while (iter != null) {
            if (map.get(iter.val) == 1) {
                curr.next = iter;
                curr = iter;
            }
            iter = iter.next;
        }

        curr.next = null;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        _1836_RemoveDuplicatesUnsorted rds = new _1836_RemoveDuplicatesUnsorted();

        int[][] inputs = {
                {1,2,3,2},
                {3,2,2,1,3,2,4}
        };

        for (int[] inp : inputs) {
            ListNode inpHead = ListUtils.construct(inp);
            System.out.print("Input = ");
            ListUtils.print(inpHead);
            System.out.print("List with duplicates removed = ");
            ListUtils.print(rds.deleteDuplicatesUnsorted(inpHead));
            System.out.println(PrintHypens.generate());
            System.out.println();
        }
    }
}

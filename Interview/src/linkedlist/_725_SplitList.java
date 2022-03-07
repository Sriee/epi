package linkedlist;

import util.PrintHypens;

public class _725_SplitList {
    public ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] ans = new ListNode[k];
        if (head == null)
            return ans;

        int len = 0;
        for (ListNode iter = head; iter != null; iter = iter.next) {
            len++;
        }

        int split = len / k, remaining = k > len ? 0 : len % k, idx = 0;
        ListNode curr = head;
        while (curr != null) {
            ans[idx++] = curr;

            for (int i = 1; i < split; i++) {
                curr = curr.next;
            }

            if (remaining > 0) {
                curr = curr.next;
                remaining--;
            }

            ListNode temp = curr.next;
            curr.next = null;
            curr = temp;
        }

        return ans;
    }

    public static void main(String[] args) {
        _725_SplitList spl = new _725_SplitList();

        int[][] input = {
                {1, 2, 3},
                {1,2,3,4,5,6,7,8,9,10}
        };

        int[] ns = {5, 3};

        for (int i = 0; i < input.length; i++) {
            ListNode head = ListUtils.construct(input[i]);

            System.out.print("\n" + (i + 1) + ".\tInput = ");
            ListUtils.print(head);

            System.out.print("\tSplit List:\n");
            ListNode[] res = spl.splitListToParts(head, ns[i]);
            for (ListNode node : res) {
                ListUtils.print(node);
            }
            System.out.println(PrintHypens.generate());
        }
    }
}

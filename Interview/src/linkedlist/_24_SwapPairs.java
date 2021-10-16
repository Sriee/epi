package linkedlist;

public class _24_SwapPairs {

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null)
            return head;

        int n = ListUtils.length(head);
        int g = n / 2;
        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, curr = head, temp;

        for (int i = 0; i < g && curr.next != null; i++) {
            // Swap
            temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;

            prev = curr;
            curr = curr.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        _24_SwapPairs sp = new _24_SwapPairs();

        // 1
        int[] input = {8, 0, 6, 1, 0, 7, 8, 7, 5, 3, 5, 2, 4, 9};
        ListNode head = ListUtils.construct(input);
        ListNode revHead = sp.swapPairs(head);
        ListUtils.print(revHead);

        // 2
        input = new int[] {3, 5};
        head = ListUtils.construct(input);
        revHead = sp.swapPairs(head);
        ListUtils.print(revHead);
    }
}

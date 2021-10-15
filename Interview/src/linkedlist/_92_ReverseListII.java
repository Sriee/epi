package linkedlist;

public class _92_ReverseListII {

    /**
     * TC: O(n)
     * SC: O(1)
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;

        ListNode dummy = new ListNode(-1, head);
        ListNode prev = dummy, curr = head, temp;
        for (int i = 1; i < left; i++) {
            prev = curr;
            curr = curr.next;
        }

        for (int i = 0; i < right - left; i++) {
            temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        _92_ReverseListII rl = new _92_ReverseListII();
        int[] input = {8, 0, 6, 1, 0, 7, 8, 7, 5, 3, 5, 2, 4, 9};

        ListNode head = ListUtils.construct(input);
        ListNode revHead = rl.reverseBetween(head, 3, 11);
        ListUtils.print(revHead);
    }
}

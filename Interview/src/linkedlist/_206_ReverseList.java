package linkedlist;

public class _206_ReverseList {

    /**
     * Iterative approach.
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(-1, head), iter = head, temp;

        while (iter.next != null) {
            temp = iter.next;
            iter.next = temp.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }
        return dummy.next;
    }

    /**
     * Recursive approach.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return node;
    }

    public static void main(String[] args) {
        _206_ReverseList rl = new _206_ReverseList();
        int[][] inputs = {
            {1, 2, 3, 4, 5},
            {1, 2}
        };

        for (int[] inp : inputs) {
            ListNode head = ListUtils.construct(inp);
            ListNode revHead = rl.reverse(head);
            ListUtils.print(revHead);
        }
    }
}

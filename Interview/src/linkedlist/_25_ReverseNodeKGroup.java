package linkedlist;

public class _25_ReverseNodeKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1)
            return head;

        int n = ListUtils.length(head);
        int g = n / k;
        ListNode dummy = new ListNode(-1, head);
        ListNode curr = dummy, prev, temp;

        for (int i = 0; i < g && curr.next != null; i++) {
            prev = curr;
            curr = curr.next;

            for (int j = 1; j < k; j++) {
                temp = curr.next;
                curr.next = temp.next;
                temp.next = prev.next;
                prev.next = temp;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        _25_ReverseNodeKGroup rkg = new _25_ReverseNodeKGroup();

        int[] input = {8, 0, 6, 1, 0, 7, 8, 7, 5, 3, 5, 2, 4, 9};
        int[] ks = {3, 7, 9};

        for (int k : ks) {
            ListNode head = ListUtils.construct(input);
            ListNode revHead = rkg.reverseKGroup(head, k);
            ListUtils.print(revHead);
        }
    }
}

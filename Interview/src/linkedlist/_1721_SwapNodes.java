package linkedlist;

public class _1721_SwapNodes {

    /**
     * Return the head of the linked list after swapping the values of the kth
     * node from the beginning and the kth node from the end.
     *
     * TC: O(k) + O(n); WC: if k = n => O(2n) = O(n)
     *     moving Left + Right pointer
     * SC: O(1)
     */
    public ListNode swapNodes(ListNode head, int k) {
        // Left
        ListNode left = head;
        for (int i = 1; i < k; i++)
            left = left.next;

        // Right
        ListNode right = head, iter = head;
        for (int i = 0; i < k; i++)
            iter = iter.next;

        while (iter != null) {
            right = right.next;
            iter = iter.next;
        }

        swap(left, right);
        return head;
    }

    private void swap(ListNode left, ListNode right) {
        int temp = left.val;
        left.val = right.val;
        right.val = temp;
    }

    public static void main(String[] args) {
        _1721_SwapNodes sn = new _1721_SwapNodes();

        int[] input = {8, 0, 6, 1, 0, 7, 8, 7, 5, 3, 5, 2, 4, 9};
        int[] ks = {3, 7, 9, 1};

        for (int k : ks) {
            ListNode head = ListUtils.construct(input);
            ListNode revHead = sn.swapNodes(head, k);
            ListUtils.print(revHead);
        }
    }
}

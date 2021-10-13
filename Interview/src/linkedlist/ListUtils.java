package linkedlist;

public class ListUtils {

    /**
     * Calculate length of a LinkedList
     */
    public static int length(ListNode node) {
        ListNode fast = node;
        int len = 0;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            len += 2;
        }
        return fast == null ? len : len + 1;
    }

}
